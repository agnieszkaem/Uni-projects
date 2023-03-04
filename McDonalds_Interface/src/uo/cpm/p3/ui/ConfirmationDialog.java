package uo.cpm.p3.ui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmationDialog extends JDialog {
	private JLabel lblOK;
	private JLabel lblMessage;
	private JLabel lblOrderCode;
	private JTextField txtCode;
	private JButton btnFinish;

	private RegisterForm rf =null;
	private JTextField priceTxtField;
	private JLabel lblPrice;
	/**
	 * Launch the application.
	 */
	/*

	/**
	 * Create the dialog.
	 */
	public ConfirmationDialog(RegisterForm parent) {
		rf= parent;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonald's: Order Confirmation");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOK());
		getContentPane().add(getLblMessage());
		getContentPane().add(getLblOrderCode());
		getContentPane().add(getTxtCode());
		getContentPane().add(getBtnFinish());
		getContentPane().add(getPriceTxtField());
		getContentPane().add(getLblPrice());
		setBounds(100, 100, 561, 380);

	}

	private JLabel getLblOK() {
		if (lblOK == null) {
			lblOK = new JLabel("New label");
			lblOK.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/ok.png")));
			lblOK.setBounds(77, 50, 46, 48);
		}
		return lblOK;
	}
	private JLabel getLblMessage() {
		if (lblMessage == null) {
			lblMessage = new JLabel("We are processing your order");
			lblMessage.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblMessage.setBounds(123, 74, 282, 23);
		}
		return lblMessage;
	}
	private JLabel getLblOrderCode() {
		if (lblOrderCode == null) {
			lblOrderCode = new JLabel("The order code is:");
			lblOrderCode.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblOrderCode.setBounds(90, 211, 128, 14);
		}
		return lblOrderCode;
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setEnabled(false);
			txtCode.setBounds(264, 204, 141, 31);
			txtCode.setColumns(10);
			
			txtCode.setText(this.rf.getWindow().getMcDonalds().getOrderCode());
		}
		return txtCode;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.setMnemonic('F');
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					txtCode.getText();
					rf.getWindow().getMcDonalds().saveOrder();
					rf.getWindow().initialize();
					//System.exit(1);
					
				}
			});
			btnFinish.setBackground(Color.GREEN);
			btnFinish.setForeground(Color.WHITE);
			btnFinish.setBounds(218, 278, 113, 31);
			
			
		}
		return btnFinish;
	}
	private JTextField getPriceTxtField() {
		if (priceTxtField == null) {
			priceTxtField = new JTextField();
			priceTxtField.setEnabled(false);
			priceTxtField.setColumns(10);
			priceTxtField.setBounds(264, 141, 141, 31);
			priceTxtField.setText(String.format("%.2f",rf.getWindow().getMcDonalds().getOrderTotal()));
		}
		return priceTxtField;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Final price:");
			lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblPrice.setBounds(90, 149, 128, 14);
		}
		return lblPrice;
	}
}
