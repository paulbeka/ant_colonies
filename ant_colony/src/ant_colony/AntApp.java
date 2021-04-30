package ant_colony;

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
		
		simState = new SimState();
		
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
		g.clearRect(0, 0, width, height);
		
		if(State.getState() != null) {
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		while(running) {
			
			update();
			render();
			
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
