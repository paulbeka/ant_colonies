package entities;

import java.util.Arrays;

import gfx.AntAnimated;
import states.SimState;

public class Ant extends AntAnimated {
	
	private boolean moved = false;
	private char pathState;
	private boolean hasFood;
	private int smellRange;
	private int intelligence;
		
	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	private void init() {
		pathState = 'r';
		hasFood = false;
		smellRange = 8;
		intelligence = 3;
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
		
		int[] bestLoc = getBestDirection();
		int randX = Math.round((int) (smellRange*Math.random()) -smellRange/2 +bestLoc[0]*intelligence);
		int randY = Math.round((int) (smellRange*Math.random()) -smellRange/2 +bestLoc[1]*intelligence);
		
		// Check if divide by 0
		if(randX == 0) 
			x += Math.round(((Math.random() * 2) -1));
		else
			x += randX/Math.abs(randX);
		
		if(randY == 0)
			y += Math.round(((Math.random() * 2) -1));
		else
			y += randY/Math.abs(randY);
		
		if(y >= 799)
			y = 0;
		if(x >= 799)
			x = 0;
		if(x <= 0)
			x = 799;
		if(y <= 0)
			y = 799;
				
		if(!(prevX == x && prevY == y))
			moved = true;
	}
	
	public int[] getBestDirection() {
		int[] bestLoc = new int[2];
		int startSmellX = (int) x-smellRange/2 -1;
		int startSmellY = (int) y-smellRange/2 -1;
		
		int currX, currY;
		for(int i = 0; i <= smellRange; i++) {
			for(int j = 0; j <= smellRange; j++) {
				currX = startSmellX+i;
				currY = startSmellY+j;
				
				// checks if you are within range
				// TODO: SOLVE THIS PROBLEM WHERE YOU NEED TO PUT IN THE SCREEN DIMENTIONS
				if(currX < 0 || currY < 0 || currX > 799 || currY > 799 || (currX == x && currY == y))
					continue;
				
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
				} else if (hasFood && SimState.getWorld().getLocation(currX, currY) == 'f'){
					bestLoc = goThereOrNot(-1, currX, currY, bestLoc);
				} 
			}
		}

		// Set the values to 1 or 0
		if(bestLoc[0] != 0)
			bestLoc[0] = bestLoc[0]/Math.abs(bestLoc[0]);
		if(bestLoc[1] != 0)
			bestLoc[1] = bestLoc[1]/Math.abs(bestLoc[1]);

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
