package maze.logic;

public class GameElement {

	protected Position pos;
	protected char symbol;
	
	GameElement(){
		pos = new Position(0, 0);
	}
	
	GameElement(int x, int y, char symbol){
		pos = new Position(x, y);
		this.symbol = symbol;
	}
	
	public void setSymbol(char symbol){
		this.symbol = symbol;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public char getSymbol(){
		return symbol;
	}	
}
