package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import maze.logic.Position;
import maze.logic.Dragon;
import maze.logic.Maze;
import maze.logic.Maze.Direction;

@SuppressWarnings("serial")
public class MazeGame extends JPanel {

	private Image hero;
	private Image heroArmed;
	private Image dragon;
	private Image dragonSleeping;
	private Image sword;
	private Image wall;
	private Image wallGrass;
	private Image path;
	private Image pathShadow;
	private Image exit;
	private Image exitSemiOpen;
	private Image exitOpen;
	private Image background;

	private JFrame frame;
	private Maze maze;
	private Maze.Direction direction = Maze.Direction.DOWN;
	private boolean changeDirection = false;
	private int frameCounter = 0;
	private int currentFrame;
	private int maximumFrame = 4;
	private boolean showBackImage = true;

	private int x=0, y=0, width, height;

	public MazeGame(){
		loadImages();
		repaint();
	}

	public MazeGame(JFrame gameFrame, Maze.Mode mode, int mazeSize, int numDragons, int w, int h) {	

		try{
			maze = new Maze(mode, numDragons, mazeSize);
			width = w/maze.getGameBoard().getBoard().length;
			height = h/maze.getGameBoard().getBoard().length;
			frame = gameFrame;
			currentFrame = 0;
			showBackImage = false;
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		this.setBounds((int)(600-mazeSize * width)/3, 41, mazeSize * width, mazeSize*height);
		loadImages();
		setFocusable(true);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {

				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					if (direction != Maze.Direction.LEFT){
						changeDirection = true;
						frameCounter = 0;
					}

					else
						changeDirection = false;
					direction = Maze.Direction.LEFT;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.LEFT);
					}

					repaint();
					break;

				case KeyEvent.VK_RIGHT:
					if (direction != Maze.Direction.RIGHT){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.RIGHT;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.RIGHT);
					}
					repaint();
					break;

