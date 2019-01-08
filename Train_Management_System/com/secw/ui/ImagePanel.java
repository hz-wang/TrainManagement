package com.secw.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Title		: ImagePanel.java
 * Description	: This class is to import texture
 * @author Zhang Yufeng, Wang Haozhe
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Image icon = new ImageIcon("resource/titleBackground.jpg").getImage();
		g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
