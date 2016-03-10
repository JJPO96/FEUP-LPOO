package maze.logic;

public class Dragon extends GameElement{

	private boolean sleeping;
	private boolean alive = true;
	
	public Dragon(int x, int y, char symbol) {
		super(x, y, symbol);
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
