package entities;

import gfx.AnimatedImage;

public class Ant extends AnimatedImage {
	
	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	private void init() {
		
	}
	
	public void update() {
		x += 1;
	}
	
}
