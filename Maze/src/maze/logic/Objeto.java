package maze.logic;

public class Objeto {
	private int x;
	private int y;
	protected char simbolo;
	
	Objeto(){
		this.x = 0;
		this.y = 0;
	}
	Objeto(int a, int b, char simbolo){
		this.x = a;
		this.y = b;
		this.simbolo = simbolo;
	}
	
	public void setSimbolo(char simbolo){
		this.simbolo = simbolo;
	}
	
	public char getSimbolo(){
		return simbolo;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}	
}