package com.sillypantscoder.pixelbound;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	public int playerX;
	public int playerY;
	public Surface level;
	public Game() {
		playerX = 0;
		playerY = 0;
		level = new Surface(100, 100, Color.WHITE);
	}
	public Surface renderFrame(int width, int height, ArrayList<String> keys) {
		// Keys
		if (keys.contains("Up"))    playerY -= 1;
		if (keys.contains("Down"))  playerY += 1;
		if (keys.contains("Left"))  playerX -= 1;
		if (keys.contains("Right")) playerX += 1;
		// Render
		Surface s = new Surface(width, height, Color.WHITE);
		s.blit(level, 0, 0);
		s.drawRect(Color.RED, playerX - 5, playerY - 5, 10, 10);
		return s;
	}
	public void startLevel(String name) {
		try {
			level = ImageHelpers.loadImage(name);
			playerX = level.get_width() / 2;
			playerY = level.get_height() / 2;
		} catch (IOException e) {
		}
	}
}
