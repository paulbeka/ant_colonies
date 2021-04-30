package states;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	//public abstract void run();
	
	public static State getState() {
		return currentState;
	}
	
	public static void setState(State newState) {
		currentState = newState;
	}
	
}
