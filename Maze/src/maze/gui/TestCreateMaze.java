package maze.gui;

import java.util.Arrays;

public class TestCreateMaze {

	public static class Point {		
		private int x, y;

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Point(int y, int x) {
			this.x = x;
			this.y = y;
		}

		public boolean adjacentTo(Point p) {
			return Math.abs(p.x - this.x) + Math.abs(p.y - this.y) == 1;
		}
	}

	// a) the maze boundaries must have exactly one exit and everything else walls
	// b) the exit cannot be a corner
	private static boolean checkBoundaries(char [][] m) {
		int countExit = 0;
		int n = m.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
					if (m[i][j] == 'S')
						if ((i == 0 || i == n-1) && (j == 0 || j == n-1))
							return false;
						else
							countExit++;
					else if (m[i][j] != 'X')
						return false;
		return countExit == 1;
	}


	// d) there cannot exist 2x2 (or greater) squares with blanks only 
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only
	private static boolean hasSquare(char[][] maze, char[][] square) {
		for (int i = 0; i < maze.length - square.length; i++)
			for (int j = 0; j < maze.length - square.length; j++) {
				boolean match = true;
				for (int y = 0; y < square.length; y++)
					for (int x = 0; x < square.length; x++) {
						if (maze[i+y][j+x] != square[y][x])
							match = false;
					}
				if (match)
					return true;
			}		
		return false; 
	}

	private static Point findPos(char [][] maze, char c) {
		for (int x = 0; x < maze.length; x++)			
			for (int y = 0; y < maze.length; y++)
				if (maze[y][x] == c)
					return new Point(y, x);
		return null;		
	}

	static char[][] badWalls = {
			{'X', 'X', 'X'},
			{'X', 'X', 'X'},
			{'X', 'X', 'X'}};
	static char[][] badSpaces = {
			{' ', ' '},
			{' ', ' '}};
	static char[][] badDiagonalDown = {
			{'X', ' '},
			{' ', 'X'}};
	static char[][] badDiagonalUp = {
			{' ', 'X'},
			{'X', ' '}};

	// c) there must exist a path between any blank cell and the maze exit 
	private static boolean checkExitReachable(char [][] maze) {
		Point p = findPos(maze, 'S');
		boolean [][] visited = new boolean[maze.length] [maze.length];

		visit(maze, p.getY(), p.getX(), visited);

		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != 'X' && ! visited[i][j] )
					return false;

		return true; 
	}

	private static boolean checkHero(char [][] maze) {
		for( int i = 0; i < maze.length ; i++)
			for( int j = 0; j < maze[i].length ; j++)
				if(maze[i][j] == 'H')
					return true;
		
		return false; 
	}
	
	private static boolean checkSword(char [][] maze) {
		for( int i = 0; i < maze.length ; i++)
			for( int j = 0; j < maze[i].length ; j++)
				if(maze[i][j] == 'E')
					return true;
		
		return false; 
	}
	
	private static boolean checkDragons(char[][] maze, int dragons) {
		int numDragons = 0;
		for( int i = 0; i < maze.length ; i++)
			for( int j = 0; j < maze[i].length ; j++)
				if(maze[i][j] == 'D')
					numDragons++;
		
		if(numDragons == dragons)
			return true;
		
		return false; 
	}
		
	// auxiliary method used by checkExitReachable
	private static void visit(char[][] m, int i, int j, boolean [][] visited) {
		if (i < 0 || i >= m.length || j < 0 || j >= m.length)
			return;
		if (m[i][j] == 'X' || visited[i][j])
			return;
		visited[i][j] = true;
		visit(m, i-1, j, visited);
		visit(m, i+1, j, visited);
		visit(m, i, j-1, visited);
		visit(m, i, j+1, visited);
	}

	public String str(char [][] maze) {
		StringBuilder s = new StringBuilder();
		for (char [] line : maze) {
			s.append(Arrays.toString(line));
			s.append("\n");
		}
		return s.toString();
	}

	public static boolean mazeIsValid(char [][] maze, int dragons) {
		if(checkBoundaries(maze))
			if(checkExitReachable(maze))
				if(checkSword(maze)&&checkHero(maze)&&checkDragons(maze,dragons))
					if(!hasSquare(maze,badWalls))
						return true;
		
		return false;
	}


	
}

