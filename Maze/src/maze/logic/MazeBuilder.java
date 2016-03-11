package maze.logic;

import java.util.Random;
import java.util.Stack;

import maze.logic.Maze.Token;

public class MazeBuilder implements IMazeBuilder {

	private char[][] maze;
	private char[][] suportMaze;
	private Stack<Position> pathCell;
	private Position guideCell;
	private Position exitCell;

	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		// TODO  É PRECISO FAZER ESTE MÉTODO

		maze = new char[size][size];
		pathCell = new Stack<Position>();

		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (i%2 == 0 || j%2==0)
					maze[i][j] = Token.WALL.getSymbol();
				else
					maze[i][j] = Token.PATH.getSymbol();
			}
		}

		suportMaze = new char[(size-1)/2][(size-1)/2];

		for (int i = 0; i < suportMaze.length; i++){
			for (int j = 0; j < suportMaze[i].length; j++){
				suportMaze[i][j]=Token.UNVISITED.getSymbol();
			}
		}

		setExitandGuideCell(size);

		return maze;
	}

	//TODO O labirinto está completo quando todas as células vazias estão visitadas
	// (stack fica vazia)

	public void setExitandGuideCell(int size){
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
			guideCell = new Position(coordExit, 1);
			break;
		case 1: // Down border
			maze[size-1][coordExit]=Token.EXIT.getSymbol();
			exitCell = new Position(coordExit, size-1);
			maze[size-2][coordExit]=Token.GUIDE.getSymbol();
			guideCell = new Position(coordExit, size-2);
			break;
		case 2: // Left Border
			maze[coordExit][0]=Token.EXIT.getSymbol();
			exitCell = new Position(0, coordExit);
			maze[coordExit][1]=Token.GUIDE.getSymbol();
			guideCell = new Position(1, coordExit);
			break;
		case 3: // Right Border
			maze[coordExit][size-1]=Token.EXIT.getSymbol();
			exitCell = new Position(size-1, coordExit);
			maze[coordExit][size-2]=Token.GUIDE.getSymbol();
			guideCell = new Position(size-2, coordExit);
			break;
		}
	}

	public char[][] getMaze(){
		return maze;
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
