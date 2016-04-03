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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateManualMaze {

	public JFrame frame;
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
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameLauncherMaze.class.getResource("/maze/gui/res/dragon.png")));
		frame.setTitle("Maze");
		frame.setBounds(100, 100, 600, 670);
		frame.setPreferredSize(new Dimension(600, 670));		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new MazeCreator(gamePanelSize, gamePanelSize,dragons,size,this);
		panel.setBounds(1, 41, gamePanelSize, gamePanelSize);
		panel.setPreferredSize(new Dimension(gamePanelSize, gamePanelSize));
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.repaint();
		panel.setFocusable(true);
		
		
		
		JLabel lblTypeOfElement = new JLabel("Type of Element");
		lblTypeOfElement.setBounds(191, 16, 89, 14);
		frame.getContentPane().add(lblTypeOfElement);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Wall", "Exit", "Hero", "Dragon", "Sword"}));
		comboBox.setBounds(290, 14, 77, 20);
		frame.getContentPane().add(comboBox);
		panel.requestFocus();
		
		JButton btnSaveMaze = new JButton("Save Maze");
		btnSaveMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSaveMaze.setBounds(65, 11, 89, 23);
		frame.getContentPane().add(btnSaveMaze);
		
	}
	
	public int getElement(){
		return comboBox.getSelectedIndex();
	}
}
