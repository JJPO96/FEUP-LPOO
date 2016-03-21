package maze.logic;

import java.util.Random;
import java.util.Stack;

import maze.logic.Maze.Direction;
import maze.logic.Maze.Token;

public class MazeBuilder implements IMazeBuilder {

	private char[][] maze;
	private char[][] visitedMaze;
	private Stack<Position> pathCell;
	private Position guideCell;
	private int size;
	private int numDragons = 3;
	private Direction guideDirection;
	
	// TODO: VERIFICAR AO CRIAR NOVO LABIRINTO SE � POSS�VEL GANHAR O JOGO

	/**
	 * Builds a Maze
	 * 
	 * @param size of the Maze
	 * @param numDragons of the Maze
	 */
	@Override
	public char[][] buildMaze(int size, int numDragons) {
		// TODO: LAN�AR THROW EM CASA DO TAMANHO PRETENDIDO SER PAR OU MENOR QUE UM DETERMINADO NUMERO OU
		// NUMERO DE DRAGOES MENOR QUE 1

		this.size = size;
		this.numDragons = numDragons;

		initMaze();		
		setExitandGuideCell();
		initVisitedMaze();
		pathCell = new Stack<Position>();
		pathCell.push(guideCell);

		while(!pathCell.empty()){
			if (hasNeighboorCellsUnvisited()){ // Checks if there are neighboor cells unvisited
				generateRandDirection();
				if(validDirection()){
					moveGuideCell();
				}
			}

			else{ // There are no neighboor cells unvisited
				pathCell.pop();

				if (!pathCell.empty()){
					maze[guideCell.getY()*2+1][guideCell.getX()*2+1]=Token.PATH.getSymbol();
					guideCell = pathCell.peek();
				}				
			}
		}

		maze[guideCell.getY()*2+1][guideCell.getX()*2+1]=Token.PATH.getSymbol();
		
		addGameElements();

		return maze;
	}
	
