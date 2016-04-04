package maze.logic;

import maze.logic.Maze.Token;

/**
 * Class to handle the Game's board
 */
public class Board {

	private char[][] board = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X','H',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','D','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X','E','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};
	
	/**
	 * Board's constructor
	 */
	public Board(){};
	
	public void setBoard(char[][] board){
		this.board = board;
	}
	
	/**
	 * Return the maze's board
	 * 
	 * @return board
	 */
	public char[][] getBoard(){
		return board;
	}
	
	/**
	 * Verifies if an element collides with some element of the maze
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @return true if collides with some element of the maze
	 */
	public boolean checkCollision(int x, int y){				
		return board[y][x] != Token.PATH.getSymbol();
	}
	
	/**
	 * Verifies if an element of the game collides with the maze's wall
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @return true if collides with the maze's wall
	 */
	public boolean checkWall(int x, int y){				
		return board[y][x] == Token.WALL.getSymbol();
	}
	
	/**
	 * Verifies the Exit of the Maze is present at the coordinate parameters
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if Hero has arived to the mazes's exit
	 */
	public boolean checkExit(int x, int y){		
		return board[y][x]== Token.EXIT.getSymbol();
	}
	
	/**
	 * Returns the position of the Exit of the Maze
	 * 
	 * @return position of the Exit
	 */	
	public Position getExitPos(){
		
		Position pos = new Position();
		
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++)
				if (board[i][j] == Token.EXIT.getSymbol()){
					pos.setX(j);
					pos.setY(i);
					
					return pos;
				}
		}
		
		return pos;
	}
}
