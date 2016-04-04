package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import maze.gui.CreateManualMaze;

public class GameLauncherMaze {

	private JFrame frmDragonsMaze;
	private MazeGame gamePanel;
	private Options options;
	private static final int gamePanelSize = 600;
	private CreateManualMaze lastMazeCreated;

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
			@SuppressWarnings("unused")
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
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public GameLauncherMaze() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Frame
		frmDragonsMaze = new JFrame();
		frmDragonsMaze.setBackground(Color.WHITE);
		frmDragonsMaze.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncherMaze.class.getResource("/maze/gui/res/dragon.png")));
		frmDragonsMaze.setTitle("Dragon's Maze");
		frmDragonsMaze.setBounds(0, 0, 600, 660);
		frmDragonsMaze.setPreferredSize(new Dimension(600, 660));		
		frmDragonsMaze.setResizable(false);
		frmDragonsMaze.setLocationRelativeTo(null);
		frmDragonsMaze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDragonsMaze.getContentPane().setLayout(null);
		
		// Game's JPanel
		gamePanel = new MazeGame();
		gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);					
		frmDragonsMaze.getContentPane().add(gamePanel, BorderLayout.CENTER);

		// Options JDialog
		options = new Options();

		btnNewGame = new JButton("New Game");
		btnNewGame.setToolTipText("Start a new game.");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = "Do you want to start a new game?";
				int input = JOptionPane.showConfirmDialog(frmDragonsMaze, message);

				if (input == JOptionPane.YES_OPTION){
					gamePanel = new MazeGame(frmDragonsMaze, options.getMode(), options.getMazeSize(), options.getNumberDragons(), gamePanelSize, gamePanelSize);
					gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);					
					frmDragonsMaze.getContentPane().add(gamePanel, BorderLayout.CENTER);
					gamePanel.repaint();
					gamePanel.requestFocus();
				}
				
				else if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnNewGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnNewGame.setBounds(1, 0, 103, 40);
		frmDragonsMaze.getContentPane().add(btnNewGame);

		btnCreateGame = new JButton("Create Game");
		btnCreateGame.setToolTipText("Create a personalized maze.");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = "Do you want to create a Maze?";
				int input = JOptionPane.showConfirmDialog(frmDragonsMaze, message);

				if (input == JOptionPane.YES_OPTION){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								lastMazeCreated = new CreateManualMaze(options.getNumberDragons(),options.getMazeSize(),options.getMode());
								lastMazeCreated.frmMazeCreator.setVisible(true);
								btnLoadGame.setEnabled(true);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}

				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnCreateGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnCreateGame.setBounds(104, 0, 103, 40);
		frmDragonsMaze.getContentPane().add(btnCreateGame);

		btnOptions = new JButton("Options");
		btnOptions.setToolTipText("Game Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				options.setLocationRelativeTo(frmDragonsMaze);
				options.setVisible(true);

				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnOptions.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnOptions.setBounds(208, 0, 103, 40);
		frmDragonsMaze.getContentPane().add(btnOptions);

		btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnSaveGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnSaveGame.setBounds(311, 0, 103, 40);
		frmDragonsMaze.getContentPane().add(btnSaveGame);

		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastMazeCreated.isSuccesfull()){
					String message = "Do you want to play the last created maze?";
					int input = JOptionPane.showConfirmDialog(frmDragonsMaze, message);

					if (input == JOptionPane.YES_OPTION){
						gamePanel = lastMazeCreated.getMazeCreated();
						gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);					
						frmDragonsMaze.getContentPane().add(gamePanel, BorderLayout.CENTER);
						gamePanel.repaint();
						gamePanel.requestFocus();
					}

					else if (gamePanel != null)
						if (gamePanel.getMaze().isRunning())
							gamePanel.requestFocus();
				}else{
					JOptionPane.showMessageDialog(gamePanel, "No valid maze created");
				}
			}
		});
		btnLoadGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 10));
		btnLoadGame.setBounds(414, 0, 103, 40);
		frmDragonsMaze.getContentPane().add(btnLoadGame);
		btnLoadGame.setEnabled(false);
		
		btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit Maze's Dragon.");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to quit?";
				int input = JOptionPane.showConfirmDialog(frmDragonsMaze, message);

				if (input == JOptionPane.YES_OPTION)
					System.exit(0);
				
				else if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnExit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnExit.setBounds(518, 0, 76, 40);
		frmDragonsMaze.getContentPane().add(btnExit);		

		frmDragonsMaze.pack();		
		frmDragonsMaze.setVisible(true);
	}
	
	public JFrame getFrame(){
		return frmDragonsMaze;
	}
}
