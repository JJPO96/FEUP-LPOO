package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameLauncherMaze {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameLauncherMaze window = new GameLauncherMaze();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameLauncherMaze() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 655);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeGame panel = new MazeGame();
		panel.setBounds(0, 0, 600, 600);
		panel.setPreferredSize(new Dimension(600, 600));
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		frame.pack();
		
		frame.setVisible(true);
		
		panel.requestFocus();
	}

}
