package maze.logic;

import maze.logic.Maze.Token;

/**
 * Class that inherits from Gameelement and  represents the Maze's Hero
 */
public class Hero extends GameElement{

	private boolean alive = true;
	private boolean armed = false;
	
	/**
	 * Hero's Constructor
	 * 
	 * @param x coordinate of the Hero
	 * @param y coordinate of the Hero
	 * @param symbol of the Hero
	 */	
	public Hero(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	/**
	 * Sets a new alive's state of the Hero
	 * 
	 * @param live with the new alive's state
	 */
	public void setAlive(boolean live){
		this.alive = live;
	}
	
	/**
	 * Verifies is the Hero is alive
	 * 
	 * @return true if the Hero is alive
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * Sets Hero in armed mode
	 */
	public void setArmed(){
		armed = true;
		symbol = Token.HEROARMED.getSymbol();
	}
	
	/**
	 * Verifies if the Hero is armed
	 * 
	 * @return true if Hero is armed
	 */
	public boolean isArmed(){
		return armed;
	}
}
