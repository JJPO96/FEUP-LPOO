package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

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
					window.frame.setVisible(true);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JPanel panel = new MazeGame();
		panel.setBounds(0, 0, 594, 600);
		frame.getContentPane().add(panel);

		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBounds(0, 600, 89, 26);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("Save/Load Game");
		button.setBounds(238, 600, 89, 26);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Exit");
		button_1.setBounds(505, 600, 89, 26);
		frame.getContentPane().add(button_1);
	}
}
