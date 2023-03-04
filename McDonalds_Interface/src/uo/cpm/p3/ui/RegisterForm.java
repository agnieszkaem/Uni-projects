package uo.cpm.p3.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class RegisterForm extends JDialog { 

	private JPanel contentPane;
	private JButton btnCancel;
	private JButton btnNext;
	private JPanel panelCustomerData;
	private JPanel panelOrderType;
	private JLabel lblNameSurname;
	private JRadioButton rbOnSite;
	private JRadioButton rbTakeAway;
	private JComboBox comboBox;
	private JLabel lblPass;
	private JLabel lblConfirmPass;
	private JTextField textNameSurname;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JLabel lblBirthdate;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private mainWindow window =null; 
	private ConfirmationDialog confirm=null;
	
	
	
	public ConfirmationDialog getConfirm() {
		return confirm;
	}


	
	
	
	public RegisterForm(mainWindow parent) {
		window = parent;
		
		setTitle("McDonald's: Customer Information Form"); 
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 630, 489);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		contentPane.add(getPanelCustomerData());
		contentPane.add(getPanelOrderType());
		
		getRootPane().setDefaultButton(btnNext); 
	}

	
	
	public mainWindow getWindow() {
		return window;
	}
	
	
	
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.setFont(new Font("Arial", Font.PLAIN, 11));
			
			
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					window.initialize();
				}
			});
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(Color.RED);
			btnCancel.setBounds(441, 387, 145, 37);
		}
		return btnCancel;
	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setMnemonic('N');
			btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
					if(textNameSurname.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "The \"Name and Surname \" should not be empty!","Try again!",JOptionPane.WARNING_MESSAGE);
					}else if(getPasswordField().getPassword().length<8|| getPasswordField2().getPassword().length<8||   !Arrays.equals(getPasswordField().getPassword(), getPasswordField2().getPassword())) {
						JOptionPane.showMessageDialog(null, "The passwords should be the same and have at least 8 signs!","Error!",JOptionPane.ERROR_MESSAGE);
					}else {
						window.getMcDonalds().setTypeToOrder(buttonGroup.getSelection().getActionCommand());
						showConfirmationDialog();
					}
					
					}
			});
			btnNext.setFont(new Font("Arial", Font.PLAIN, 11));
			btnNext.setForeground(Color.WHITE);
			btnNext.setBackground(Color.GREEN);
			
			btnNext.setBounds(286, 387, 145, 37);
		}
		return btnNext;
	}
	
	
	
	private void showConfirmationDialog() { 
		
		confirm = new ConfirmationDialog(this);
		
		confirm .setModal(true); 
		
		confirm.setLocationRelativeTo(contentPane);
		confirm.setVisible(true);
	}
	
	
	private JPanel getPanelCustomerData() {
		if (panelCustomerData == null) {
			panelCustomerData = new JPanel();
			panelCustomerData.setBorder(new TitledBorder(null, "Customer Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCustomerData.setBounds(51, 44, 535, 200);
			panelCustomerData.setLayout(null);
			panelCustomerData.add(getLblNameSurname());
			panelCustomerData.add(getTextNameSurname());
			panelCustomerData.add(getLblBirthdate());
			panelCustomerData.add(getComboBox());
			panelCustomerData.add(getLblPass());
			panelCustomerData.add(getPasswordField());
			panelCustomerData.add(getLblConfirmPass());
			panelCustomerData.add(getPasswordField2());

			
		}
		return panelCustomerData;
	}

	private JLabel getLblNameSurname() {
		if (lblNameSurname == null) {
			lblNameSurname = new JLabel("Name and Surname:");
			lblNameSurname.setDisplayedMnemonic('N');
			lblNameSurname.setLabelFor(lblNameSurname);
			lblNameSurname.setFont(new Font("Arial", Font.PLAIN, 11));
			lblNameSurname.setBounds(50, 24, 153, 28);
		}
		return lblNameSurname;
	}
	
	private JTextField getTextNameSurname() {
		if (textNameSurname == null) {
			textNameSurname = new JTextField();
			textNameSurname.setBounds(210, 24, 236, 28);
			textNameSurname.setColumns(10);
		}
		return textNameSurname;
	}

	private JLabel getLblBirthdate() {
		if (lblBirthdate == null) {
			lblBirthdate = new JLabel("Birthdate:");
			lblBirthdate.setDisplayedMnemonic('B');
			lblBirthdate.setLabelFor(getComboBox());
			lblBirthdate.setFont(new Font("Arial", Font.PLAIN, 11));
			lblBirthdate.setBounds(50, 67, 100, 14);
		}
		return lblBirthdate;
	}
	
	private JComboBox getComboBox() {
		if (comboBox == null) {
			Vector years = new Vector();
			comboBox = new JComboBox();
			comboBox.setBounds(210, 63, 114, 22);
			for(int y=1930;y<=2022;y++) {
				years.add(y);	
			}
			
			comboBox.setModel(new DefaultComboBoxModel(years.toArray()));
		}
		
		return comboBox ;
	}

	
	
	
	private JLabel getLblPass() {
		if (lblPass == null) {
			lblPass = new JLabel("Password:");
			lblPass.setDisplayedMnemonic('P');
			lblPass.setLabelFor(getPasswordField());
			lblPass.setFont(new Font("Arial", Font.PLAIN, 11));
			lblPass.setBounds(50, 103, 100, 14);
		}
		return lblPass;
	}
	
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			passwordField.setBounds(210, 96, 236, 28);
		}
		return passwordField;
	}
	
	private JLabel getLblConfirmPass() {
		if (lblConfirmPass == null) {
			lblConfirmPass = new JLabel("Confirm Password:");
			lblConfirmPass.setLabelFor(getPasswordField2());
			lblConfirmPass.setDisplayedMnemonic('C');
			lblConfirmPass.setFont(new Font("Arial", Font.PLAIN, 11));
			lblConfirmPass.setBounds(50, 150, 114, 14);
		}
		return lblConfirmPass;
	}

	private JPasswordField getPasswordField2() {
		if (passwordField2 == null) {
			passwordField2 = new JPasswordField();
			passwordField2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			passwordField2.setBounds(210, 143, 236, 28);
		}
		return passwordField2;
	}
	
	private JPanel getPanelOrderType() {
		if (panelOrderType == null) {
			panelOrderType = new JPanel();
			panelOrderType.setBorder(new TitledBorder(null, "Order Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelOrderType.setBounds(51, 262, 221, 162);
			panelOrderType.add(getRbOnSite());
			panelOrderType.add(getRbTakeAway());
			
		}
		return panelOrderType;
	}
	private JRadioButton getRbOnSite() {
		if (rbOnSite == null) {
			rbOnSite = new JRadioButton("On site");
			rbOnSite.setMnemonic('O');
			rbOnSite.setSelected(true);
			buttonGroup.add(rbOnSite);
			rbOnSite.setActionCommand(rbOnSite.getText());
		}
		return rbOnSite;
	}
	private JRadioButton getRbTakeAway() {
		if (rbTakeAway == null) {
			rbTakeAway = new JRadioButton("Take away");
			rbTakeAway.setMnemonic('T');
			buttonGroup.add(rbTakeAway);
			rbTakeAway.setActionCommand(rbTakeAway.getText());
		}
		return rbTakeAway;
	}
	
	
	
	
}


