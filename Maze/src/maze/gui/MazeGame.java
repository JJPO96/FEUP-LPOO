package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Maze;

public class MazeGame extends JPanel {
	
	private Maze maze;
	private BufferedImage hero;
	// TODO REMOVE BACK
	private BufferedImage back;
	private int x=10, y=10, width=28, height=28;
	
	public MazeGame() {
		try {
			hero =  ImageIO.read(new File("res//hero.png"));
			back =  ImageIO.read(new File("res//back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			maze = new Maze(Maze.Mode.STATIC, 1, 19);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//x = e.getX();
				//y = e.getY();
				//repaint();				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		});
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("x=" + x);
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					x--; 
					break;
					
				case KeyEvent.VK_RIGHT: 
					x++; 
					//System.out.println("x=" + x);
					break;

				case KeyEvent.VK_UP: 
					y--; 
					break;

				case KeyEvent.VK_DOWN: 
					y++; 
					break;
				}
				
				repaint();				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int w = width;
		int h = height;
		
		// TODO Remover a imagem background - está a ser usada para testar print de imagens com fundo transparente
		g.drawImage(back, 0,0, 400, 400, 0, 0, back.getWidth(), back.getHeight(), null);
		
		for (int i = 0; i < 30; i++){
			for (int j = 0; j < 30; j++){
				g.drawImage(hero, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				x+=width;
			}
			
			x = 10;
			y+=height;
		}
		
		x = 10;
		y = 10;
	}

}