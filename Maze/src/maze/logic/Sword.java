package maze.logic;

/**
 * Class that inherits from Gameelement and represents the Maze's Sword
 */
public class Sword extends GameElement{

	private boolean picked = false;
	
	/**
	 * Sword's Constructor
	 * 
	 * @param x coordinate of the Sword
	 * @param y coordinate of the Sword
	 * @param symbol of the Sword
	 */
	public Sword(int x, int y, char symbol) {
		super(x, y, symbol);
	}

	/**
	 * Verifies if the Sword is picked by the Hero
	 * 
	 * @return true if is picked
	 */
	public boolean isPicked() {
		return picked;
	}

	/**
	 * Sets the Sword as picked by the Hero
	 */
	public void setPicked() {
		this.picked = true;
	}
}
