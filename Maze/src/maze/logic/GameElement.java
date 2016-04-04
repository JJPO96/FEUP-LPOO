package maze.logic;

/**
 * Class parent from which the game elements inherit
 */
public class GameElement {

	protected Position pos;
	protected char symbol;
	
	/**
	 * GameElement's Constructor
	 * 
	 * @param x coordinate of the element
	 * @param y coordinate of the element
	 * @param symbol of the element
	 */
	GameElement(int x, int y, char symbol){
		pos = new Position(x, y);
		this.symbol = symbol;
	}
		
	/**
	 * Returns the position of an element of the Game
	 * 	
	 * @return position of an element of the game
	 */
	public Position getPos(){
		return pos;
	}
	
	/**
	 * Returns the symbol of an element of the game 
	 * 
	 * @return the symbol
	 */
	public char getSymbol(){
		return symbol;
	}	
}
