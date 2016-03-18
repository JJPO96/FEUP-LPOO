package maze.logic;

import java.util.Random;
import java.util.Vector;

public class Maze {

	/* BEGGINER for static dragon, INTERMEDIATE moving/sleeping dragon 
	   and EXPERT for only moving dragon */
	public enum Mode {BEGGINER, INTERMEDIATE, EXPERT};

	/* Directions that hero can take */
	public enum Direction {LEFT, RIGHT, UP, DOWN};

	/* Tokens to be used in the game */
	public enum Token {HERO('H'), HEROARMED('A'), DRAGON('D'), DRAGONSLEEP('d'), SWORD('E'),
		DRAGSWORD('F'), PATH(' '), WALL('X'), EXIT('S'), GUIDE('+'), VISITED('+'), UNVISITED('.');

		private final char symbol;

		Token(char symbol){
			this.symbol = symbol;
		}

		public char getSymbol() {
			return symbol;
		}
	};

	private Board gameBoard;
	private Hero hero;
	private Vector<Dragon> dragons = new Vector<Dragon>();
	private Sword sword;
	private boolean running;
	private boolean mazeOpen;
	private Mode mode;	

	/**
	 * Maze's construtor
	 * @param mazeSize 
	 * @param dragons 
	 */
	public Maze(Mode m, int numDragons, int mazeSize){

		// TODO: FALTA COLOCAR NO CONSTRUTOR DO MAZEBUILDER O NUMERO DE DRAGOES
		MazeBuilder mazeRandom = new MazeBuilder();
		this.gameBoard = new Board();
		this.gameBoard.setBoard(mazeRandom.buildMaze(mazeSize, numDragons));
		this.mode = m;		
		this.running = true;
		this.setMazeOpen(false);
		init();
	};

	public Maze(char[][] board, Mode m){		
		this.gameBoard = new Board();
		this.gameBoard.setBoard(board);
		this.mode = m;		
		this.running = true;
		this.setMazeOpen(false);
		init();
	};	

	/**
	 * Initializes the elements of the game
	 */
	public void init(){

		for (int i = 0; i < gameBoard.getBoard().length; i++){
			for (int j = 0; j < gameBoard.getBoard()[i].length; j++){
				if (gameBoard.getBoard()[i][j] == Token.HERO.getSymbol()){
					gameBoard.getBoard()[i][j] = Token.PATH.getSymbol();
					hero = new Hero(j, i, Token.HERO.getSymbol());
				}

				else if (gameBoard.getBoard()[i][j] == Token.DRAGON.getSymbol()){
					gameBoard.getBoard()[i][j] = Token.PATH.getSymbol();
					dragons.add(new Dragon(j, i, Token.DRAGON.getSymbol()));
				}

				else if (gameBoard.getBoard()[i][j] == Token.SWORD.getSymbol()){
					gameBoard.getBoard()[i][j] = Token.PATH.getSymbol();
					sword = new Sword(j, i, Token.SWORD.getSymbol());
				}
			}
		}	
	}	

	/**
	 * Verifies if the game is still running
	 * 
	 * @return true if is running
	 */
	public boolean isRunning() {
		return running;
	}

	public Board getgameBoard(){		
		return gameBoard;
	}

