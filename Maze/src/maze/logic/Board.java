package maze.logic;

public class Board {

	private static char path = ' ';
	private static char wall = 'X';
	private static char exit = 'S';
	private char[][] board = {
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'}};
	
	/**
	 * Board's constructor
	 */
	public Board(){};
	
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
		return board[y][x] != path;
	}
	
	/**
	 * Verifies if Hero collides with the maze's wall
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @return true if collides with the maze's wall
	 */
	public boolean checkWall(int x, int y){				
		return board[y][x] == wall;
	}
	
	/**
	 * Verifies if Hero has has arrived to the exit of the maze
	 * 
	 * @param pos position of the Hero in the maze
	 * @return true if Hero has arived to the mazes's exit
	 */
	public boolean checkExit(int x, int y){		
		return board[y][x]== exit;
	}
}
