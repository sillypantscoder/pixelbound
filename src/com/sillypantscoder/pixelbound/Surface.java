package com.sillypantscoder.pixelbound;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Surface {
	public BufferedImage img;
	public Surface(int width, int height, Color color) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.fill(color);
	}
	public Surface(BufferedImage image) {
		img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(image, 0, 0, new DummyImageObserver());
		g2d.dispose();
	}
	public void fill(Color color) {
		Graphics2D graphics = img.createGraphics();
		graphics.setPaint(color);
		graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
		graphics.dispose();
	}
	public void blit(Surface other, int x, int y) {
		Graphics2D g2d = img.createGraphics();
		g2d.drawImage(other.img, x, y, new DummyImageObserver());
		g2d.dispose();
	}
	public int get_width() {
		return img.getWidth();
	}
	public int get_height() {
		return img.getHeight();
	}
	public Surface copy() {
		Surface r = new Surface(get_width(), get_height(), Color.BLACK);
		r.blit(this, 0, 0);
		return r;
	}
	public void set_at(int x, int y, Color color) {
		img.setRGB(x, y, color.getRGB());
	}
	public Color get_at(int x, int y) {
		return new Color(img.getRGB(x, y));
	}
	public void drawLine(Color color, int x1, int y1, int x2, int y2, int thickness) {
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(color);
		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);
		g2d.drawLine(x1, y1, x2, y2);
		g2d.dispose();
	}
	public void drawRect(Color color, int x, int y, int width, int height) {
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(x, y, width, height);
		g2d.dispose();
	}
	public void writeToFile(String filename) throws IOException {
		File outputfile = new File(filename);
		ImageIO.write(img, "jpg", outputfile);
	}
}
