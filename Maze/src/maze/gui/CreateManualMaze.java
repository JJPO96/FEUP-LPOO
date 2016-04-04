package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import maze.logic.Maze;
import maze.logic.Maze.Mode;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateManualMaze {

	public JFrame frmMazeCreator;
	private JComboBox comboBox;
	private MazeCreator panel;
	private static final int gamePanelSize = 600;

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public CreateManualMaze(int dragons,int size) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize(dragons,size);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int dragons,int size) {
		frmMazeCreator = new JFrame();
		frmMazeCreator.setBackground(Color.WHITE);
		frmMazeCreator.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncherMaze.class.getResource("/maze/gui/res/dragon.png")));
		frmMazeCreator.setTitle("Maze Creator");
		frmMazeCreator.setBounds(100, 100, 600, 670);
		frmMazeCreator.setPreferredSize(new Dimension(600, 670));		
		frmMazeCreator.setResizable(false);
		frmMazeCreator.setLocationRelativeTo(null);
		frmMazeCreator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMazeCreator.getContentPane().setLayout(null);
		
		panel = new MazeCreator(gamePanelSize, gamePanelSize,dragons,size,this);
		panel.setBounds(1, 41, gamePanelSize, gamePanelSize);
		panel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
		frmMazeCreator.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.repaint();
		panel.setFocusable(true);
		
		
		
		JLabel lblTypeOfElement = new JLabel("Type of Element");
		lblTypeOfElement.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		lblTypeOfElement.setBounds(140, 5, 90, 30);
		frmMazeCreator.getContentPane().add(lblTypeOfElement);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Wall", "Exit", "Hero", "Dragon", "Sword"}));
		comboBox.setBounds(225, 5, 90, 30);
		frmMazeCreator.getContentPane().add(comboBox);
		panel.requestFocus();
		
		JButton btnSaveMaze = new JButton("Save Maze");
		btnSaveMaze.setFont(new Font("Tempus Sans ITC", Font.BOLD, 9));
		btnSaveMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSaveMaze.setBounds(35, 5, 90, 30);
		frmMazeCreator.getContentPane().add(btnSaveMaze);
		
	}
	
	public int getElement(){
		return comboBox.getSelectedIndex();
	}
}
