package maze.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Maze;
import maze.logic.Maze.Mode;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDimension;
	private JTextField textNumDragons;
	
	private int mazeSize;
	private int numberDragons;
	private Maze.Mode mode;

	/**
	 * Create the dialog.
	 */
	public Options() {
		setTitle("Options");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Options.class.getResource("/maze/gui/res/dragon.png")));
		this.setModal(true);
		setBounds(100, 100, 300, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelDimension = new JLabel("Maze Dimension");
		labelDimension.setBounds(10, 21, 109, 14);
		contentPanel.add(labelDimension);
		
		JLabel labelNumDragons = new JLabel("Number of Dragons");
		labelNumDragons.setBounds(10, 78, 109, 14);
		contentPanel.add(labelNumDragons);
		
		JLabel labelTypeDragons = new JLabel("Type of Dragons");
		labelTypeDragons.setBounds(10, 139, 109, 14);
		contentPanel.add(labelTypeDragons);
		
		textDimension = new JTextField();
		textDimension.setHorizontalAlignment(SwingConstants.CENTER);
		textDimension.setText("11");
		mazeSize = Integer.parseInt(textDimension.getText());	
		textDimension.setBounds(129, 18, 144, 20);
		contentPanel.add(textDimension);
		textDimension.setColumns(10);
		
		textNumDragons = new JTextField();
		textNumDragons.setText("1");
		numberDragons = Integer.parseInt(textNumDragons.getText());
		textNumDragons.setHorizontalAlignment(SwingConstants.CENTER);
		textNumDragons.setColumns(10);
		textNumDragons.setBounds(129, 75, 144, 20);
		contentPanel.add(textNumDragons);
		
		JComboBox<String> modeSelect = new JComboBox<String>();
		modeSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Static", "Moving", "Moving and Sleeping"}));
		modeSelect.setSelectedIndex(0);		
		modeSelect.setBounds(129, 136, 144, 20);
		contentPanel.add(modeSelect);
		
		String strMode = modeSelect.getSelectedItem().toString();
		if(strMode == "Static")
			mode = Mode.STATIC;
		else if(strMode == "Moving")
			mode = Mode.MOVING;
		else if(strMode == "Moving and Sleepy")
			mode = Mode.MOVINGSLEEPING;	
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mazeSize = Integer.parseInt(textDimension.getText());
				numberDragons = Integer.parseInt(textNumDragons.getText());
				String strMode = modeSelect.getSelectedItem().toString();
				if(strMode == "Static")
					mode = Mode.STATIC;
				else if(strMode == "Moving")
					mode = Mode.MOVING;
				else if(strMode == "Moving and Sleepy")
					mode = Mode.MOVINGSLEEPING;
				closeDialog();
			}
		});
		btnApply.setBounds(10, 187, 89, 23);
		contentPanel.add(btnApply);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				closeDialog();
			}
		});
		btnBack.setBounds(184, 187, 89, 23);
		contentPanel.add(btnBack);
		
	}

	public int getMazeSize() {
		return mazeSize;
	}

	public int getNumberDragons() {
		return numberDragons;
	}

	public Maze.Mode getMode() {
		return mode;
	}
	
	public void closeDialog(){
		this.dispose();
	}
}
