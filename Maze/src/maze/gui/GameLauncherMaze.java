package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.Color;
import maze.gui.CreateManualMaze;

public class GameLauncherMaze {

	private JFrame frame;
	private JPanel backgroundPanel;
	private MazeGame gamePanel;
	private MazeCreator mazeCreatorPanel;
	private Options options;
	private Image background;
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

		options = new Options();

		// TODO - REMOVER SE NAO USADO
		ImageIcon image;
		image  =  new ImageIcon(this.getClass().getResource("res/background.jpg"));
		background = image.getImage();
		


		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String message = "Do you want to start a new game?";
				int input = JOptionPane.showConfirmDialog(frame, message);

				if (input == JOptionPane.YES_OPTION){
					gamePanel = new MazeGame(frame, options.getMode(), options.getMazeSize(), options.getNumberDragons(), gamePanelSize, gamePanelSize);
					gamePanel.setBounds(1, 41, gamePanelSize, gamePanelSize);
					frame.getContentPane().add(gamePanel, BorderLayout.SOUTH);
					gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					gamePanel.repaint();
					gamePanel.requestFocus();
				}
			}
		});
		btnNewGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnNewGame.setBounds(1, 0, 98, 40);
		frame.getContentPane().add(btnNewGame);

		btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO - DECIDIR ENTRE CRIAR JFRAME À PARTE OU USAR A MESMA


				String message = "Do you want to create a Maze?";
				int input = JOptionPane.showConfirmDialog(frame, message);

				if (input == JOptionPane.YES_OPTION){
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								CreateManualMaze window = new CreateManualMaze(options.getNumberDragons(),options.getMazeSize(),options.getMode());
								window.frmMazeCreator.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}


				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();

				// TODO - REMOVER ESTE CODIGO SE NAO USADO
				/*JPanel mazeCreatorPanel = new MazeCreator(gamePanelSize, gamePanelSize, 1, 11);
				mazeCreatorPanel.setBounds(1, 41, gamePanelSize, gamePanelSize);
				mazeCreatorPanel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
				frame.getContentPane().add(mazeCreatorPanel, BorderLayout.SOUTH);
				mazeCreatorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				mazeCreatorPanel.repaint();
				mazeCreatorPanel.setFocusable(true);
				mazeCreatorPanel.requestFocus();
				mazeCreatorPanel.setVisible(true);*/
			}
		});
		btnCreateGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnCreateGame.setBounds(99, 0, 98, 40);
		frame.getContentPane().add(btnCreateGame);

		btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				options.setLocationRelativeTo(frame);
				options.setVisible(true);

				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnOptions.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnOptions.setBounds(197, 0, 98, 40);
		frame.getContentPane().add(btnOptions);

		btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnSaveGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnSaveGame.setBounds(295, 0, 98, 40);
		frame.getContentPane().add(btnSaveGame);

		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gamePanel != null)
					if (gamePanel.getMaze().isRunning())
						gamePanel.requestFocus();
			}
		});
		btnLoadGame.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnLoadGame.setBounds(393, 0, 98, 40);
		frame.getContentPane().add(btnLoadGame);

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Are you sure you want to quit?";
				int input = JOptionPane.showConfirmDialog(frame, message);

				if (input == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnExit.setBounds(491, 0, 100, 40);
		frame.getContentPane().add(btnExit);		

		frame.pack();		
		frame.setVisible(true);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
