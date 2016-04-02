package maze.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameLauncherMaze {

	private JFrame frame;
	private MazeGame gamePanel;
	private static final int gamePanelSize = 600;
	private JButton btnNewGame;
	private JButton btnCreateGame;
	private JButton btnOptions;
	private JButton btnSaveGame;
	private JButton btnLoadGame;
	private JButton btnExit;

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
		/*frame = new JFrame();
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 670);
		frame.setPreferredSize(new Dimension(600, 670));
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
			
		gamePanel = new MazeGame(gamePanelSize , gamePanelSize );
		gamePanel.setBounds(0, 41, 594 , 600 );
		gamePanel.setPreferredSize(new Dimension(gamePanelSize , gamePanelSize ));
		gamePanel.requestFocus();
		frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		
		
		
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				
			}
		});
		btnNewGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnNewGame.setBounds(1, 0, 98, 40);
		frame.getContentPane().add(btnNewGame);
		
		btnCreateGame = new JButton("Create Game");
		btnCreateGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnCreateGame.setBounds(99, 0, 98, 40);
		frame.getContentPane().add(btnCreateGame);
		
		btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnOptions.setBounds(197, 0, 98, 40);
		frame.getContentPane().add(btnOptions);
		
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnSaveGame.setBounds(295, 0, 98, 40);
		frame.getContentPane().add(btnSaveGame);
		
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnLoadGame.setBounds(393, 0, 98, 40);
		frame.getContentPane().add(btnLoadGame);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO - CRIAR DIALOG A PERGUNTAR SE QUER REALMENTE SAIR
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnExit.setBounds(491, 0, 100, 40);
		frame.getContentPane().add(btnExit);

		frame.pack();
		
		frame.setVisible(true);*/
		
		frame = new JFrame();
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 655);
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gamePanel = new MazeGame(gamePanelSize, gamePanelSize);
		gamePanel.setBounds(0, 0, gamePanelSize, gamePanelSize);
		gamePanel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
		frame.getContentPane().add(gamePanel);

		frame.pack();
		
		frame.setVisible(true);
		
		gamePanel.requestFocus();
	}
}
