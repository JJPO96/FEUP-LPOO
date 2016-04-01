package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import maze.logic.Position;
import maze.logic.Dragon;
import maze.logic.Maze;

public class MazeGame extends JPanel {

	private Maze maze;
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
	private int x=0, y=0, width, height;

	public MazeGame() {		

		try{ // TODO - REMOVER O VALOR DE 600, PARA O MESMO SER RECEBIDO DIRETAMENTE DO JPANEL
			maze = new Maze(Maze.Mode.STATIC, 1, 41);
			width = 600/maze.getGameBoard().getBoard().length;
			height = 600/maze.getGameBoard().getBoard().length;
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		loadImages();

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					maze.update(Maze.Direction.LEFT);
					break;

				case KeyEvent.VK_RIGHT: 
					maze.update(Maze.Direction.RIGHT);
					break;

				case KeyEvent.VK_UP: 
					maze.update(Maze.Direction.UP); 
					break;

				case KeyEvent.VK_DOWN: 
					maze.update(Maze.Direction.DOWN);
					break;
				}

				repaint();				
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
			
			image  =  new ImageIcon(this.getClass().getResource("res/dragon.png"));
			dragon = image.getImage();
			
			image  =  new ImageIcon(this.getClass().getResource("res/dragonsleeping.png"));
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
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Position pos = new Position();

		for (int i = 0; i < maze.getGameBoard().getBoard().length; i++){
			for (int j = 0; j < maze.getGameBoard().getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);
				// TODO -  trocar os simbolos 'X' para getsymbol
				if (maze.getGameBoard().getBoard()[i][j] == 'X')
					if (i == maze.getGameBoard().getBoard().length-1)
						g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wall.getHeight(null), null);
					else if (i < maze.getGameBoard().getBoard().length-1 && maze.getGameBoard().getBoard()[i+1][j]!='X')
						g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wallGrass.getHeight(null), null);
					else
						g.drawImage(wallGrass, x, y, x + width, y + height, 0, 0, wallGrass.getWidth(null), wallGrass.getHeight(null), null);
				else if (maze.getGameBoard().getBoard()[i][j] == 'S')
					g.drawImage(exit, x, y, x + width, y + height, 0, 0, exit.getWidth(null), exit.getHeight(null), null);
				else if (maze.getGameBoard().getBoard()[i][j-1] == 'X') // Display shadow on one side
					g.drawImage(pathShadow, x, y, x + width, y + height, 0, 0, pathShadow.getWidth(null), pathShadow.getHeight(null), null);
				else
					g.drawImage(path, x, y, x + width, y + height, 0, 0, path.getWidth(null), path.getHeight(null), null);

				if (maze.getHero().getPos().equals(pos))
					if (maze.getHero().isArmed())
						g.drawImage(heroArmed, x, y, x + width, y + height, 0, 0, heroArmed.getWidth(null), heroArmed.getHeight(null), null);
					else
						g.drawImage(hero, x, y, x + width, y + height, 0, 0, hero.getWidth(null), hero.getHeight(null), null);

				else if(maze.checkDragon(pos) instanceof Dragon && maze.checkDragon(pos).isAlive()){
					if (pos.equals(maze.getSword().getPos()) && !maze.getSword().isPicked()) // TODO Corrigir sword para dragsword
						g.drawImage(sword, x, y, x + width, y + height, 0, 0, sword.getWidth(null), sword.getHeight(null), null);
					else if (maze.checkDragon(pos).isSleeping())
						g.drawImage(dragonSleeping, x, y, x + width, y + height, 0, 0, dragonSleeping.getWidth(null), dragonSleeping.getHeight(null), null);
					else
						g.drawImage(dragon, x, y, x + width, y + height, 0, 0, dragon.getWidth(null), dragon.getHeight(null), null);
				}				

				else if (maze.getSword().getPos().equals(pos) && !maze.getSword().isPicked())
					g.drawImage(sword, x, y, x + width, y + height, 0, 0, sword.getWidth(null), sword.getHeight(null), null);

				x+=width;
			}

			x = 0;
			y+=height;
		}		

		x = 0;
		y = 0;
	}
}