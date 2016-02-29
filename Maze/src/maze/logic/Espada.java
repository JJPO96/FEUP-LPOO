package maze.logic;

public class Espada extends Objeto {
	
	private boolean picked = false;

	public Espada(){
		super();
		this.simbolo = 'E';
	}
	
	public Espada(int x, int y, char simbolo) {
		super(x, y, simbolo);
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked() {
		this.picked = true;
	}
}
