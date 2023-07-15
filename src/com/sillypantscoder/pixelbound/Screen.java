package com.sillypantscoder.pixelbound;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Screen extends RepaintingPanel {
	public Game currentGame;
	public Screen() {
		currentGame = new Game();
		currentGame.startLevel("rw5.jpg");
	}
	public void run() {
		super.run();
		try {
			ImageHelpers.setWindowIcon(ImageHelpers.loadImage("rw5.png"), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Surface painter(int width, int height) {
		return currentGame.renderFrame(width, height, keys);
	}
	public void keyPressed(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
