package maze.logic;

/**
 * Maze Builder interface
 */
public interface IMazeBuilder {
	public char[][] buildMaze(int size, int dragons) throws IllegalArgumentException;
}
