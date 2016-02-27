package maze.logic;

public class Maze {
	
	private enum tipoJogo {Dormir, Nao};
	
	private Labirinto lab;
	private Heroi hero;
	private Dragao dragon;
	private Espada sword;
	private boolean running;

	public Maze(){
		lab = new Labirinto();
		setRunning(true);
	};
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Labirinto getLab(){		
		return lab;
	}
	
	public void update(char input){
		
		//TODO
	}
}
