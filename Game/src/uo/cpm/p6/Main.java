package uo.cpm.p6;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p6.service.SpaceInvaders;
import uo.cpm.p6.ui.MainWindow;


public class Main {

	public static void main(String[] args) {
		final SpaceInvaders game = new SpaceInvaders(); //first
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties props = new Properties(); // load library
					props.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");		
					MainWindow frame = new MainWindow(game);
					
					
					frame.setLocationRelativeTo(null);  
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}


