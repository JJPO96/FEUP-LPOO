package maze.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import maze.logic.Maze;
import maze.logic.MazeBuilder;

public class MazeCreator extends JPanel  implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Image hero;
	private Image dragon;
	private Image sword;
	private Image wall;
	private Image wallGrass;
	private Image path;
	private Image pathShadow;
	private Image exit;
	private int x=0, y=0, width, height,size;
	private char tempMaze[][]; 

	public MazeCreator(){};
	
	public MazeCreator(int w, int h, int dragons, int size) {	// TODO - MUDAR CONSTRUTOR	
		
		this.addMouseListener(this);
		
		tempMaze = new char[size][size];
		for (int i = 0; i < tempMaze.length; i++){
			for (int j = 0; j < tempMaze[i].length; j++){
				tempMaze[i][j] = 'X';
			}
		}
		width = w/tempMaze.length;
		height = h/tempMaze.length;
		this.size = size;
		loadImages();
		setFocusable(true);
	
		repaint();
	}
	

	public void loadImages(){

		ImageIcon image;

		image  =  new ImageIcon(this.getClass().getResource("res/hero.png"));
		hero = image.getImage();

		image  =  new ImageIcon(this.getClass().getResource("res/dragon.png"));
		dragon = image.getImage();

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
		y = 0;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		float tempX = e.getX();
		float tempY = e.getY();

		int iX = (int) (tempX/width);
		int iY = (int) (tempY/height);
		
		if(!(iX == 0 || iY == 0 || iX == (size-1) || iY == (size-1))){
			if (tempMaze[iY][iX] != 'X')
				tempMaze[iY][iX] = 'X';
			else tempMaze[iY][iX] = ' ';
		}
		System.out.println("X =" + iX);
		System.out.println("Y =" + iY);
		
		repaint();
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

}