package maze.logic;

public class Objeto {
	private int x;
	private int y;
	
	Objeto(){
		x = 0;
		y = 0;
	}
	Objeto(int a, int b){
		x = a;
		y = b;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}	
}