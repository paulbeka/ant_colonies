package gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class AnimatedImage {

	private BufferedImage[] imageList;
	private int width, height;
	private int current = 0;
	protected int x, y;
	
	public void render(Graphics g) {
		// Loop to the next image, then back to the start
		if(current >= imageList.length)
			current = 0;
		g.drawImage(imageList[current], x, y, width, height, null);
	}
	
}
