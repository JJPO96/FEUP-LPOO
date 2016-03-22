package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import maze.logic.*;
import maze.logic.Maze.Direction;
import maze.logic.Maze.Mode;

import javax.swing.JTextArea;



public class GameLauncherGUI {

	private JFrame frmTitulo;
	private final Action action = new SwingAction();
	private JTextField textDim;
	private JTextField textNrDrag;
	private Maze maze;
	private int drAlive = 0;
	private int dragons;
	
	
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
	
	public int auxButton(int button,int drAlive){ 
		switch (button){
		case 0:
			maze.update(Direction.UP);
			break;
		case 1:
			maze.update(Direction.DOWN);
			break;
		case 2:
			maze.update(Direction.LEFT);
			break;
		case 3:
			maze.update(Direction.RIGHT);
			break;
		}
		int tempDrAlive = 0;
		for(Dragon dr : maze.getDragons())
			if(dr.isAlive())
				tempDrAlive++;
	
		if(maze.hasDragonsAlive()){
			 if(maze.getHero().isAlive()){
				 if(maze.getHero().isArmed())
					 return 3;
				 else if(tempDrAlive < drAlive)
					 return 4;
				 else
					 return 1;
			 }else
				 return 2;
				 
		}else{
			if(maze.getHero().getPos().getX() == maze.getGameBoard().getExitPos().getX() && maze.getHero().getPos().getY() == maze.getGameBoard().getExitPos().getY())
				return 6;
			else
				return 5;
		}
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
		frmTitulo.setBounds(100, 100, 630, 400);
		frmTitulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitulo.getContentPane().setLayout(null);
		
		//local variables
		
		
		
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
		
		JLabel lblGameState = new JLabel("Crie o seu labirinto.");
		lblGameState.setBounds(386, 287, 220, 20);
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
		
		JComboBox modeSlct = new JComboBox();
		modeSlct.setBounds(175, 105, 125, 20);
		modeSlct.addItem("Static");
		modeSlct.addItem("Moving");
		modeSlct.addItem("Moving and Sleepy");
		frmTitulo.getContentPane().add(modeSlct);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(35, 136, 341, 215);
		textArea.setEditable(false);
		frmTitulo.getContentPane().add(textArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(420, 80, 150, 35);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frmTitulo.getContentPane().add(btnExit);
		
		JButton btnUp = new JButton("UP");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxButton(0,drAlive);
				textArea.setText(maze.toString());
			}
		});
		btnUp.setBounds(445, 180, 100, 25);
		btnUp.setEnabled(false);
		frmTitulo.getContentPane().add(btnUp);
		
		JButton btnDown = new JButton("DOWN");
		btnDown.setBounds(445, 250, 100, 25);
		btnDown.setEnabled(false);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxButton(1,drAlive);
				textArea.setText(maze.toString());
			}
		});
		frmTitulo.getContentPane().add(btnDown);
		
		JButton btnLeft = new JButton("LEFT");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxButton(2,drAlive);
				textArea.setText(maze.toString());
			}
		});
		btnLeft.setBounds(385, 215, 100, 25);
		btnLeft.setEnabled(false);
		frmTitulo.getContentPane().add(btnLeft);
		
		JButton btnRight = new JButton("RIGHT");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				auxButton(3,drAlive);
				textArea.setText(maze.toString());
			}
		});
		btnRight.setBounds(505, 215, 100, 25);
		btnRight.setEnabled(false);
		frmTitulo.getContentPane().add(btnRight);
		
		JButton btnNewMaze = new JButton("New Maze");
		btnNewMaze.setBounds(420, 35, 150, 35);
		btnNewMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO ver maus inputs
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
				drAlive = dragons;
				maze = new Maze(mode,dragons,dim);
				textArea.setText(maze.toString());
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				lblGameState.setText("Game Running");
				
			}
		});
		frmTitulo.getContentPane().add(btnNewMaze);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
