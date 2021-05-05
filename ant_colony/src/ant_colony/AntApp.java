package ant_colony;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import states.SimState;
import states.State;

public class AntApp implements java.lang.Runnable {
		
	private boolean running = false;
	Thread thread;
	
	private int width, height;
	
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	
	State simState;
	State menuState;
	
	public AntApp(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	private void init() {
		display = new Display("Ants", width, height);
		
		simState = new SimState(width, height);
		
		State.setState(simState);
	}
	
	private void update() {
		if(State.getState() != null)
			State.getState().update();
	}
	
	private void render() {
		// Set up strategy and clear the screen
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				delta--;
			}
		}
		
		stop();
		
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
