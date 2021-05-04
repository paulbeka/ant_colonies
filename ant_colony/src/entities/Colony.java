package entities;

import java.awt.Color;
import java.awt.Graphics;

public class Colony {
	
	private int x, y;
	private char color;
	
	public Colony(int x, int y) {
		this.x = x;
		this.y = y;
		color = 'r';
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 30, 30);
		g.setColor(Color.black);
	}

}
