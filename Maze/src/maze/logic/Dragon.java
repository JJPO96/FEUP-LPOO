package maze.logic;

import maze.logic.Maze.Token;

/**
 * Class that inherits from Gameelement and  represents the Maze's Dragons
 */
public class Dragon extends GameElement{

	private boolean sleeping;
	private boolean alive = true;
	
	/**
	 * GameElement's Constructor
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @param symbol of the element
	 */
	public Dragon(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	/**
	 * Verifies if the Dragon is sleeping
	 * 
	 * @return true if the Dragon is sleeping
	 */
	public boolean isSleeping() {
		return sleeping;
	}

	/**
	 * Sets the Dragons sleeping/wakes up
	 * 
	 * @param sleep to change Dragon sleep state
	 */
	public void setSleeping(boolean sleep) {
		this.sleeping = sleep;
		
		if (sleeping)
			this.symbol = Token.DRAGONSLEEP.getSymbol();
		else
			this.symbol = Token.DRAGON.getSymbol();
	}

	/**
	 * Verifies if the Dragons is alive
	 * 
	 * @return true if the dragon is alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Sets Dragon's live state
	 * 
	 * @param alive to change Dragon alive state
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
