package maze.logic;

public interface IMazeBuilder {
	public char[][] buildMaze(int size, int dragons) throws IllegalArgumentException;
}
