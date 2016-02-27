package maze.logic;

public class Maze {
	
	private Labirinto lab;
	private Heroi hero;
	private Dragao dragon;
	private Espada sword;
	private boolean running;

	public Maze(){
		this.lab = new Labirinto();
		this.hero = new Heroi(1, 1, 'H');
		this.dragon = new Dragao(1, 3, 'D');
		this.sword = new Espada(1, 8, 'E');
		this.running = false;
	};
	
	/**
	 * Coloca os elementos do jogo nas posições iniciais do tabuleiro
	 */
	public void init(){
		lab.setObjeto(hero);
		lab.setObjeto(dragon);
		lab.setObjeto(sword);
		this.running = true;
	}
	
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
