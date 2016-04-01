package maze.exception;

public class InvalidMazeSize extends Exception {
	
	private int min;
	private int max;
	
	public InvalidMazeSize(int minsize, int maxsize) {
		this.min = minsize;
		this.max = maxsize;
	}

	public String getMessage(){
		return "The Maze Size must be an odd number between "+min+" and "+max+"!";
	}
}
