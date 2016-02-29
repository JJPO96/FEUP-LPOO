package maze.logic;

public class Heroi extends Objeto {
	
	private boolean alive = true;
	private boolean armed = false;
	
	public Heroi(){
		super();
		this.simbolo = 'H';
	}
	
	public Heroi(int x, int y, char simbolo) {
		super(x, y, simbolo);
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean getAlive(){
		return alive;
	}
	
	public void setArmed(){
		armed = true;
		simbolo = 'A';
	}
	
	public boolean isArmed(){
		return armed;
	}
}