	/**
	 * Initializes the Maze
	 */
	public void initMaze(){

		maze = new char[size][size];		

		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (i%2 == 0 || j%2==0)
					maze[i][j] = Token.WALL.getSymbol();
				else
					maze[i][j] = Token.PATH.getSymbol();
			}
		}
	}

	/**
	 * Creates an Array to track the visited cells of the Maze
	 */
	public void initVisitedMaze(){


		visitedMaze = new char[(size-1)/2][(size-1)/2];

		for (int i = 0; i < visitedMaze.length; i++){
			for (int j = 0; j < visitedMaze[i].length; j++){
				visitedMaze[i][j]=Token.UNVISITED.getSymbol();
			}
		}

		visitedMaze[guideCell.getY()][guideCell.getX()]=Token.GUIDE.getSymbol();
	}

	/**
	 * Creates an exit and guicell in the Maze
	 */
	public void setExitandGuideCell(){
		Random rand = new Random();
		int coordExit = rand.nextInt(size);
		int randNumber = rand.nextInt(4);

		while(coordExit%2 == 0){
			coordExit = rand.nextInt(size);
		}

		switch(randNumber){
		case 0: // Up border
			maze[0][coordExit]=Token.EXIT.getSymbol();
			maze[1][coordExit]=Token.GUIDE.getSymbol();
			guideCell = new Position((coordExit-1)/2, 0);
			break;
		case 1: // Down border
			maze[size-1][coordExit]=Token.EXIT.getSymbol();
			maze[size-2][coordExit]=Token.GUIDE.getSymbol();
			guideCell = new Position((coordExit-1)/2, (size-3)/2);
			break;
		case 2: // Left Border
			maze[coordExit][0]=Token.EXIT.getSymbol();
			maze[coordExit][1]=Token.GUIDE.getSymbol();
			guideCell = new Position(0, (coordExit-1)/2);
			break;
		case 3: // Right Border
			maze[coordExit][size-1]=Token.EXIT.getSymbol();
			maze[coordExit][size-2]=Token.GUIDE.getSymbol();
			guideCell = new Position((size-3)/2, (coordExit-1)/2);
			break;
		}		
	}
	
	/**
	 * Verifies if there are still unvisited neighboor cells
	 * 
	 * @return true if there are unvisited neighboor cells
	 */
	public boolean hasNeighboorCellsUnvisited(){

		if (guideCell.getY()>=0 && guideCell.getX()+1 >= 0 && guideCell.getY()<visitedMaze.length && guideCell.getX()+1 < visitedMaze.length)
			if (visitedMaze[guideCell.getY()][guideCell.getX()+1]==Token.UNVISITED.getSymbol())
				return true;
		if (guideCell.getY()>=0 && guideCell.getX()-1 >= 0 && guideCell.getY()<visitedMaze.length && guideCell.getX()-1 < visitedMaze.length)
			if (visitedMaze[guideCell.getY()][guideCell.getX()-1]==Token.UNVISITED.getSymbol())
				return true;
		if (guideCell.getY()+1>=0 && guideCell.getX() >= 0 && guideCell.getY()+1<visitedMaze.length && guideCell.getX() < visitedMaze.length)
			if (visitedMaze[pathCell.peek().getY()+1][pathCell.peek().getX()]==Token.UNVISITED.getSymbol())
				return true;
		if (guideCell.getY()-1>=0 && guideCell.getX() >= 0 && guideCell.getY()-1<visitedMaze.length && guideCell.getX() < visitedMaze.length)
			if (visitedMaze[guideCell.getY()-1][guideCell.getX()]==Token.UNVISITED.getSymbol())
				return true;

		return false;
	}

	/**
	 * Verifies if a direction is valid
	 * 
	 * @return true if the direction is valid
	 */
	public boolean validDirection(){

		switch(guideDirection){
		case LEFT:
			if (guideCell.getY()>=0 && guideCell.getX()-1 >= 0 && guideCell.getY() < visitedMaze.length && guideCell.getX()-1 < visitedMaze.length)
				if(visitedMaze[guideCell.getY()][guideCell.getX()-1]==Token.UNVISITED.getSymbol())
					return true;
			break;
		case RIGHT:
			if(guideCell.getY()>=0 && guideCell.getX()+1 >= 0 && guideCell.getY() < visitedMaze.length && guideCell.getX()+1 < visitedMaze.length)
				if(visitedMaze[guideCell.getY()][guideCell.getX()+1]==Token.UNVISITED.getSymbol())
					return true;
			break;
		case UP:
			if (guideCell.getY()-1>=0 && guideCell.getX() >= 0 && guideCell.getY()-1<visitedMaze.length && guideCell.getX() < visitedMaze.length)
				if(visitedMaze[guideCell.getY()-1][guideCell.getX()]==Token.UNVISITED.getSymbol())
					return true;
			break;
		case DOWN:
			if (guideCell.getY()+1>=0 && guideCell.getX() >= 0 && guideCell.getY()+1<visitedMaze.length && guideCell.getX() < visitedMaze.length)
				if(visitedMaze[guideCell.getY()+1][guideCell.getX()]==Token.UNVISITED.getSymbol())
					return true;
			break;
		}

		return false;
	}

	/**
	 * Generate a random direction to move the guideCell
	 */
	public void generateRandDirection(){

		Random rand = new Random();
		int randDirection = rand.nextInt(4); // 0 left, 1 rigth, 2, up, 3, down

		switch(randDirection){
		case 0:
			guideDirection = Direction.LEFT;
			break;
		case 1:
			guideDirection = Direction.RIGHT;
			break;
		case 2:
			guideDirection = Direction.UP;
			break;
		case 3:
			guideDirection = Direction.DOWN;
			break;		
		}
	}
	
	/**
	 * Moves the guide cell according to the random direction generated by generateRandDirection()
	 */
	public void moveGuideCell(){

		switch(guideDirection){
		case LEFT:
			visitedMaze[guideCell.getY()][guideCell.getX()-1] = Token.VISITED.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1]=Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1-1] = Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1-2] = Token.GUIDE.getSymbol();
			guideCell = new Position(guideCell.getX()-1, guideCell.getY());
			pathCell.push(guideCell);
			break;
		case RIGHT:
			visitedMaze[guideCell.getY()][guideCell.getX()+1] = Token.VISITED.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1]=Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1+1] = Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1+2] = Token.GUIDE.getSymbol();
			guideCell = new Position(guideCell.getX()+1, guideCell.getY());
			pathCell.push(guideCell);
			break;
		case UP:
			visitedMaze[guideCell.getY()-1][guideCell.getX()] = Token.VISITED.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1]=Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1-1][(guideCell.getX()*2)+1] = Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1-2][(guideCell.getX()*2)+1] = Token.GUIDE.getSymbol();
			guideCell = new Position(guideCell.getX(), guideCell.getY()-1);
			pathCell.push(guideCell);
			break;
		case DOWN:
			visitedMaze[guideCell.getY()+1][guideCell.getX()] = Token.VISITED.getSymbol();
			maze[(guideCell.getY()*2)+1][(guideCell.getX()*2)+1]=Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1+1][(guideCell.getX()*2)+1] = Token.PATH.getSymbol();
			maze[(guideCell.getY()*2)+1+2][(guideCell.getX()*2)+1] = Token.GUIDE.getSymbol();
			guideCell = new Position(guideCell.getX(), guideCell.getY()+1);
			pathCell.push(guideCell);			
			break;
		}
	}
	
	/**
	 * Add a Hero to the Maze
	 * 
	 * @param rand to generate random positions
	 */
	void addHero(Random rand){
		int randPosX;
		int randPosY;
		do{
			randPosX = rand.nextInt(maze.length-1)+1;
			randPosY = rand.nextInt(maze.length-1)+1;
			
			if (maze[randPosY][randPosX]==Token.PATH.getSymbol()){
				maze[randPosY][randPosX] = Token.HERO.getSymbol();
				break;
			}		

		}while(true);
	}
	
	/**
	 * Add Dragon/Dragons to the Maze
	 * 
	 * @param rand to generate random positions
	 */
	void addDragons(Random rand){
		int randPosX;
		int randPosY;
		for(int i = 0;i < numDragons;i++){
			do{
				randPosX = rand.nextInt(maze.length-1)+1;
				randPosY = rand.nextInt(maze.length-1)+1;

				if (maze[randPosY][randPosX]==Token.PATH.getSymbol()){
					if (maze[randPosY][randPosX+1]!=Token.HERO.getSymbol()&&maze[randPosY][randPosX-1]!=Token.HERO.getSymbol()
							&&maze[randPosY+1][randPosX]!=Token.HERO.getSymbol()&&maze[randPosY-1][randPosX]!=Token.HERO.getSymbol()){
						maze[randPosY][randPosX] = Token.DRAGON.getSymbol();
						break;
					}				
				}	

			}while(true);
		}
	}

	/**
	 * Add a Sword to the Maze
	 * 
	 * @param rand to generate random positions
	 */
	void addSword(Random rand){
		int randPosX;
		int randPosY;
		do{
			randPosX = rand.nextInt(maze.length-1)+1;
			randPosY = rand.nextInt(maze.length-1)+1;
			
			if (maze[randPosY][randPosX]==Token.PATH.getSymbol()){
				maze[randPosY][randPosX] = Token.SWORD.getSymbol();
				break;
			}			

		}while(true);
	}

	/**
	 * Add Game's Elements to the Maze
	 */
	public void addGameElements(){
		Random rand = new Random();
		
		addHero(rand);
		addDragons(rand);
		addSword(rand);		
	}
}
