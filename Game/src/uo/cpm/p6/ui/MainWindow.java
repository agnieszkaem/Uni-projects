package uo.cpm.p6.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	private SpaceInvaders game = null; 
	private JLabel lblSpaceship;
	private JLabel lblScore;
	private JTextField txtScore;
	private JLabel lblEarth;
	private JPanel pnShots;
	private JPanel pnBoard;
	private JButton btnDice;

	
	private MyButtonListener mBL = new MyButtonListener();
	private JMenuBar menuBar;
	private JMenu menuGame;
	private JMenu menuHelp;
	private JMenuItem mnItemGameNew;
	private JSeparator separator;
	private JMenuItem mnItemGameExit;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JMenuItem mnItemHelpContents;
	private JSeparator separator_1;
	private JMenuItem mnItemHelpAbout;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainWindow(SpaceInvaders game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/space.jpg")));
		setTitle("Conquering The Space ");
		setResizable(false);
		this.game=game; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 1010, 420);
		setBounds (100, 100, 1080, 477);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblSpaceship());
		contentPane.add(getLblScore());
		contentPane.add(getTxtScore());
		contentPane.add(getLblEarth());
		contentPane.add(getPnShots());
		contentPane.add(getPnBoard());
		contentPane.add(getBtnDice());
		
		
		initialize(Level.INTERMEDIATE);  
	}
	
	
	private JButton newButton(Integer position) {
		JButton button = new JButton();
		button.setBorder(new LineBorder (Color.GREEN, 3));
		button.setActionCommand(position.toString());
		button.addActionListener(mBL);
		return button;
	}
	
	class MyButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer position = Integer.parseInt(((JButton)e.getSource()).getActionCommand());
			shoot(position);
		}
	}
	
	
	
	
	
	
	public void enableBoard(boolean state) {
		for(Component c : getPnBoard().getComponents()) { 
			
			c.setEnabled(state);
		}
	}
	

	private void prepareBoard(Level level) {
		
		Integer width =0;
		switch (level) {
		case EASY:{width=1010;break;}
		case INTERMEDIATE:{width=815;break;}
		case HARD:{width=610;break;}
		}
		getPnBoard().setBounds(20,250,width,100);
		getPnBoard().setLayout(new GridLayout(1, Board.DIM,4,0)); 
		getPnBoard().removeAll(); 
		for(int i =0; i<Board.DIM ; i++) { 
			getPnBoard().add(newButton(i));
			}
		
		
		validate(); 
		enableBoard(false);


		
	}
	
	private void initialize(Level level) {
		
		
		getPnShots().removeAll();
		this.repaint();
		game.initialize(); 
		getTxtScore().setText(String.valueOf(game.getScore()));
		
		
		getBtnDice().setEnabled(true);
		prepareBoard(level);
		
	}
	
	
	
	
	
	private JLabel newShot() {
		JLabel lblShot = new JLabel();
		lblShot.setIcon(ImageFactory.loadImage("/img/shoot.png"));
		lblShot.setBorder(new LineBorder(Color.green, 1));
		return lblShot;
	}
	
	private void paintShots() {
		for(int i =0; i< game.getShots();i++) {
			getPnShots().add(newShot());
		}
		validate(); 
	}
	
	
	
	private void initGame() {
		game.launch(); 
		paintShots();
		btnDice.setEnabled(false); 
		enableBoard(true);
	}
	
	
	private void removeShot() {
		getPnShots().remove(0);  
		getPnShots().repaint(); 
	}
	
	
	private void paintCell(Integer position) {
		String pictureName = game.getBoard().getPicture(position);
		ImageIcon image = ImageFactory.loadImage(pictureName);
		((JButton)getPnBoard().getComponent(position)).setIcon(image);  
		((JButton)getPnBoard().getComponent(position)).setDisabledIcon(image);
	}
	
	private void showCells() {
		for(int i =0; i<Board.DIM; i++) {
			String pictureName = game.getBoard().getPicture(i);
			ImageIcon image = ImageFactory.loadImage(pictureName);
			((JButton)getPnBoard().getComponent(i)).setIcon(image);  
			((JButton)getPnBoard().getComponent(i)).setDisabledIcon(image);
		}
	}
	private void updateStateOfTheGame(Integer position) {
		getTxtScore().setText(String.valueOf(game.getScore())); 
		removeShot();
		paintCell(position);
		
		if (game.isGameOver()) { 
			int result= game.getResult();
			String message = "";
			switch(result) {
			case 0: message="Invader Found!";
			break;
			case 1: message="A meteorite has destroyed you!"; 
			break;
			case 2: message="You ran out of shots!";
			break;
			}
			
			JOptionPane.showMessageDialog(this, message, "Space Invasion", JOptionPane.INFORMATION_MESSAGE);
			enableBoard(false);
			showCells();
		}
	}
	
	private void shoot(Integer position) {
		game.shoot(position);
		this.updateStateOfTheGame(position);
		
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	
	private JLabel getLblSpaceship() {
		if (lblSpaceship == null) {
			lblSpaceship = new JLabel("");
			lblSpaceship.setToolTipText("Spaceship");
			lblSpaceship.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spaceship.png")));
			lblSpaceship.setBounds(309, 28, 137, 77);
		}
		return lblSpaceship;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setForeground(Color.GREEN);
			lblScore.setFont(new Font("Consolas", Font.PLAIN, 20));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setBounds(666, 28, 86, 21);
		}
		return lblScore;
	}
	private JTextField getTxtScore() {
		if (txtScore == null) {
			txtScore = new JTextField();
			txtScore.setForeground(Color.GREEN);
			txtScore.setHorizontalAlignment(SwingConstants.CENTER);
			txtScore.setText("0");
			txtScore.setFont(new Font("Consolas", Font.PLAIN, 20));
			txtScore.setEditable(false);
			txtScore.setBounds(666, 60, 86, 34);
			txtScore.setColumns(10);
		}
		return txtScore;
	}
	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setToolTipText("Earth image");
			lblEarth.setIcon(new ImageIcon(MainWindow.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(832, 29, 188, 175);
		}
		return lblEarth;
	}
	private JPanel getPnShots() {
		if (pnShots == null) {
			pnShots = new JPanel();
			pnShots.setBounds(148, 124, 490, 80);
			pnShots.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnShots;
	}
	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBorder(new LineBorder(Color.GREEN, 5));
			
			pnBoard.setBounds(43, 224, -4, 145);
			pnBoard.setLayout(new GridLayout(1, 0, 0, 0));
	
			
			
			
		}
		return pnBoard;
	}
	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initGame();  
				}
			});
			btnDice.setDisabledIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setToolTipText("Launch the dice");
			btnDice.setIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setBounds(20, 11, 113, 106);
		}
		return btnDice;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuGame());
			menuBar.add(getMenuHelp());
		}
		return menuBar;
	}
	private JMenu getMenuGame() {
		if (menuGame == null) {
			menuGame = new JMenu("Game");
			menuGame.add(getMnItemGameNew());
			menuGame.add(getSeparator());
			menuGame.add(getMnItemGameExit());
		}
		return menuGame;
	}
	private JMenu getMenuHelp() {
		if (menuHelp == null) {
			menuHelp = new JMenu("Help");
			menuHelp.add(getMenuItem_1());
			menuHelp.add(getSeparator_1());
			menuHelp.add(getMnItemHelpAbout());
		}
		return menuHelp;
	}
	private JMenuItem getMnItemGameNew() {
		if (mnItemGameNew == null) {
			mnItemGameNew = new JMenuItem("New");
			mnItemGameNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize(Level.INTERMEDIATE);
				}
			});
			mnItemGameNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mnItemGameNew;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMnItemGameExit() {
		if (mnItemGameExit == null) {
			mnItemGameExit = new JMenuItem("Exit");
			mnItemGameExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					if(JOptionPane.showConfirmDialog(contentPane, "Are you sure?")==JOptionPane.YES_OPTION) 
					{
						System.exit(0);
					}
				}
			});
		}
		return mnItemGameExit;
	}
	private JMenuItem getMenuItem_1() {
		if (mnItemHelpContents == null) {
			mnItemHelpContents = new JMenuItem("Contents");
		}
		return mnItemHelpContents;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMnItemHelpAbout() {
		if (mnItemHelpAbout == null) {
			mnItemHelpAbout = new JMenuItem("About");
		}
		return mnItemHelpAbout;
	}
}
