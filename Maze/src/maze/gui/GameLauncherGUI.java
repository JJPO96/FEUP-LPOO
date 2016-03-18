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
import javax.swing.JTextArea;

public class GameLauncherGUI {

	private JFrame frmTitulo;
	private final Action action = new SwingAction();
	private JTextField textFieldDimensoes;
	private JTextField textFieldNrDragoes;

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
	 */
	public GameLauncherGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTitulo = new JFrame();
		frmTitulo.setTitle("Jogo do Labirinto");
		frmTitulo.setResizable(false);
		frmTitulo.setBounds(100, 100, 629, 358);
		frmTitulo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTitulo.getContentPane().setLayout(null);
		
		JLabel lblDimensoDoLabirinto = new JLabel("Dimens\u00E3o do Labirinto");
		lblDimensoDoLabirinto.setBounds(35, 25, 135, 25);
		frmTitulo.getContentPane().add(lblDimensoDoLabirinto);
		
		JLabel label = new JLabel("N\u00FAmero de Drag\u00F5es");
		label.setBounds(35, 65, 135, 25);
		frmTitulo.getContentPane().add(label);
		
		JLabel lblTipoDeDrages = new JLabel("Tipo de Drag\u00F5es");
		lblTipoDeDrages.setBounds(35, 105, 135, 25);
		frmTitulo.getContentPane().add(lblTipoDeDrages);
		
		JLabel lblGameState = new JLabel("Crie o seu labirinto.");
		lblGameState.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameState.setBounds(386, 287, 220, 20);
		frmTitulo.getContentPane().add(lblGameState);
		
		textFieldDimensoes = new JTextField();
		textFieldDimensoes.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDimensoes.setText("11");
		textFieldDimensoes.setBounds(175, 25, 110, 20);
		frmTitulo.getContentPane().add(textFieldDimensoes);
		textFieldDimensoes.setColumns(10);
		
		textFieldNrDragoes = new JTextField();
		textFieldNrDragoes.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNrDragoes.setText("1");
		textFieldNrDragoes.setColumns(10);
		textFieldNrDragoes.setBounds(175, 65, 110, 20);
		frmTitulo.getContentPane().add(textFieldNrDragoes);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Imóveis");
		comboBox.addItem("Móveis");
		comboBox.addItem("Móveis c/ sono");
		comboBox.setBounds(175, 105, 110, 20);
		frmTitulo.getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(35, 136, 341, 182);
		frmTitulo.getContentPane().add(textArea);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(425, 80, 150, 35);
		frmTitulo.getContentPane().add(btnSair);
		
		JButton btnCima = new JButton("CIMA");
		btnCima.setEnabled(false);
		btnCima.setBounds(445, 180, 100, 25);
		frmTitulo.getContentPane().add(btnCima);
		
		JButton btnBaixo = new JButton("BAIXO");
		btnBaixo.setEnabled(false);
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaixo.setBounds(445, 250, 100, 25);
		frmTitulo.getContentPane().add(btnBaixo);
		
		JButton btnEsq = new JButton("ESQUERDA");
		btnEsq.setEnabled(false);
		btnEsq.setBounds(385, 215, 100, 25);
		frmTitulo.getContentPane().add(btnEsq);
		
		JButton btnDir = new JButton("DIREITA");
		btnDir.setEnabled(false);
		btnDir.setBounds(505, 215, 100, 25);
		frmTitulo.getContentPane().add(btnDir);
		
		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCima.setEnabled(true);
				btnBaixo.setEnabled(true);
				btnEsq.setEnabled(true);
				btnDir.setEnabled(true);
			}
		});
		btnGerarNovoLabirinto.setBounds(425, 35, 150, 35);
		frmTitulo.getContentPane().add(btnGerarNovoLabirinto);

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
