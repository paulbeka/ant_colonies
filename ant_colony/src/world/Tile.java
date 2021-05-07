package world;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	
	public final static int FOOD = 10;
	public final static int ANT_TRAIL = 1;
	
	private boolean hasAnt;
	private Color displayColor;
	private int x, y;
	private int value;
	
	public Tile() {
		displayColor = Color.white;
		value = 0;
		hasAnt = false;
	}
	
	public void render(Graphics g) {
		g.setColor(displayColor);
		g.fillRect(x, y, 1, 1);
		g.setColor(Color.black);
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean getAnt() {
		return hasAnt;
	}
	
	public void setAnt(boolean antOrNot) {
		hasAnt = antOrNot;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setColor(Color color) {
		displayColor = color;
	}

}
