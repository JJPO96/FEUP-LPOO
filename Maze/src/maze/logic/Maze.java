package maze.logic;

public class Maze {

	private Labirinto lab;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean running;
	private boolean win;

	public Maze(){
		this.lab = new Labirinto();
		this.hero = new Hero(1, 1, 'H');
		this.dragon = new Dragon(1, 3, 'D');
		this.sword = new Sword(1, 8, 'E');
		this.running = false;
		this.setWin(false);
	};
	
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
		if (hero.pos.equals(sword.pos))
			if (!sword.isPicked()){
				hero.setArmed();
				sword.setPicked();
			}
	}
	
	public void moveHero(char input){

		switch (input){
		case 'L':
			if(!lab.checkCollision(hero.getPos().getX()-1, hero.getPos().getY()))
				hero.pos.x = hero.pos.x-1;
			break;
		case 'R':
			if(!lab.checkCollision(hero.getPos().getX()+1, hero.getPos().getY()))
				hero.pos.x = hero.pos.x+1;
			break;
		case 'U':
			if(!lab.checkCollision(hero.getPos().getX(), hero.getPos().getY()-1))
				hero.pos.y = hero.pos.y-1;
			break;
		case 'B':
			if(!lab.checkCollision(hero.getPos().getX(), hero.getPos().getY()+1))
				hero.pos.y = hero.pos.y+1;
			break;
		default:
			break;
		}				
	}
		
	public Hero getHero(){
		return hero;
	}
	
	public Dragon getDragon(){
		return dragon;
	}
	
	public Sword getSword(){
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
