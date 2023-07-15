package com.sillypantscoder.pixelbound;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageHelpers {
	public static Surface renderText(int size, String text, Color color) {
		// Measure the text
		Surface measure = new Surface(1, 1, Color.BLACK);
		Graphics2D big = (Graphics2D) measure.img.getGraphics();
		FontMetrics fm = big.getFontMetrics();
		Surface ret = new Surface(fm.stringWidth(text), fm.getHeight(), new Color(0, 0, 0, 0));
		// Draw the text
		try {
			Graphics2D g2d = ret.img.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(color);
			g2d.drawString(text, 0, fm.getAscent());
			g2d.dispose();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		// Finish
		return ret;
	}
	public static Surface loadImage(String filename) throws IOException {
		// Get the file
		File f = AssetLoader.getResource(filename);
		BufferedImage img = ImageIO.read(f);
		return new Surface(img);
	}
	public static void setWindowIcon(Surface icon, RepaintingPanel panel) {
		ImageIcon img = new ImageIcon(icon.img);
		RepaintingPanel.frame.setIconImage(img.getImage());
	}
}
