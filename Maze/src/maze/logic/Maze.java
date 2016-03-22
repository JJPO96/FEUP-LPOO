package maze.logic;

import java.util.Random;
import java.util.Vector;

public class Maze {

	/* STATIC for static dragons, MOVING for moving dragons 
	   and MOVINGSLEEPING for moving and sleeping dragons*/
	public enum Mode {STATIC, MOVING, MOVINGSLEEPING};

	/* Directions that the Hero can take */
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
	 * Maze's Construtor
	 * 
	 * @param mode of the Game
	 * @param size of the Maze
	 * @param numDragons number of Dragons of the Maze
	 */
	public Maze(Mode mode, int numDragons, int size){

		MazeBuilder mazeRandom = new MazeBuilder();
		this.gameBoard = new Board();
		this.gameBoard.setBoard(mazeRandom.buildMaze(size, numDragons));
		this.mode = mode;		
		this.running = true;
		this.mazeOpen = false;
		init();
	};

	/**
	 * Maze's Construtor
	 * 
	 * @param board of the Maze
	 * @param numDragons number of Dragons of the Maze
	 */
	public Maze(char[][] board, Mode m){		
		this.gameBoard = new Board();
		this.gameBoard.setBoard(board);
		this.mode = m;		
		this.running = true;
		this.mazeOpen = false;
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
	 * Updates game's state
	 * 
	 * @param input received from the User
	 */
	public void update(Direction input){

		updateHero(input);		
		updateDragons();		
	}

	/**
	 * Updates the state of the Hero
	 * 
	 * @param input with the direction
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

	/**
	 * Updates the state of the Dragons
	 */
	public void updateDragons(){
		Random rand = new Random();
		int randNumber;

		for(int i = 0; i < dragons.size();i++) {
			if(this.mode == Mode.MOVINGSLEEPING){
				if(dragons.get(i).isSleeping() == true){
					if(rand.nextInt(2) == 0){ // Dragons wakes up
						dragons.get(i).setSleeping(false);
						moveDragon(0, 0, dragons.get(i));
					}
					else
						dragons.get(i).setSleeping(true); // Dragon falls asleep
				}else{
					randNumber = rand.nextInt(5);

					switch(randNumber){
					case 0: // Moves UP the Dragon
						moveDragon(-1, 0, dragons.get(i));
						break;
					case 1: // Moves DOWN the Dragon
						moveDragon(1, 0, dragons.get(i));
						break;
					case 2: // Moves LEFT the Dragon
						moveDragon(0, -1, dragons.get(i));
						break;
					case 3: // Moves RIGHT the Dragon
						moveDragon(0, 1, dragons.get(i));
						break;
					case 4: // Dragon falls asleep
						dragons.get(i).setSleeping(true);
						break;
					default:
						break;
					}
				}		
			}

			else if (this.mode == Mode.MOVING){
				randNumber = rand.nextInt(4);

				switch(randNumber){
				case 0:
					moveDragon(-1, 0, dragons.get(i));
					break;
				case 1:
					moveDragon(1, 0, dragons.get(i));
					break;
				case 2:
					moveDragon(0, -1, dragons.get(i));
					break;
				case 3:
					moveDragon(0, 1, dragons.get(i));
					break;
				default:
					break;
				}
			}

			else{
				moveDragon(0, 0, dragons.get(i));
			}
		}
	}

	/**
	 * Moves the Hero
	 * 
	 * @param x variation of position
	 * @param y variation of position
	 */
	public void moveHero(int x, int y){

		// Verifies if the Hero collides with some element of the Maze
		if (!checkCollision(hero.getPos().x+x, hero.getPos().y+y)){
			System.out.println("x: "+x+"   y: "+y);
			hero.getPos().updatePos(x, y);			
			checkSword(); // Verifies if the Hero is in the same posiion of the Sword
		}
		
		else{ // Verifies if the Hero is at the Exit of the Maze
			if (gameBoard.checkExit(hero.getPos().x+x, hero.getPos().y+y))
				if(!hasDragonsAlive()){
					hero.getPos().updatePos(x, y);
					running = false;
					mazeOpen = true;
				}
		}

		// Verifies if there is any Dragon adjacent to the Hero, and if yes, the Hero fights the Dragon
		checkNeighboorDragons();
	}

	/**
	 * Moves the Dragon
	 * 
	 * @param x variation of position
	 * @param y variation of position
	 * @param dragon to be moved
	 */
	public void moveDragon(int x, int y, Dragon dragon){

		// Checks if the position is not already occupied
		if (!checkCollision(dragon.getPos().getX()+x, dragon.getPos().getY()+y))
			dragon.getPos().updatePos(x, y);

		if (isHeroNear(dragon.getPos())) // If the Hero is on an adjacent cell of the Dragon, the Dragon fights the Hero 
			initHeroDragonFight(dragon);	
	}

	/**
	 * Returns the Maze's board
	 * 
	 * @return the board
	 */
	public Board getGameBoard(){		
		return gameBoard;
	}

	/**
	 * Returns the Hero of the game
	 * 
	 * @return the Hero
	 */
	public Hero getHero(){
		return hero;
	}

	/**
	 * Returns Sword object of the game
	 * 
	 * @return the Sword
	 */
	public Sword getSword(){
		return sword;
	}

	/**
	 * Returns the Maze's Dragons
	 * 
	 * @return dragons
	 */
	public Vector<Dragon> getDragons(){
		return dragons;
	}

	/**
	 * Verifies if a position is occupied by a Dragon
	 * 
	 * @param pos of the Dragon
	 * @return a Dragon if the position is ocupied by a dragon or otherwise null
	 */
	public Dragon checkDragon(Position pos){

		for (Dragon dragon: dragons)
			if (dragon.getPos().equals(pos))
				return dragon;

		return null;
	}
	
	/**
	 * Hero trys to pick the Sword
	 */
	public void checkSword(){

		if (hero.getPos().equals(sword.getPos())){
			if (!sword.isPicked()){
				hero.setArmed();
				sword.setPicked();
			}
		}
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
	 * Checks collision of a game's element with the Wall or Exit of the Maze
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if the game's element collides
	 */
	public boolean checkCollision(int x, int y){
		Position pos = new Position(x, y);

		if (gameBoard.checkCollision(x, y) || (checkDragon(pos) instanceof Dragon && checkDragon(pos).isAlive()))
			return true;

		return false;
	}

	/**
	 * Verifies if the Hero is adjacent to a Dragon
	 * 
	 * @param pos position of the Dragon
	 * @return true if the Hero is adjacent to the Draggon
	 */
	public boolean isHeroNear(Position pos){

		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i == 0 && j == 0 || (pos.x+i!=pos.x && pos.y+j!=pos.y))
					continue;
				else{
					if (hero.getPos().getX() == pos.x+i && hero.getPos().getY()== pos.y+j)
						return true;						
				}
			}
		}		

