package entities;

import gfx.AntAnimated;
import states.SimState;

public class Ant extends AntAnimated {
	
	private boolean moved = false;
	private char pathState;
	private boolean hasFood;
	private int smellRange;
	
	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	private void init() {
		pathState = 'r';
		hasFood = false;
		smellRange = 4;
	}
	
	public void update() {
		moved = false;
		move();
		if(hasFood)
			pathState = 'f';
		if(moved)
			SimState.getWorld().setLocation(x, y, pathState);
	}
	
	private void move() {
		int prevX = x;
		int prevY = y;
		x += Math.round(((Math.random() * 2) -1));
		y += Math.round(((Math.random() * 2) -1));
		
		if(!(prevX == x && prevY == y))
			moved = true;
	}
	
	public int[] getBestDirection() {
		int[] bestLoc = new int[2];
		int startSmellX = (int) x-smellRange/2;
		int startSmellY = (int) y-smellRange/2;
		
		int currX, currY;
		for(int i = 0; i <= smellRange; i++) {
			for(int j = 0; j <= smellRange; j++) {
				currX = startSmellX+i;
				currY = startSmellY+j;
				// Looking for food so go to food
				if(!hasFood && SimState.getWorld().getLocation(currX, currY) == 'f') {
					bestLoc = goThereOrNot(1, currX, currY, bestLoc);
				// looking for food so don't go home
				} else if (!hasFood && SimState.getWorld().getLocation(currX, currY) == 'r')  {
					bestLoc = goThereOrNot(-1, currX, currY, bestLoc);
				// looking to go home
				} else if (hasFood && SimState.getWorld().getLocation(currX, currY) == 'r') {
					bestLoc = goThereOrNot(1, currX, currY, bestLoc);
				// going home so don't go towards food
				} else {
					bestLoc = goThereOrNot(-1, currX, currY, bestLoc);
				}
			}
		}
		return bestLoc;
	}
	
	public int[] goThereOrNot(int go, int currX, int currY, int[] bestLoc) {
		// modify best value on whether to go there or not
		if(currX > x)
			bestLoc[0] += go;
		else if(currX < x)
			bestLoc[0] -= go;
		if(currY > y)
			bestLoc[1] += go;
		else if(currY < y)
			bestLoc[1] -= go;
		return bestLoc;
	}
	
	public int[] getLocation() {
		return new int[] {x, y};
	}
	
}
