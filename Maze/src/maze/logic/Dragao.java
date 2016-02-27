package maze.logic;

public class Dragao extends Objeto {
	private boolean dormir;
	
	public Dragao(){
		super();
		super.setSimbolo('H');
		setDormir(false);
	}
	
	public Dragao(int x, int y, char simbolo) {
		super(x, y, simbolo);
	}

	public boolean isDormir() {
		return dormir;
	}

	public void setDormir(boolean dormir) {
		this.dormir = dormir;
	}
}