		return false;
	}
	
	/**
	 * Verifies if there are any Dragon adjacent to the Hero
	 */
	public void checkNeighboorDragons(){
		
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i == 0 && j == 0 || (hero.getPos().getX()+i!=hero.getPos().getX() && hero.getPos().getY()+j!=hero.getPos().getY()))
					continue;
				else{
					for (int k = 0; k < dragons.size(); k++){
						if (dragons.get(k).getPos().getX() == hero.getPos().getX()+i && dragons.get(k).getPos().getY() == hero.getPos().getY()+j && dragons.get(k).isAlive())
							initHeroDragonFight(dragons.get(k));	// If the Dragon is adjacent, the Hero fights the Dragon					
					}
				}					
			}
		}
	}

	/**
	 * Hero faces a Dragon/Dragons
	 * 
	 * @param dragon to fight against the Hero
	 */
	public void initHeroDragonFight(Dragon dragon){
		
		if (hero.isArmed())
			dragon.setAlive(false);
		else if (dragon.isAlive() && !dragon.isSleeping() && !hero.isArmed()){
			hero.setAlive(false);
			running = false;
		}
	}		

	/**
	 * Verifies if the game is still running
	 * 
	 * @return true if the game is still running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Verifies if the Maze is open (Hero arrived to the exit)
	 * 
	 * @return true if the Maze is open
	 */
	public boolean isMazeOpen() {
		return mazeOpen;
	}	

	/**
	 * Returns Game's mode
	 * 
	 * @return mode
	 */
	public Mode getMode(){
		return mode;
	}

	/**
	 * Converts the Maze in a string to be used to represent it in text mode
	 * 
	 * @return the Maze as a string
	 */
	@Override
	public String toString(){

		Position pos = new Position();
		String mazeString = "";

		for (int i = 0; i < gameBoard.getBoard().length; i++){
			for ( int j = 0; j < gameBoard.getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);

				mazeString+=" "; // Spacing between elements

				if (hero.getPos().equals(pos))
					mazeString+= hero.getSymbol();

				else if(checkDragon(pos) instanceof Dragon && checkDragon(pos).isAlive()){
					if (pos.equals(sword.getPos()) && !sword.isPicked())
						mazeString += Token.DRAGSWORD.getSymbol();
					else
						mazeString+= checkDragon(pos).getSymbol();
				}				

				else if (sword.getPos().equals(pos) && !sword.isPicked())
					mazeString+= sword.getSymbol();
				else
					mazeString += gameBoard.getBoard()[i][j];
			}

			mazeString += "\n";
		}		

		return mazeString;		
	}	
}
