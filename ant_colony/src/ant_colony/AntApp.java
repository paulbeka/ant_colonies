package ant_colony;

import java.awt.image.BufferStrategy;

import states.State;

public class AntApp {
	
	public static State currentState = null;
	
	private boolean running = false;
	private int width, height;
	
	private Display display;
	private BufferStrategy bs;
	
	public AntApp(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		display = new Display("Ants", width, height);
		
	}
	
	private void update() {
		
	}
	
	private void render() {
		// Set up strategy and clear the screen
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
	}
	
	public void run() {
		
		running = true;
		
		while(running) {
			
			update();
			render();
			
		}
		
	}

}
