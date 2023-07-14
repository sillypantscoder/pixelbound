package com.sillypantscoder.pixelbound;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Screen extends RepaintingPanel {
	public Screen() {}
	public Surface painter(int width, int height) {
		Surface s = new Surface(width, height, Color.WHITE);
		s.drawLine(Color.BLACK, 0, height / 2, width / 2, height / 2, 1);
		s.drawLine(Color.BLACK, width / 2, 0, width / 2, height / 2, 1);
		String k = "Keys:";
		for (int i = 0; i < keys.size(); i++) k += " " + keys.get(i);
		Surface t = ImageHelpers.renderText(30, k, Color.BLACK);
		s.blit(t, 0, 0);
		return s;
	}
	public void keyPressed(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
}
