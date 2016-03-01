package maze.logic;

public class Hero extends GameElement{

	private boolean alive = true;
	private boolean armed = false;
	
	/**
	 * Hero's constructor
	 */
	public Hero(){
		super();
		this.symbol = 'H';
	}
	
	public Hero(int x, int y, char symbol) {
		super(x, y, symbol);
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	/**
	 * Verifies is the Hero is alive
	 * 
	 * @return if Hero is alive
	 */
	public boolean isAlive(){
		return alive;
	}
	
	/**
	 * Sets hero is armed mode
	 */
	public void setArmed(){
		armed = true;
		symbol = 'A';
	}
	
	/**
	 * Verifies if the Hero is armed
	 * 
	 * @return if hero is armed
	 */
	public boolean isArmed(){
		return armed;
	}
}
