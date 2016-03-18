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
	private Position exitCell;
	private int size;
	private int numDragons = 3;
	private Direction guideDirection;
	
	// TODO: VERIFICAR AO CRIAR NOVO LABIRINTO SE É POSSÍVEL GANHAR O JOGO

	@Override
	public char[][] buildMaze(int size, int numDragons) throws IllegalArgumentException {
		// TODO  FALTA ACRESCENTAR POSSIBILIDADE DE MAIS DE UM DRAGÃO
		// TODO: LANÇAR THROW EM CASA DO TAMANHO PRETENDIDO SER PAR OU MENOR QUE UM DETERMINADO NUMERO OU
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

	public void initVisitedMaze(){


		visitedMaze = new char[(size-1)/2][(size-1)/2];

		for (int i = 0; i < visitedMaze.length; i++){
			for (int j = 0; j < visitedMaze[i].length; j++){
				visitedMaze[i][j]=Token.UNVISITED.getSymbol();
			}
		}

		visitedMaze[guideCell.getY()][guideCell.getX()]=Token.GUIDE.getSymbol();
	}

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
			exitCell = new Position(coordExit, 0);
			maze[1][coordExit]=Token.GUIDE.getSymbol();
			guideCell = new Position((coordExit-1)/2, 0);
			break;
		case 1: // Down border
			maze[size-1][coordExit]=Token.EXIT.getSymbol();
			exitCell = new Position(coordExit, size-1);
			maze[size-2][coordExit]=Token.GUIDE.getSymbol();
			guideCell = new Position((coordExit-1)/2, (size-3)/2);
			break;
		case 2: // Left Border
			maze[coordExit][0]=Token.EXIT.getSymbol();
			exitCell = new Position(0, coordExit);
			maze[coordExit][1]=Token.GUIDE.getSymbol();
			guideCell = new Position(0, (coordExit-1)/2);
			break;
		case 3: // Right Border
			maze[coordExit][size-1]=Token.EXIT.getSymbol();
			exitCell = new Position(size-1, coordExit);
			maze[coordExit][size-2]=Token.GUIDE.getSymbol();
			guideCell = new Position((size-3)/2, (coordExit-1)/2);
			break;
		}		
	}

	public char[][] getMaze(){
		return maze;
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

	public void addGameElements(){
		Random rand = new Random();
		
		addHero(rand);
		addDragons(rand);
		addSword(rand);		
	}

	public String toString(){
		String board = "";

		for (char[] line: maze){
			for (char symbol: line){
				board+=symbol;
			}
			board+="\n";
		}

		return board;
	}

}