	public Vector<Integer> hasDragonNear(int a, int b){
		Vector<Integer> ret = new Vector<Integer>();
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i == 0 && j == 0 || (a+i!=a && b+i!=b))
					continue;
				else{
					for(int k= 0;k < dragons.size();k++){
						if (dragons.get(k).getPos().getX() == a+i && dragons.get(k).getPos().getY()== b+j)
							ret.add(k);
					}
				}
			}
		}		

		return ret;
	}

	public void checkSword(){

		if (hero.pos.equals(sword.pos)){
			if (!sword.isPicked()){
				hero.setArmed();
				sword.setPicked();
			}
		}
	}

	/**
	 * Verifies if hero is adjacent to the dragon and if yes, updates Hero condition
	 * 
	 * @param indVec Vector with indices od the Dragons near Hero
	 */
	public void checkDragonFight(Vector<Integer> indVec){
		for(int i: indVec){
			if (hero.isArmed())
				dragons.get(i).setAlive(false);
			else if (!dragons.get(i).isSleeping()&&!hero.isArmed()){
				hero.setAlive(false);
				running = false;
			}
		}
	}

	public boolean checkCollision(int x, int y){
		Position posTemp = new Position(x, y);

		if (gameBoard.checkCollision(x, y) || posTemp.equals(sword.pos))
			return true;

		return false;
	}	

	public void moveHero(int a, int b){

		Vector<Integer> dragonsNear = new Vector<Integer>();
		dragonsNear = hasDragonNear(hero.pos.x+a, hero.pos.y+b);

		if(checkCollision(hero.pos.x+a, hero.pos.y+b) || dragonsNear.size() > 0){			

			if(!gameBoard.checkWall(hero.pos.x+a, hero.pos.y+b)){				
				if(gameBoard.checkExit(hero.pos.x+a, hero.pos.y+b)){
					if(!hasDragonsAlive()){
						hero.pos.updatePos(a, b);
						running = false;
						mazeOpen = true;
					}
				}

				else if (!occupiedByDragon(hero.pos.x+a, hero.pos.y+b)){
					hero.pos.updatePos(a, b);
					checkSword();
					if (dragonsNear.size() > 0)
						checkDragonFight(dragonsNear);
				}
			}
		}
		
		else 
			if (occupiedByDragon(hero.pos.x+a, hero.pos.y+b)){
				if (!dragonIsAlive(hero.pos.x+a, hero.pos.y+b))
					hero.pos.updatePos(a, b);
			}
			else
				hero.pos.updatePos(a, b);
	}
	
	public boolean dragonIsAlive(int a, int b){
		
		//TODO: CRIAR FUNÇÃO QUE COMPARA POSIÇÕES DIRETAMENTE (USANDO POS)
		
		for (Dragon dragon: dragons)
			if (dragon.getPos().getX() == a && dragon.getPos().getY() == b)
				if (dragon.isAlive())
					return true;
		
		return false;
	}

	/**
	 * Verifies if there is some dragon alive
	 * 
	 * @return a boolean which is true in casse there is some dragon alive
	 */

	public boolean hasDragonsAlive() {
		for(Dragon dragon : dragons)
			if (dragon.isAlive())
				return true;

		return false;
	}	
	
	/**
	 * Verifies if a position in the maze is already occupied by a dragon 
	 * 
	 * @param a coord x of position
	 * @param b coord y of position
	 * @return
	 */
	public boolean occupiedByDragon(int a,int b){
		for(Dragon dragon : dragons){
			if(dragon.getPos().getX()==a && dragon.getPos().getY() == b)
				return true;
		}

		return false;
	}

	public boolean moveDragon(int a, int b, int i){
		Dragon dragon = dragons.get(i);

		if (!occupiedByDragon(a, b)){
			boolean hasDragonnear = false;
			if (hasDragonNear(hero.pos.x+a, hero.pos.y+b).size() > 0)
				hasDragonnear = true;

			if(!gameBoard.checkWall(dragon.pos.x+a, dragon.pos.y+b)&&!dragon.isSleeping()){
				dragon.pos.updatePos(a, b);

			}else return false;
			Vector<Integer> tempVec = new Vector<Integer>();
			tempVec.add(i);
			if (hasDragonnear)
				checkDragonFight(tempVec);

			return true;			
		}

		return false;
	}

	/**
	 * Moves the Hero if posible and updates his state
	 * 
	 * @param input with the direction the Hero will try to move
	 */
	public void updateHero(Direction input){

		switch (input){
		case LEFT:
			moveHero(-1, 0);
			break;
		case RIGHT:
			moveHero(1, 0);
			break;
		case UP:
			moveHero(0, -1);
			break;
		case DOWN:
			moveHero(0, 1);
			break;
		default:
			break;
		}
	}

	public void updateDragon(){
		Random rn = new Random();
		Dragon dragon;
		int rand; // 0 up, 1 down, 2 left and 3 right		
		for(int i = 0; i < dragons.size();i++) {
			dragon = dragons.get(i);
			if(this.mode == Mode.INTERMEDIATE){
				if(dragon.isSleeping() == true){
					if(rn.nextInt(2) == 0){ // Dragons wakes up
						dragon.setSleeping(false);
						moveDragon(0, 0, i);
					}
					else
						dragon.setSleeping(true); // Dragon sleeps
				}else{
					rand = rn.nextInt(5);

					switch(rand){
					case 0:
						moveDragon(-1, 0, i);
						break;
					case 1:
						moveDragon(1, 0, i);
						break;
					case 2:
						moveDragon(0, -1, i);
						break;
					case 3:
						moveDragon(0, 1, i);
						break;
					case 4:
						dragon.setSleeping(true); // Dragon sleeps
						break;
					default:
						break;
					}
				}		
			}

			else if (this.mode == Mode.EXPERT){
				rand = rn.nextInt(4);

				switch(rand){
				case 0:
					moveDragon(-1, 0, i);
					break;
				case 1:
					moveDragon(1, 0, i);
					break;
				case 2:
					moveDragon(0, -1, i);
					break;
				case 3:
					moveDragon(0, 1, i);
					break;
				default:
					break;
				}
			}

			else{
				moveDragon(0, 0, i);
			}
		}
	}

	/**
	 * Returns Hero object of the game
	 * 
	 * @return hero
	 */
	public Hero getHero(){
		return hero;
	}

	/**
	 * Return a dragon in a determinated position
	 * 
	 * @return a dragon
	 */
	public Dragon getDragon(int a, int b){
		
		for (Dragon dragon: dragons)
			if (dragon.getPos().getX()==a && dragon.getPos().getY() == b)
				return dragon;
		
		
		return null;
	}

	/**
	 * Returns Sword object of the game
	 * 
	 * @return sword
	 */
	public Sword getSword(){
		return sword;
	}

	public Mode getMode(){
		return mode;
	}

	/**
	 * Updates game state according to the input command received from the user
	 * 
	 * @param input with the direction in which the Hero will try to move
	 */
	public void update(Direction input){
		updateHero(input);
		updateDragon();
	}

	public boolean isMazeOpen() {
		return mazeOpen;
	}

	public void setMazeOpen(boolean mazeOpen) {
		this.mazeOpen = mazeOpen;
	}
}
