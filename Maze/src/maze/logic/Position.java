package maze.logic;

public class Position {

	protected int x;
	protected int y;

	public Position(){
		this.x = 0;
		this.y = 0;
	}

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	/**
	 * Implementation of equals() function for Position objects (compares if 2 object have the same position)
	 * 
	 * @param coords Object received to be compared
	 * @return returns true if both objects have the same position
	 */
	@Override
	public boolean equals(Object pos){		
		
		// Checks if the argument Object is an instance of Position
		
		if (!(pos instanceof Position)) {
	        return false;
	    }

	    Position posTemp = (Position) pos;
	    
	    // Compares x and y values from both objects
	    return this.x == posTemp.getX() && this.y == posTemp.getY();
	}
}
