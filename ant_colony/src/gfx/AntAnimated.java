package gfx;

import java.awt.image.BufferedImage;

public abstract class AntAnimated extends AnimatedImage {
	
	private SpriteSheet sheet;
	
	public AntAnimated() {
		//generateImage();
	}
	
	private void generateImage() {
		imageList = new BufferedImage[62];
		sheet = new SpriteSheet(ImageLoader.loadImage("ant_walk.png"));
		
		width = 25;
		height = 42;
		
		// Add all images to the animation
		int count = 0;
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(count < 62) {
					imageList[count] = sheet.crop(202*i, j*248, 202, 248);
				}
				count++;
			}
		}
	}

}
