package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;

public class GameLauncherGUI {

	private JFrame frmTitulo;
	private JTextField textDim;
	private JTextField textNrDrag;
	private Maze maze;
	private static int numDragonsAlive = 0;
	private static int dragons;
	private static int status;
	private JTextField textField;
	private static final String youWin = "\n\n\n\n\n\n\n\n\n\n\n              ___  _ ____  _       _      _  _      _ \n              \\  \\///  _ \\/ \\ /\\  / \\  /|/ \\/ \\  /|/ \\\n               \\  / | / \\|| | ||  | |  ||| || |\\ ||| |\n               / /  | \\_/|| \\_/|  | |/\\||| || | \\||\\_/\n              /_/   \\____/\\____/  \\_/  \\|\\_/\\_/  \\|(_)";
	private static final String gameOver = "\n\n\n\n\n\n\n\n\n\n\n        _____ ____  _      _____   ____  _     _____ ____  _ \n       /  __//  _ \\/ \\__/|/  __/  /  _ \\/ \\ |\\/  __//  __\\/ \\\n       | |  _| / \\|| |\\/|||  \\    | / \\|| | //|  \\  |  \\/|| |\n       | |_//| |-||| |  |||  /_   | \\_/|| \\// |  /_ |    /\\_/\n       \\____\\\\_/ \\|\\_/  \\|\\____\\  \\____/\\__/  \\____\\\\_/\\_\\(_)";
	private static final double minSize = 5;
	private static final int maxSize = 27;
	private static final int minNumDragons = 1;
	private static final double maxFontSize = 54;
	
	/**
	 *Direction buttons auxiliary function
	 * Input Values:
	 * 	0 - up
	 * 	1 - down 
	 * 	2 - left 
	 * 	3 - right
	 * 
	 * Output values
	 * 	0 - error //
	 * 	1 - hero moved, nothing happened //
	 *  2 - hero/dragon moved, hero is dead //
	 *  3 - hero moved, is armed //
	 *  4 - hero moved, killed a dragon //
	 *  5 - hero moved, killed all dragons, exit open //
	 *  6 - hero exited the maze //
	 */
	
	/**
	 * Get the current status of the Game
	 * 
	 * @param numDragonsAlive
	 * @return the status
	 */
	public int getStatus(int numDragonsAlive){ 
		
		int tempnumDragonsAlive = 0;
		for(Dragon dr : maze.getDragons())
			if(dr.isAlive())
				tempnumDragonsAlive++;
	
		if(maze.hasDragonsAlive()){
			 if(maze.getHero().isAlive()){
				 if(maze.getHero().isArmed())
					 return 3;
				 else if(tempnumDragonsAlive < numDragonsAlive){
					 numDragonsAlive -= 1; 
					 return 4;
				 }else
					 return 1;
			 }else
				 return 2;
				 
		}else{
			if(maze.isMazeOpen())
				return 6;
			else
				return 5;
		}
	}
	
	public void updateStatus(TextArea textArea, Button btnUp, Button btnDown, Button btnLeft, Button btnRight,Label lblGameState, JProgressBar progressBar){
		if(status == 6){
			lblGameState.setText("YOU WON!!!!");
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
			textArea.setText(youWin);
			textArea.setBackground(new Color(30,140,30));
		}else if(status == 5){
			lblGameState.setText("All dragons killed!!!!");
			textArea.setText(maze.toString());
		}else if(status == 4){
			lblGameState.setText("You killed one dragon!");
			numDragonsAlive--;
			textArea.setText(maze.toString());
		}else if(status == 3){
			lblGameState.setText("You are armed!");
			textField.setBackground(new Color(30,30,140));
			textField.setText("Armed");
			textArea.setText(maze.toString());
		}else if(status == 2){
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
			btnLeft.setEnabled(false);
			btnRight.setEnabled(false);
			textField.setBackground(new Color(140,30,30));
			textField.setText("Dead");
			lblGameState.setText("GAME OVER");
			textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
			textArea.setText(gameOver);
			textArea.setBackground(new Color(140,30,30));
		}else
			textArea.setText(maze.toString());
		
		int temp = 0;
		for(Dragon x : maze.getDragons())
			if(x.isAlive())
				temp++; 
		double z = (dragons - temp);
		int y = (int)(100*(z/dragons));
		progressBar.setValue(y);
	}
	
