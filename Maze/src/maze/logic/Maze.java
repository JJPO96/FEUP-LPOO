package maze.logic;

import java.util.Random;

public class Maze {

	/* BEGGINER for static dragon, INTERMEDIATE moving/sleeping dragon 
	   and EXPERT for only moving dragon */
	public enum Mode {BEGGINER, INTERMEDIATE, EXPERT}; 
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	private Board gameBoard;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean running;
	private Mode mode;

	/**
	 * Maze's construtor
	 */
	public Maze(Mode m){
		this.gameBoard = new Board();
		this.mode = m;		
		this.running = true;
		init();
	};
	
	public Maze(char[][] board, Mode m){
		this.gameBoard = new Board();
		this.gameBoard.setBoard(board);
		this.mode = m;		
		this.running = true;
		init();
	};	

	/**
	 * Initializes the game in the mode selected
	 */
	public void init(){
		
		for (int i = 0; i < gameBoard.getBoard().length; i++){
			for (int j = 0; j < gameBoard.getBoard()[i].length; j++){
				if (gameBoard.getBoard()[i][j] == 'H'){
					gameBoard.getBoard()[i][j] = ' ';
					hero = new Hero(j, i, 'H');
				}
				
				else if (gameBoard.getBoard()[i][j] == 'D'){
					gameBoard.getBoard()[i][j] = ' ';
					dragon = new Dragon(j, i, 'D');
				}
				
				else if (gameBoard.getBoard()[i][j] == 'E'){
					gameBoard.getBoard()[i][j] = ' ';
					sword = new Sword(j, i, 'E');
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
	
	public boolean isDragonnear(int a, int b){
		
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i == 0 && j == 0 || (a+i!=a && b+i!=b))
					continue;
				else{
					if (dragon.getPos().getX() == a+i && dragon.getPos().getY()== b+j)
						return true;						
				}
			}
		}		
		
		return false;
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
	 */
	public void checkDragonFight(){
		
		if (hero.isArmed())
			dragon.setAlive(false);
		else if (!dragon.isSleeping()&&!hero.isArmed()){
			hero.setAlive(false);
			running = false;
		}
	}

	public boolean checkCollision(int x, int y){
		Position posTemp = new Position(x, y);

		if (gameBoard.checkCollision(x, y) || posTemp.equals(sword.pos))
			return true;
		return false;
	}	

	public void moveHero(int a, int b){
		
		boolean hasDragonnear = isDragonnear(hero.pos.x+a, hero.pos.y+b);

		if(checkCollision(hero.pos.x+a, hero.pos.y+b) || hasDragonnear){			
			if(!gameBoard.checkWall(hero.pos.x+a, hero.pos.y+b)){				
				if(gameBoard.checkExit(hero.pos.x+a, hero.pos.y+b)){
					if(!dragon.isAlive()){
						hero.pos.updatePos(a, b);
						running = false;
					}
				}
				
				else if ((hero.pos.x+a!=dragon.pos.x )|| (hero.pos.y+b!=dragon.pos.y)){
					hero.pos.updatePos(a, b);
					checkSword();
					if (hasDragonnear)
						checkDragonFight();
				}
			}
		}

		else if ((hero.pos.x+a!=dragon.pos.x )|| (hero.pos.y+b!=dragon.pos.y)){
			hero.pos.updatePos(a, b);
		}
	}
	
	public boolean moveDragon(int a, int b){
		if(!gameBoard.checkWall(dragon.pos.x+a, dragon.pos.y+b)&&!dragon.isSleeping()){
			dragon.pos.updatePos(a, b);
			
		}else return false;
		
		boolean hasDragonnear = isDragonnear(hero.pos.x, hero.pos.y);
		
		//checkSword();
		if (hasDragonnear)
			checkDragonFight();
		
		return true;
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
		int rand; // 0 up, 1 down, 2 left and 3 right		
		
		if(this.mode == Mode.INTERMEDIATE){
			if(dragon.isSleeping() == true){
				if(rn.nextInt(2) == 0){ // Dragons wakes up
					dragon.setSleeping(false);
					moveDragon(0, 0);
				}
				else
					dragon.setSleeping(true); // Dragon sleeps
			}else{
				rand = rn.nextInt(5);
				
				switch(rand){
				case 0:
					moveDragon(-1, 0);
					break;
				case 1:
					moveDragon(1, 0);
					break;
				case 2:
					moveDragon(0, -1);
					break;
				case 3:
					moveDragon(0, 1);
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
				moveDragon(-1, 0);
				break;
			case 1:
				moveDragon(1, 0);
				break;
			case 2:
				moveDragon(0, -1);
				break;
			case 3:
				moveDragon(0, 1);
				break;
			default:
				break;
			}
		}
		
		else{
			moveDragon(0, 0);
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
	 * Returns Dragon object of the game
	 * 
	 * @return dragon
	 */
	public Dragon getDragon(){
		return dragon;
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
	 * Update game state according to the input command received from the user
	 * 
	 * @param input with the direction in which the Hero will try to move
	 */
	public void update(Direction input){
		updateHero(input);
		updateDragon();
	}
}
