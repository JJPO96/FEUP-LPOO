package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameLaunchertesting {

	private JFrame frmGraphicsDemo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameLaunchertesting window = new GameLaunchertesting();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameLaunchertesting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGraphicsDemo = new JFrame();
		frmGraphicsDemo.setTitle("Maze");
		// TODO - REMOVER SE NAO USADO
		//frmGraphicsDemo.setBounds(100, 100, 600, 400);
		//frmGraphicsDemo.setPreferredSize(new Dimension(600, 400));
		frmGraphicsDemo.setResizable(false);
		frmGraphicsDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeGame panel = new MazeGame();
		panel.setBounds(0, 0, 600, 600);
		panel.setPreferredSize(new Dimension(600, 600));
		frmGraphicsDemo.getContentPane().add(panel, BorderLayout.CENTER);

		frmGraphicsDemo.pack();
		
		frmGraphicsDemo.setVisible(true);
		
		panel.requestFocus();
	}

}