				case KeyEvent.VK_UP:
					if (direction != Maze.Direction.UP){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.UP;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.UP);
					}
					repaint();
					break;

				case KeyEvent.VK_DOWN:
					if (direction != Maze.Direction.DOWN){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.DOWN;
					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.DOWN);
					}
					repaint();
					break;
				}				

				if (!maze.isRunning()){
					repaint();
					showBackImage = true;
					setFocusable(false);
					if (!maze.getHero().isAlive())					
						JOptionPane.showMessageDialog(frame, "Game Over!");
					else
						JOptionPane.showMessageDialog(frame, "You Win!");

					repaint();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
	}


	public MazeGame(Maze.Mode mode, char[][] tempMaze, int w, int h) {	

		try{
			maze = new Maze(tempMaze,mode);
			width = w/maze.getGameBoard().getBoard().length;
			height = h/maze.getGameBoard().getBoard().length;
			currentFrame = 0;
			showBackImage = false;
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		this.setBounds((int)(600-maze.getGameBoard().getBoard().length * width)/3, 41, maze.getGameBoard().getBoard().length * width, maze.getGameBoard().getBoard().length*height);
		loadImages();
		setFocusable(true);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {

				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					if (direction != Maze.Direction.LEFT){
						changeDirection = true;
						frameCounter = 0;
					}

					else
						changeDirection = false;
					direction = Maze.Direction.LEFT;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.LEFT);
					}

					repaint();
					break;

				case KeyEvent.VK_RIGHT:
					if (direction != Maze.Direction.RIGHT){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.RIGHT;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.RIGHT);
					}
					repaint();
					break;

				case KeyEvent.VK_UP:
					if (direction != Maze.Direction.UP){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.UP;

					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.UP);
					}
					repaint();
					break;

				case KeyEvent.VK_DOWN:
					if (direction != Maze.Direction.DOWN){
						changeDirection = true;
						frameCounter = 0;
					}
					else
						changeDirection = false;
					direction = Maze.Direction.DOWN;
					if (frameCounter == 2){
						frameCounter = 0;
						maze.update(Maze.Direction.DOWN);
					}
					repaint();
					break;
				}				

				if (!maze.isRunning()){
					showBackImage = true;
					setFocusable(false);
					if (!maze.getHero().isAlive())					
						JOptionPane.showMessageDialog(frame, "Game Over!");
					else
						JOptionPane.showMessageDialog(frame, "You Win!");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
	}

	public void loadImages(){

		ImageIcon image;

		image  =  new ImageIcon(this.getClass().getResource("res/hero.png"));
		hero = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/hero_armed.png"));
		heroArmed = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/dragon.png"));
		dragon = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/dragon_sleeping.png"));
		dragonSleeping = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/sword.png"));
		sword = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/wall.png"));
		wall = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/wallGrass.png"));
		wallGrass = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/path.png"));
		path = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/pathShadow.png"));
		pathShadow = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/exit.png"));
		exit = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/exit_semiopen.png"));
		exitSemiOpen = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/exit_open.png"));
		exitOpen = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/background.jpg"));
		background = image.getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (showBackImage)
			g.drawImage(background, 0, 0, 600, 600, 0, 0, background.getWidth(null), background.getHeight(null), null);

		else{
			Position pos = new Position();

			for (int i = 0; i < maze.getGameBoard().getBoard().length; i++){
				for (int j = 0; j < maze.getGameBoard().getBoard()[i].length; j++){
					pos.setX(j);
					pos.setY(i);

					// Wall
					if (maze.getGameBoard().getBoard()[i][j] == Maze.Token.WALL.getSymbol())
						if (i == maze.getGameBoard().getBoard().length-1)
							g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wall.getHeight(null), null);
						else if (i < maze.getGameBoard().getBoard().length-1 && maze.getGameBoard().getBoard()[i+1][j]!=Maze.Token.WALL.getSymbol())
							g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wallGrass.getHeight(null), null);
						else
							g.drawImage(wallGrass, x, y, x + width, y + height, 0, 0, wallGrass.getWidth(null), wallGrass.getHeight(null), null);

					// Exit
					else if (maze.getGameBoard().getBoard()[i][j] == Maze.Token.EXIT.getSymbol())
						if (maze.isMazeOpen())
							g.drawImage(exitOpen, x, y, x + width, y + height, 0, 0, exitOpen.getWidth(null), exitOpen.getHeight(null), null);
						else if (!maze.hasDragonsAlive())
							g.drawImage(exitSemiOpen, x, y, x + width, y + height, 0, 0, exitSemiOpen.getWidth(null), exitSemiOpen.getHeight(null), null);
						else						
							g.drawImage(exit, x, y, x + width, y + height, 0, 0, exit.getWidth(null), exit.getHeight(null), null);

					// Path
					else if (maze.getGameBoard().getBoard()[i][j-1] == 'X') // Displays shadow on one side
						g.drawImage(pathShadow, x, y, x + width, y + height, 0, 0, pathShadow.getWidth(null), pathShadow.getHeight(null), null);
					else
						g.drawImage(path, x, y, x + width, y + height, 0, 0, path.getWidth(null), path.getHeight(null), null);

					// Hero
					if (maze.getHero().getPos().equals(pos))
						if (maze.getHero().isArmed())
							if (direction == Direction.DOWN)
								drawHero(g, heroArmed, x, y-height/2, j, i);
							else if (direction == Direction.RIGHT)
								drawHero(g, heroArmed, x-width/2, y, j, i);
							else
								drawHero(g, heroArmed, x, y, j, i);
						else{
							if (direction == Direction.DOWN)
								drawHero(g, hero, x, y-height/2, j, i);
							else if (direction == Direction.RIGHT)
								drawHero(g, hero, x-width/2, y, j, i);
							else
								drawHero(g, hero, x, y, j, i);
						}							

					// Sword
					else if (maze.getSword().getPos().equals(pos) && !maze.getSword().isPicked())
						g.drawImage(sword, x, y, x + width, y + height, 0, 0, sword.getWidth(null), sword.getHeight(null), null);

					// Dragon(s)
					else if(maze.checkDragon(pos) instanceof Dragon && maze.checkDragon(pos).isAlive()){
						if (maze.checkDragon(pos).isSleeping())
							g.drawImage(dragonSleeping, x, y, x + width, y + height, 0, 0, dragonSleeping.getWidth(null), dragonSleeping.getHeight(null), null);
						else
							g.drawImage(dragon, x, y, x + width, y + height, 0, 0, dragon.getWidth(null), dragon.getHeight(null), null);
					}				

					x+=width;
				}

				x = 0;
				y+=height;
			}		

			x = 0;
			y = 0;
		}		
	}

	public void drawHero(Graphics g, Image sprite, int i, int j, int posx, int posy){

		if (changeDirection == true)
			currentFrame = 0;		

		if (currentFrame > maximumFrame)
			currentFrame = 0;

		switch(direction){
		case UP:
			if(maze.getGameBoard().getBoard()[posy-1][posx] == Maze.Token.WALL.getSymbol() || currentFrame == 0)
				g.drawImage(sprite, i, j, i + width, j + height, 0, 68, 34, 102, null);
			else				
				g.drawImage(sprite, i, j-frameCounter*height/2, i + width, j + height-frameCounter*height/2, currentFrame * 34, 68, 34 + currentFrame * 34, 102, null);
			break;
		case DOWN:
			if(maze.getGameBoard().getBoard()[posy+1][posx] == Maze.Token.WALL.getSymbol() || currentFrame == 0)
				g.drawImage(sprite, i, j+height/2, i + width, j + height+height/2, 0, 102, 34, 136, null);
			else
				g.drawImage(sprite, i, j+frameCounter*height/2, i + width, j+height+frameCounter*height/2, currentFrame * 34, 102, 34 + currentFrame * 34, 136, null);
			break;
		case LEFT:
			if(maze.getGameBoard().getBoard()[posy][posx-1] == Maze.Token.WALL.getSymbol() || currentFrame == 0)
				g.drawImage(sprite, i, j, i + width, j + height, 0, 0, 34, 34, null);
			else
				g.drawImage(sprite, i-frameCounter*width/2, j, i + width-frameCounter*width/2, j + height, currentFrame * 34, 0, 34 + currentFrame * 34, 34, null);
			break;
		case RIGHT:
			if(maze.getGameBoard().getBoard()[posy][posx+1] == Maze.Token.WALL.getSymbol() || currentFrame == 0)
				g.drawImage(sprite, i+width/2, j, i + width+width/2, j + height, 0, 34, 34, 68, null);
			else
				g.drawImage(sprite, i+frameCounter*width/2, j, i+width+frameCounter*width/2,j + height, currentFrame * 34, 34, 34 + currentFrame * 34, 68, null);
			break;
		}

		frameCounter++;
		currentFrame++;		
	}
	
	public Maze getMaze(){
		return maze;
	}
}