	/**
	 * Calculates the font size to use to print the Maze
	 * 
	 * @param mazeSize
	 * @return the font size
	 */
	public static int calculateFontSize(int mazeSize){
		return (int) (maxFontSize/ (mazeSize/minSize));
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameLauncherGUI window = new GameLauncherGUI();
					window.frmTitulo.setVisible(true);
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
	public GameLauncherGUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitulo = new JFrame();
		frmTitulo.setTitle("Maze");
		frmTitulo.setResizable(false);
		frmTitulo.setBounds(100, 100, 630, 530);
		frmTitulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitulo.getContentPane().setLayout(null);
			
		// Buttons and fields declarations
		JLabel lblMazeDim = new JLabel("Maze Dimension");
		lblMazeDim.setBounds(35, 25, 135, 25);
		frmTitulo.getContentPane().add(lblMazeDim);
		
		JLabel lblNumberDragons = new JLabel("Number of Dragons");
		lblNumberDragons.setBounds(35, 65, 135, 25);
		frmTitulo.getContentPane().add(lblNumberDragons);
		
		JLabel lblTypeDrag = new JLabel("Type of Dragons");
		lblTypeDrag.setBounds(35, 105, 135, 25);
		frmTitulo.getContentPane().add(lblTypeDrag);
		
		JLabel lblGameState = new JLabel("Create your Maze.");
		lblGameState.setBounds(390, 417, 220, 20);
		lblGameState.setHorizontalAlignment(SwingConstants.CENTER);
		frmTitulo.getContentPane().add(lblGameState);
		
		textDim = new JTextField();
		textDim.setBounds(175, 25, 125, 20);
		textDim.setHorizontalAlignment(SwingConstants.CENTER);
		textDim.setText("11");
		frmTitulo.getContentPane().add(textDim);
		textDim.setColumns(10);
		
		textNrDrag = new JTextField();
		textNrDrag.setBounds(175, 65, 125, 20);
		textNrDrag.setHorizontalAlignment(SwingConstants.CENTER);
		textNrDrag.setText("1");
		textNrDrag.setColumns(10);
		frmTitulo.getContentPane().add(textNrDrag);
		
		JComboBox<String> modeSlct = new JComboBox<String>();
		modeSlct.setBounds(175, 105, 125, 20);
		modeSlct.addItem("Static");
		modeSlct.addItem("Moving");
		modeSlct.addItem("Moving and Sleepy");
		frmTitulo.getContentPane().add(modeSlct);
	
		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 152, 345, 328);
		textArea.setEditable(false);
		frmTitulo.getContentPane().add(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 80, 150, 35);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JProgressBar progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBounds(420, 320, 150, 20);
		frmTitulo.getContentPane().add(progressBar);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(0, 0, 0));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(420, 364, 150, 20);
		frmTitulo.getContentPane().add(textField);
		textField.setColumns(10);
		frmTitulo.getContentPane().add(btnExit);
	
		JButton btnUp = new JButton("UP");
		btnUp.setBounds(446, 152, 100, 25);
		btnUp.setEnabled(false);
		frmTitulo.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.setBounds(446, 252, 100, 25);
		btnDown.setEnabled(false);
		frmTitulo.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("LEFT");
		btnLeft.setBounds(390, 202, 100, 25);
		btnLeft.setEnabled(false);
		frmTitulo.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("RIGHT");
		btnRight.setBounds(510, 202, 100, 25);
		btnRight.setEnabled(false);
		frmTitulo.getContentPane().add(btnRight);

		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.update(Direction.UP);
				status = getStatus(numDragonsAlive);
				
				if(status == 6){
					lblGameState.setText("YOU WIN!!!!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(youWin);
					textArea.setBackground(new Color(30,140,30));
				}else if(status == 5){
					lblGameState.setText("All dragons killed!!!!");
					textArea.setText(maze.toString());
				}else if(status == 4){
					lblGameState.setText("You killed one dragon!");
					numDragonsAlive--;
					textArea.setText(maze.toString());
				}else if(status == 3){
					lblGameState.setText("You are armed!");
					textField.setBackground(new Color(30,30,140));
					textField.setText("Armed");
					textArea.setText(maze.toString());
				}else if(status == 2){
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textField.setBackground(new Color(140,30,30));
					textField.setText("Dead");
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					lblGameState.setText("Game Over");
					textArea.setText(gameOver);
					textArea.setBackground(new Color(140,30,30));
				}else
					textArea.setText(maze.toString());	
				int temp = 0;
				for(Dragon x : maze.getDragons())
					if(x.isAlive())
						temp++; 
				double z = (dragons - temp);
				int y = (int) (100*(z/dragons));
				progressBar.setValue(y);
			}
		});
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.update(Direction.DOWN);
				status = getStatus(numDragonsAlive);

				if(status == 6){
					lblGameState.setText("YOU WON!!!!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(youWin);
					textArea.setBackground(new Color(30,140,30));
				}else if(status == 5){
					lblGameState.setText("All dragons killed!!!!");
					textArea.setText(maze.toString());
				}else if(status == 4){
					lblGameState.setText("You killed one dragon!");
					numDragonsAlive--;
					textArea.setText(maze.toString());
				}else if(status == 3){
					lblGameState.setText("You are armed!");
					textField.setBackground(new Color(30,30,140));
					textField.setText("Armed");
					textArea.setText(maze.toString());
				}else if(status == 2){
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textField.setBackground(new Color(140,30,30));
					textField.setText("Dead");
					lblGameState.setText("GAME OVER");
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(gameOver);
					textArea.setBackground(new Color(140,30,30));
				}else
					textArea.setText(maze.toString());
				int temp = 0;
				for(Dragon x : maze.getDragons())
					if(x.isAlive())
						temp++; 
				double z = (dragons - temp);
				int y = (int) (100*(z/dragons));
				progressBar.setValue(y);
			}
		});
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.update(Direction.LEFT);
				status = getStatus(numDragonsAlive);

				if(status == 6){
					lblGameState.setText("YOU WON!!!!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(youWin);
					textArea.setBackground(new Color(30,140,30));
				}else if(status == 5){
					lblGameState.setText("All dragons killed!!!!");
					textArea.setText(maze.toString());
				}else if(status == 4){
					lblGameState.setText("You killed one dragon!");
					numDragonsAlive--;
					textArea.setText(maze.toString());
				}else if(status == 3){
					lblGameState.setText("You are armed!");
					textField.setBackground(new Color(30,30,140));
					textField.setText("Armed");
					textArea.setText(maze.toString());
				}else if(status == 2){
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textField.setBackground(new Color(140,30,30));
					textField.setText("Dead");
					lblGameState.setText("GAME OVER");
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(gameOver);
					textArea.setBackground(new Color(140,30,30));
				}else
					textArea.setText(maze.toString());
				int temp = 0;
				for(Dragon x : maze.getDragons())
					if(x.isAlive())
						temp++; 
				double z = (dragons - temp);
				int y = (int) (100*(z/dragons));
				progressBar.setValue(y);
			}
		});
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.update(Direction.RIGHT);
				status = getStatus(numDragonsAlive);
				
				if(status == 6){
					lblGameState.setText("YOU WON!!!!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(youWin);
					textArea.setBackground(new Color(30,140,30));
				}else if(status == 5){
					lblGameState.setText("All dragons killed!!!!");
					textArea.setText(maze.toString());
				}else if(status == 4){
					lblGameState.setText("You killed one dragon!");
					numDragonsAlive--;
					textArea.setText(maze.toString());
				}else if(status == 3){
					lblGameState.setText("You are armed!");
					textField.setBackground(new Color(30,30,140));
					textField.setText("Armed");
					textArea.setText(maze.toString());
				}else if(status == 2){
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					textField.setBackground(new Color(140,30,30));
					textField.setText("Dead");
					lblGameState.setText("GAME OVER");
					textArea.setFont(new Font("Courier New", Font.PLAIN, 9));
					textArea.setText(gameOver);
					textArea.setBackground(new Color(140,30,30));
				}else
					textArea.setText(maze.toString());
				
				int temp = 0;
				for(Dragon x : maze.getDragons())
					if(x.isAlive())
						temp++; 
				double z = (dragons - temp);
				int y = (int)(100*(z/dragons));
				progressBar.setValue(y);
				
			}
		});
		
		JButton btnNewMaze = new JButton("New Maze");
		btnNewMaze.setBounds(420, 35, 150, 35);
		btnNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textArea.setBackground(new Color(255,255,255));
				textField.setBackground(new Color(30,140,30));
				textField.setText("Alive");
				
				Mode mode = null;
				String strMode = modeSlct.getSelectedItem().toString();
				if(strMode == "Static")
					mode = Mode.STATIC;
				else if(strMode == "Moving")
					mode = Mode.MOVING;
				else if(strMode == "Moving and Sleepy")
					mode = Mode.MOVINGSLEEPING;
				
				int dim = Integer.parseInt(textDim.getText());
				dragons = Integer.parseInt(textNrDrag.getText());
				numDragonsAlive = dragons;
				
				// Creating a new Maze
				maze = new Maze(mode, dragons, dim);
				
				textArea.setFont(new Font("Courier New", Font.PLAIN, calculateFontSize(dim)));
				frmTitulo.getContentPane().add(textArea);
				textArea.setText(maze.toString());
				
				// Enabling command buttons
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				
				progressBar.setValue(0);
				lblGameState.setText("Game Running");				
			}
		});
		frmTitulo.getContentPane().add(btnNewMaze);

	}
}
