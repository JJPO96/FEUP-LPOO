package maze.logic;

public class Dragao extends Objeto {
	private boolean dormir;
	
	public Dragao(){
		super();
		setDormir(false);
	}
	
	public Dragao(int x, int y) {
		super(x, y);
	}

	public boolean isDormir() {
		return dormir;
	}

	public void setDormir(boolean dormir) {
		this.dormir = dormir;
	}
}
