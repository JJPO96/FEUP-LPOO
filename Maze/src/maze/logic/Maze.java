package maze.logic;

public class Maze {

	private Labirinto lab;
	private Heroi hero;
	private Dragao dragon;
	private Espada sword;
	private boolean running;
	private boolean win;

	public Maze(){
		this.lab = new Labirinto();
		this.hero = new Heroi(1, 1, 'H');
		this.dragon = new Dragao(1, 3, 'D');
		this.sword = new Espada(1, 8, 'E');
		this.running = false;
		this.setWin(false);
	};

	/**
	 * Coloca os elementos do jogo nas posições iniciais do tabuleiro
	 */
	public void init(){
		
		// TODO - adicionar modo de jogo escolhido
		
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

	public void checkSword(){
		if (hero.coords.x == sword.coords.x && hero.coords.y == sword.coords.y)
			if (!sword.isPicked()){
				hero.setArmed();
				sword.setPicked();
			}
	}
	
	public void moveHero(char input){

		switch (input){
		case 'e':
			if(!lab.checkCollision(hero.getCoords().getX()-1, hero.getCoords().getY()))
				hero.coords.x = hero.coords.x-1;
			break;
		case 'd':
			if(!lab.checkCollision(hero.getCoords().getX()+1, hero.getCoords().getY()))
				hero.coords.x = hero.coords.x+1;
			break;
		case 'c':
			if(!lab.checkCollision(hero.getCoords().getX(), hero.getCoords().getY()-1))
				hero.coords.y = hero.coords.y-1;
			break;
		case 'b':
			if(!lab.checkCollision(hero.getCoords().getX(), hero.getCoords().getY()+1))
				hero.coords.y = hero.coords.y+1;
			break;
		default:
			break;
		}				
	}
		
	public Heroi getHero(){
		return hero;
	}
	
	public Dragao getDragon(){
		return dragon;
	}
	
	public Espada getSword(){
		return sword;
	}

	public void update(char input){

		//TODO - Implementar checkExit, checkDraon e win condition

		moveHero(input);
		checkSword();

	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}
}
