package uo.cpm.p3.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class mainWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblMcDonalds;
	private JLabel lblProducts;
	private JLabel lblUnits;
	private JSpinner spUnits;
	private JButton btnAdd;
	private JComboBox cbProducts;
	private JLabel lblOrderPrice;
	private JTextField txtPrice;
	private JButton btnNext;
	private JButton btnCancel;
	
	private RegisterForm rf= null; 
	private McDonalds mcDonalds = null; 
	private JLabel lblDiscount;
	private JButton btnRemove;
	private JMenuBar menuBar;
	private JMenu mnOrderMenu;
	private JMenuItem mntmNew;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;
	private JLabel lblProductPicture;
	private JButton btnHamburgers;
	private JButton btnDrinks;
	private JButton btnSides;
	private JButton btnDesserts;
	private JScrollPane scrollPane;
	private JTextArea orderArea;
	private JLabel lblOrder;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	
	
	
	public void initialize() {
		
		
		if(rf != null) { 
			if(rf.getConfirm()!=null) { 
				rf.getConfirm().dispose();
			}
			rf.dispose();
		}
		
		
		orderArea.setText("");
		mcDonalds.initOrder();
		cbProducts.setModel(new DefaultComboBoxModel(mcDonalds.getMenuProducts()));
		cbProducts.setSelectedIndex(0);
		spUnits.setValue(1);
		txtPrice.setText("");
		btnNext.setEnabled(false);
		lblDiscount.setVisible(false);
		
	}
	
	private void showProductPicture() {
		
		String file="/uo/cpm/p3/ui/img/"+((Product)cbProducts.getSelectedItem()).getCode()+".png";
		this.getLblProductPicture().setIcon(new ImageIcon(mainWindow.class.getResource(file)));
		
	}
	
	
	
	private boolean checkExit() {
		if(JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit and cancel the order?")==JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	public McDonalds getMcDonalds() {
		return mcDonalds;
	}
	
	
	public mainWindow() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(checkExit()) {
					System.exit(0);
				}
			}
		});
		
		
		
		this.mcDonalds=new McDonalds(); 
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonald's");
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		setBounds(100, 100, 869, 644);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblMcDonalds());
		contentPane.add(getLblProducts());
		contentPane.add(getLblUnits());
		contentPane.add(getSpUnits());
		contentPane.add(getBtnAdd());
		contentPane.add(getCbProducts());
		contentPane.add(getLblOrderPrice());
		contentPane.add(getTxtPrice());
		contentPane.add(getBtnNext());
		contentPane.add(getBtnCancel());
		
		
		
		
		getRootPane().setDefaultButton(btnNext); 
		contentPane.add(getLblDiscount());
		contentPane.add(getBtnRemove());
		contentPane.add(getLblProductPicture());
		contentPane.add(getBtnHamburgers());
		contentPane.add(getBtnDrinks());
		contentPane.add(getBtnSides());
		contentPane.add(getBtnDesserts());
		contentPane.add(getScrollPane());
		contentPane.add(getLblOrder());
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
			lblLogo.setBounds(135, 44, 183, 124);
		}
		return lblLogo;
	}
	private JLabel getLblMcDonalds() {
		if (lblMcDonalds == null) {
			lblMcDonalds = new JLabel("McDonald's");
			lblMcDonalds.setFont(new Font("Tahoma", Font.BOLD, 46));
			lblMcDonalds.setBounds(319, 70, 284, 112);
		}
		return lblMcDonalds;
	}
	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setDisplayedMnemonic('P');
			lblProducts.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblProducts.setBounds(205, 249, 69, 14);
		}
		return lblProducts;
	}
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('U');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUnits.setBounds(493, 249, 46, 14);
		}
		return lblUnits;
	}
	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnits.setBounds(493, 274, 46, 33);
		}
		return spUnits;
	}
	
	
	
	
	
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product selectedItem = (Product) cbProducts.getSelectedItem();
					Integer units = (Integer) spUnits.getValue();
					mcDonalds.addToOrder(selectedItem, units);
					txtPrice.setText(String.format("%.2f",mcDonalds.getOrderTotal()));
					btnNext.setEnabled(true); //enabled if there is something added to order, to avoid empty order
					
					orderArea.setText(mcDonalds.getOrderList());
					///scrollPane.setVisible(true);
					
					if(mcDonalds.discount()){
						lblDiscount.setVisible(true);
					}
				}
				
			});
			
			
			btnAdd.setMnemonic('A');
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.GREEN);
			btnAdd.setBounds(586, 273, 90, 34);
			
			
		}
		return btnAdd;
	}
	
	
	
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			
			
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product selectedItem = (Product) cbProducts.getSelectedItem();
					Integer units = (Integer) spUnits.getValue();
					mcDonalds.removeFromOrder(selectedItem, units);
					txtPrice.setText(String.format("%.2f",mcDonalds.getOrderTotal()));
					orderArea.setText(mcDonalds.getOrderList());
					if((float)mcDonalds.getOrderTotal()==0) {
						btnNext.setEnabled(false);
						
					}
					if(!mcDonalds.discount()){
						lblDiscount.setVisible(false);
					}
					
				}
			});
			btnRemove.setMnemonic('A');
			btnRemove.setForeground(Color.WHITE);
			btnRemove.setBackground(Color.RED);
			btnRemove.setBounds(686, 273, 90, 34);
		}
		return btnRemove;
	}
	
	
	private JComboBox getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox();
			cbProducts.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					 
					spUnits.setValue(1);
					
					
					
					
					
					showProductPicture();
					
				}
			});
			cbProducts.setBounds(206, 273, 262, 34);
			cbProducts.setModel(new DefaultComboBoxModel(mcDonalds.getMenuProducts()));
			
			
			
			

			showProductPicture();
		}
		return cbProducts;
	}
	private JLabel getLblOrderPrice() {
		if (lblOrderPrice == null) {
			lblOrderPrice = new JLabel("Order Price:");
			lblOrderPrice.setLabelFor(getTxtPrice());
			lblOrderPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblOrderPrice.setBounds(491, 395, 90, 14);
		}
		return lblOrderPrice;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setToolTipText("Total order price");
			txtPrice.setEnabled(false);
			txtPrice.setBounds(491, 420, 148, 34);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setMnemonic('N');
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					showCustomerInfoDialog();
				}
			});
			btnNext.setForeground(Color.WHITE);
			btnNext.setBackground(Color.GREEN);
			btnNext.setBounds(537, 524, 108, 33);
		}
		return btnNext;
	}
	
	
	private void showCustomerInfoDialog() { 
		rf = new RegisterForm(this);
		// IMPORTANT
		rf.setModal(true);
		
		rf.setLocationRelativeTo(contentPane);
		rf.setVisible(true);
	}
	
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						initialize(); 
				
				}
			});
			btnCancel.setMnemonic('C');
			btnCancel.setBackground(Color.RED);
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBounds(668, 524, 108, 33);
			
			
		}
		return btnCancel;
	}
	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/HappyOffer22_23.png")));
			lblDiscount.setBounds(630, 346, 213, 124);
			lblDiscount.setVisible(false);
		}
		return lblDiscount;
	}

	
	
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnOrderMenu());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnOrderMenu() {
		if (mnOrderMenu == null) {
			mnOrderMenu = new JMenu("Order");
			mnOrderMenu.setMnemonic('O');
			mnOrderMenu.add(getMntmNew());
			mnOrderMenu.add(getSeparator());
			mnOrderMenu.add(getMntmExit());
		}
		return mnOrderMenu;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("New ");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			mntmNew.setMnemonic('N');
			mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNew;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(JOptionPane.showConfirmDialog(contentPane, "Are you sure?")==JOptionPane.YES_OPTION) 
					{
						System.exit(0);
					}
				}
			});
			mntmExit.setMnemonic('E');
		}
		return mntmExit;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setMnemonic('t');
		}
		return mntmContents;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('A');
		}
		return mntmAbout;
	}
	private JLabel getLblProductPicture() {
		if (lblProductPicture == null) {
			lblProductPicture = new JLabel("");
			lblProductPicture.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductPicture.setBounds(243, 318, 191, 152);
		}
		return lblProductPicture;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	private void setMenuByType(String category) {
		//cbProducts.removeAllItems();
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbProducts.getModel();
	//	model.removeAllElements();	
		
		cbProducts.setModel(new DefaultComboBoxModel(mcDonalds.getProductsByType(category)));
		cbProducts.setSelectedIndex(0);
			}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	private JButton getBtnHamburgers() {
		if (btnHamburgers == null) {
			btnHamburgers = new JButton("Hamburger");
			btnHamburgers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					setMenuByType("Burger");
				}
			});
			btnHamburgers.setContentAreaFilled(false);
			btnHamburgers.setHorizontalTextPosition(SwingConstants.CENTER);
			btnHamburgers.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnHamburgers.setForeground(Color.BLACK);
			btnHamburgers.setBackground(Color.WHITE);
			btnHamburgers.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/Burguer.png")));
			btnHamburgers.setBounds(16, 26, 117, 112);
		}
		return btnHamburgers;
	}
	private JButton getBtnDrinks() {
		if (btnDrinks == null) {
			btnDrinks = new JButton("Drink");
			btnDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					setMenuByType("Drink");
				}
			});
			
			
			btnDrinks.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDrinks.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnDrinks.setContentAreaFilled(false);
			btnDrinks.setForeground(Color.BLACK);
			btnDrinks.setBackground(Color.WHITE);
			btnDrinks.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/Drink.png")));
			btnDrinks.setBounds(16, 161, 117, 112);
		}
		return btnDrinks;
	}
	private JButton getBtnSides() {
		if (btnSides == null) {
			btnSides = new JButton("Side");
			btnSides.setContentAreaFilled(false);
			btnSides.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSides.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSides.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/Sides.png")));
			btnSides.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					setMenuByType("Side");
				}
			});
			
			btnSides.setBounds(16, 297, 117, 112);
		}
		return btnSides;
	}
	private JButton getBtnDesserts() {
		if (btnDesserts == null) {
			btnDesserts = new JButton("Dessert");
			btnDesserts.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDesserts.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnDesserts.setContentAreaFilled(false);
			btnDesserts.setBackground(Color.WHITE);
			btnDesserts.setForeground(Color.BLACK);
			btnDesserts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					setMenuByType("Dessert");
				}
			});
			
			btnDesserts.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/Dessert.png")));
			btnDesserts.setBounds(16, 432, 117, 112);
		}
		return btnDesserts;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(630, 70, 204, 139);
			scrollPane.setViewportView(getOrderArea());
			orderArea.setDisabledTextColor(Color.WHITE);
			
			
			setVisible(false);
		}
		return scrollPane;
	}
	
	
	private JTextArea getOrderArea() {
		if (orderArea == null) {
			orderArea = new JTextArea();
			orderArea.setEditable(false);
			orderArea.setDisabledTextColor(Color.WHITE);
			
			
		}
		return orderArea;
	}
	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel("");
			lblOrder.setIcon(new ImageIcon(mainWindow.class.getResource("/uo/cpm/p3/ui/img/order.png")));
			lblOrder.setLabelFor(getOrderArea());
			lblOrder.setToolTipText("Order");
			lblOrder.setBounds(668, 26, 110, 37);
		}
		return lblOrder;
	}
}
