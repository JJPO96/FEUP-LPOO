package maze.exception;

@SuppressWarnings("serial")
public class InvalidNumDragons extends Exception {

	private int min;
	private int max;
	
	public InvalidNumDragons(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public String getMessage(){
		
		if (this.min == this.max)
			return "The Number of Dragons for this Maze size must be "+min+"!";
		
		return "The Number of Dragons for this Maze size must be between "+min+" and "+max+"!";
	}
}