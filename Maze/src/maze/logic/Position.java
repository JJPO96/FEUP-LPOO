package maze.logic;

/**
 * Class that represents a position of an element of the game
 */
public class Position {

	protected int x;
	protected int y;

	/**
	 * Position's Contructor
	 */
	public Position(){
		this.x = 0;
		this.y= 0;
	}
	
	/**
	 * Position's Constructor
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets a new x coordinate
	 * 
	 * @param x coordinate
	 */
	public void setX(int x){
		this.x = x;
	}

	/**
	 * Sets a new y coordinate
	 * 
	 * @param y coordinate
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Returns coordinate x
	 * 
	 * @return coordinate x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns coordinate y
	 * 
	 * @return coordimate y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Updates position
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void updatePos(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Implementation of equals() function for Position objects (compares if 2 object have the same position)
	 * 
	 * @param pos position to be comparade
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
