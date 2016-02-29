package maze.logic;

public class Hero extends GameElement{

	private boolean alive = true;
	private boolean armed = false;
	
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
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setArmed(){
		armed = true;
		symbol = 'A';
	}
	
	public boolean isArmed(){
		return armed;
	}
}
