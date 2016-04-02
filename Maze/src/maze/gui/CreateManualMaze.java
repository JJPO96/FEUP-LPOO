package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import maze.gui.MazeGame;
import maze.logic.Maze;
import maze.logic.Maze.Mode;

public class CreateManualMaze extends JPanel implements MouseListener, MouseMotionListener{

	private JFrame frmCreateNewMaze;
	
	private JFrame frame;
	private MazeGame gamePanel;
	private static final int gamePanelSize = 600;
	private int mazeSize = 11;
	private Mode tempMode = Mode.STATIC;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateManualMaze window = new CreateManualMaze();
					window.frmCreateNewMaze.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public CreateManualMaze() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncherMaze.class.getResource("/maze/gui/res/dragon.png")));
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 670);
		frame.setPreferredSize(new Dimension(600, 670));		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		char[][] tempMaze = new char[mazeSize][mazeSize];
		for(int i = 0;i<mazeSize;i++){
			for(int j = 0;j<mazeSize;j++){
				tempMaze[i][j] = 'X';
			}		
		}
		
		Maze maze = new Maze(tempMaze,tempMode);
		
		gamePanel = new MazeGame(gamePanelSize, gamePanelSize);
		gamePanel.setMaze(maze);
		gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);
		gamePanel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
		frame.getContentPane().add(gamePanel, BorderLayout.SOUTH);
		gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		

		frame.pack();		
		frame.setVisible(true);		
		gamePanel.requestFocus();
	}
	
/*	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncherMaze.class.getResource("/maze/gui/res/dragon.png")));
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 670);
		frame.setPreferredSize(new Dimension(600, 670));		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		char[][] tempMaze = new char[mazeSize][mazeSize];
		for(int i = 0;i<mazeSize;i++){
			for(int j = 0;j<mazeSize;j++){
				tempMaze[i][j] = 'X';
			}		
		}
		
		Maze maze = new Maze(tempMaze,tempMode);
		gamePanel = new MazeGame(gamePanelSize, gamePanelSize);
		gamePanel.setMaze(maze);
		gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);
		gamePanel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
		frame.getContentPane().add(gamePanel, BorderLayout.SOUTH);
		gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		

		frame.pack();		
		frame.setVisible(true);		
		gamePanel.requestFocus();
		
		
		
		
	}
*/
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

}
