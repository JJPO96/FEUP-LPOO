package maze.logic;

public class Coordenadas {
	protected int x;
	protected int y;

	public Coordenadas(){
		this.x = 0;
		this.y = 0;
	}

	public Coordenadas(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}




}
