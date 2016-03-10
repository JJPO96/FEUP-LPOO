package maze.logic;

public class Sword extends GameElement{

	private boolean picked = false;
	
	public Sword(int x, int y, char symbol) {
		super(x, y, symbol);
	}

	public boolean isPicked() {
		return picked;
	}

	public void setPicked() {
		this.picked = true;
	}
}
