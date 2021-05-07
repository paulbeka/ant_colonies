package world;

import java.awt.Color;
import java.awt.Graphics;

public class World {
	
	private Tile[][] map;
	
	private int width, height;
	
	public World(int width, int height) {
		// Generate map of characters
		map = new Tile[width][height];
		this.width = width;
		this.height = height;
		for(int i = 0; i < 799; i++) {
			//
		}
		for(int i = 0; i <= width-1; i++) {
			for(int j = 0; j <= height-1; j++) {
				map[i][j] = new Tile();
			}
		}
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i <= width-1; i++) {
			for(int j = 0; j <= height-1; j++) {
				map[i][j].render(g);
			}
		}
	}
	
	public void setLocation(int x, int y, char val) {
		map[x][y].setColor(Color.red);
		map[x][y].setAnt(true);
	}
	
	public void setAnt(int x, int y, boolean val) {
		map[x][y].setAnt(val);
	}
	
	public int getLocationValue(int x, int y) {
		return map[x][y].getValue();
	}

}
