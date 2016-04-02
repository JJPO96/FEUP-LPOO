package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.logic.Position;
import maze.logic.Dragon;
import maze.logic.Maze;

public class MazeCreator extends JPanel  /*implements MouseListener, MouseMotionListener */{

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
	private Image exitOpen;
	private Image background;
	private int x=0, y=0, width, height;
	private char tempMaze[][]; 

	
	public MazeCreator(int w, int h, int dragons, int size) {	// TODO - MUDAR CONSTRUTOR	
		/*
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		 */
		tempMaze = new char[size][size];
		for (int i = 0; i < tempMaze.length; i++){
			for (int j = 0; j < tempMaze[i].length; j++){
				tempMaze[i][j] = 'X';
			}
		}
		width = w/tempMaze.length;
		height = h/tempMaze.length;
		loadImages();
		setFocusable(true);

		repaint();
	}
	
/*
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
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

		image  =  new ImageIcon(this.getClass().getResource("res/exit_open.png"));
		exitOpen = image.getImage();
		
		image  =  new ImageIcon(this.getClass().getResource("res/background.jpg"));
		background = image.getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < tempMaze.length; i++){
			for (int j = 0; j < tempMaze[i].length; j++){

				// Wall
				if (tempMaze[i][j] == Maze.Token.WALL.getSymbol())
					if (i == tempMaze.length-1)
						g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wall.getHeight(null), null);
					else if (i < tempMaze.length-1 && tempMaze[i+1][j]!=Maze.Token.WALL.getSymbol())
						g.drawImage(wall, x, y, x + width, y + height, 0, 0, wall.getWidth(null), wallGrass.getHeight(null), null);
					else
						g.drawImage(wallGrass, x, y, x + width, y + height, 0, 0, wallGrass.getWidth(null), wallGrass.getHeight(null), null);

				// Exit
				else if (tempMaze[i][j] == Maze.Token.EXIT.getSymbol())						
					g.drawImage(exit, x, y, x + width, y + height, 0, 0, exit.getWidth(null), exit.getHeight(null), null);

				// Path
				else if (tempMaze[i][j-1] == 'X') // Displays shadow on one side
					g.drawImage(pathShadow, x, y, x + width, y + height, 0, 0, pathShadow.getWidth(null), pathShadow.getHeight(null), null);
				else
					g.drawImage(path, x, y, x + width, y + height, 0, 0, path.getWidth(null), path.getHeight(null), null);

				// Hero
				if (tempMaze[i][j] == Maze.Token.HERO.getSymbol())
					g.drawImage(hero, x, y, x + width, y + height, 0, 0, hero.getWidth(null), hero.getHeight(null), null);

				// Sword
				else if (tempMaze[i][j] == Maze.Token.SWORD.getSymbol())
					g.drawImage(sword, x, y, x + width, y + height, 0, 0, sword.getWidth(null), sword.getHeight(null), null);

				// Dragon(s)
				else if(tempMaze[i][j] == Maze.Token.DRAGON.getSymbol())
					g.drawImage(dragon, x, y, x + width, y + height, 0, 0, dragon.getWidth(null), dragon.getHeight(null), null);

				x+=width;
			}

			x = 0;
			y+=height;
		}		

		x = 0;
		y =5;
	}

}