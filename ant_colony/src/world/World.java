package world;

import java.awt.Color;
import java.awt.Graphics;

public class World {
	
	private char[][] map;
	
	private int width, height;
	
	public World(int width, int height) {
		// Generate map of characters
		map = new char[width][height];
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i <= width-1; i++) {
			for(int j = 0; j <= height-1; j++) {
				if(map[i][j] == 'r') {
					g.setColor(Color.red);
					g.fillRect(i, j, 1, 1);
					g.setColor(Color.black);
				}
			}
		}
	}
	
	public void setLocation(int x, int y, char val) {
		map[x][y] = val;
	}
	
	public char getLocation(int x, int y) {
		return map[x][y];
	}

}
