package maze.logic;

import java.util.Random;

public class Maze {

	private Board gameBoard;
	private Hero hero;
	private Dragon dragon;
	private Sword sword;
	private boolean running;
	private int mode; // 0 for static dragon,1 for moving dragon and 2 for moving/sleeping dragon

	/**
	 * Maze's construtor
	 */
	public Maze(){
		this.gameBoard = new Board();
		this.hero = new Hero(1, 1, 'H');
		this.dragon = new Dragon(1, 3, 'D');
		this.sword = new Sword(1, 8, 'E');
		this.running = false;
		this.mode = 0;
	};

	/**
	 * Initializes the game in the mode selected
	 */
	public void init(int m){
		this.mode = m;
		
		this.running = true;
	}

	/**
	 * Verifies if the game is still running
	 * 
	 * @return true if is running
	 */
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Board getgameBoard(){		
		return gameBoard;
	}
	
	public boolean isDragonnear(int a, int b){
		
		for (int i = -1; i < 2; i++){
			for (int j = -1; j < 2; j++){
				if (i == 0 && j == 0)
					continue;
				else{
					if (dragon.getPos().getX() == a+i && dragon.getPos().getY()== b+j)
						return true;						
				}
			}
		}
		
		
		return false;
	}

	public void checkSword(){

		if (hero.pos.equals(sword.pos)){
			if (!sword.isPicked()){
				hero.setArmed();
				sword.setPicked();
			}
		}
	}

	/**
	 * Verifies if hero is adjacent to the dragon and if yes, updates Hero condition
	 */
	public void checkDragon(){
		
		if (hero.isArmed())
			dragon.setAlive(false);
		else{
			hero.setAlive(false);
			running = false;
		}
	}

	public boolean checkCollision(int x, int y){
		Position posTemp = new Position(x, y);

		if (gameBoard.checkCollision(x, y) || posTemp.equals(sword.pos))
			return true;
		return false;
	}	

	public void updateHero(int a, int b){
		
		boolean hasDragonnear = isDragonnear(hero.pos.x+a, hero.pos.y+b);

		if(checkCollision(hero.pos.x+a, hero.pos.y+b) || hasDragonnear){			
			if(!gameBoard.checkWall(hero.pos.x+a, hero.pos.y+b)){				
				if(gameBoard.checkExit(hero.pos.x+a, hero.pos.y+b)){
					if(!dragon.isAlive()){
						hero.pos.updatePos(a, b);
						running = false;
					}
				}

				else{
					hero.pos.updatePos(a, b);
					checkSword();
					if (hasDragonnear)
						checkDragon();
				}
			}
		}

		else{
			hero.pos.updatePos(a, b);
		}
	}
	
	public boolean updateDragon(int a, int b){
		if(!gameBoard.checkWall(dragon.pos.x+a, dragon.pos.y+b)&&!dragon.isSleeping()){
			dragon.pos.updatePos(a, b);
			
		}else return false;
		
		boolean hasDragonnear = isDragonnear(hero.pos.x, hero.pos.y);
		
		checkSword();
		if (hasDragonnear)
			checkDragon();
		return true;
	}

	/**
	 * Moves the Hero if posible and updates his state
	 * 
	 * @param input with the direction the Hero will try to move
	 */
	public void moveHero(char input){

		switch (input){
		case 'L':
			updateHero(-1, 0);
			break;
		case 'R':
			updateHero(1, 0);
			break;
		case 'U':
			updateHero(0, -1);
			break;
		case 'D':
			updateHero(0, 1);
			break;
		default:
			break;
		}
	}
	
	public void moveDragon(){
		Random rn = new Random();

		int x; // 0 up, 1 down, 2 left and 3 right
		
		if (this.getMode() == 1){
			x = rn.nextInt(4);
			switch(x){
			case 0:
				updateDragon(-1, 0);
				break;
			case 1:
				updateDragon(1, 0);
				break;
			case 2:
				updateDragon(0, -1);
				break;
			case 3:
				updateDragon(0, 1);
				break;
			default:
				break;
			}
		}else if(this.getMode() == 2){
			if(dragon.isSleeping() == true){
				if(rn.nextInt(2) == 0)
					dragon.setSleeping(false);
				else
					dragon.setSleeping(true);
			}else{
				x = rn.nextInt(4);
				switch(x){
				case 0:
					updateDragon(-1, 0);
					break;
				case 1:
					updateDragon(1, 0);
					break;
				case 2:
					updateDragon(0, -1);
					break;
				case 3:
					updateDragon(0, 1);
					break;
				default:
					break;
				}				
				
				if(rn.nextInt(2) == 0)
					dragon.setSleeping(false);
				else
					dragon.setSleeping(true);
			}
		}
	}

	/**
	 * Returns Hero object of the game
	 * 
	 * @return hero
	 */
	public Hero getHero(){
		return hero;
	}

	/**
	 * Returns Dragon object of the game
	 * 
	 * @return dragon
	 */
	public Dragon getDragon(){
		return dragon;
	}

	/**
	 * Returns Sword object of the game
	 * 
	 * @return sword
	 */
	public Sword getSword(){
		return sword;
	}
	
	public int getMode(){
		return mode;
	}

	/**
	 * Update game state according to the input command received from the user
	 * 
	 * @param input with the direction in which the Hero will try to move
	 */
	public void update(char input){

		//TODO - Implementar as funções de atualização necessarias das alineas seguintes da ficha

		moveHero(input);
		moveDragon();

	}
}
