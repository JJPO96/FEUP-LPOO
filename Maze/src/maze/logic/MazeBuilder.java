package maze.logic;

import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder {
	
	private static final char path = ' ';
	private static final char wall = 'X';
	private static final char exit = 'S';
	private char[][] maze;
	private char[][] suportMaze;
	private Stack<Position> pathCell;
	private char guideCell;
	private Position exitCell;

	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		// TODO  É PRECISO FAZER ESTE MÉTODO
		
		maze = new char[size][size];
		
		for (int i = 0; i < maze.length; i++){
			for (int j = 0; j < maze[i].length; j++){
				if (i%2 == 0 || j%2==0)
					maze[i][j] = wall;
				else
					maze[i][j] = path;
			}
		}
		
		suportMaze = new char[(size-1)/2][(size-1)/2];
		
		for (int i = 0; i < suportMaze.length; i++){
			for (int j = 0; j < suportMaze[i].length; j++){
				suportMaze[i][j]='.';
			}
		}
		
		return maze;
	}
	
	// O labirinto está completo quando todas as células vazias estão visitadas
	// (stack fica vazia)
	
	public void setExit(){
		Random rand = new Random();
		int randNumber = rand.nextInt(4);
		
		switch(randNumber){
		case 0: // Top border
			break;
		case 1: // Bottom border
			break;
		case 2: // Left Border
			break;
		case 3: // Right Border
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
