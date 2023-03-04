package uo.cpm.p6.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import uo.cpm.p6.model.Cell;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Space;

public class ImageFactory {
	//encapsulate to show a picture
	static ImageIcon loadImage(String fqImageName) {
		return new ImageIcon(ImageFactory.class.getResource(fqImageName));
	}

}
