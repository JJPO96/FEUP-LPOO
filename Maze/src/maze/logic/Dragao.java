package maze.logic;

public class Dragao extends Objeto {
	private boolean sleeping;
	private boolean alive;
	
	public Dragao(){
		super();
		this.simbolo = 'D';
		this.sleeping = false;
	}
	
	public Dragao(int x, int y, char simbolo) {
		super(x, y, simbolo);
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleep) {
		this.sleeping = sleep;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
}
