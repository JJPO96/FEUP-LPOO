package maze.logic;

public class Objeto {
	protected Coordenadas coords;
	protected char simbolo;
	
	Objeto(){
		coords = new Coordenadas(0, 0);
	}
	Objeto(int a, int b, char simbolo){
		coords = new Coordenadas(a, b);
		this.simbolo = simbolo;
	}
	
	public void setSimbolo(char simbolo){
		this.simbolo = simbolo;
	}
	
	public Coordenadas getCoords(){
		return coords;
	}
	
	public char getSimbolo(){
		return simbolo;
	}		
}