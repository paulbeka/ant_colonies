package entities;

import java.util.Arrays;

import gfx.AntAnimated;
import states.SimState;
import world.Tile;

public class Ant extends AntAnimated {
	
	private boolean moved = false;
	private char pathState;
	private boolean hasFood;
	private int smellRange;
	private int intelligence;
	private int lastX, lastY;
		
	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
		init();
	}
	
	private void init() {
		pathState = 'r';
		hasFood = false;
		smellRange = 6;
		intelligence = -2;
	}
	
	public void update() {
		moved = false;
		move();
		
		if(SimState.getWorld().getLocationValue(x, y) == Tile.FOOD) {
			hasFood = true;
		}
		
		if(moved) {
			SimState.getWorld().setLocation(x, y, pathState);
			SimState.getWorld().setAnt(lastX, lastY, false);
		}
		
		if(hasFood)
			pathState = 'f';
		
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
		
		// Check if out of bounds
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
	
	// Find the best direction for the ant to go to
	public int[] getBestDirection() {
		int[] bestLoc = new int[2];
		int startSmellX = (int) x-smellRange/2;
		int startSmellY = (int) y-smellRange/2;
		
		int currX, currY;
		int xRange = 0;
		for(int i = 0; i <= smellRange; i++) {
			currX = startSmellX+i;
			for(int j = 0; j <= smellRange; j++) {
				currY = startSmellY+j;
				
				// checks if you are within range
				// TODO: SOLVE THIS PROBLEM WHERE YOU NEED TO PUT IN THE SCREEN DIMENTIONS
				if(currX < 0 || currY < 0 || currX > 799 || currY > 799 || currX == this.x || currY == this.y)
					continue;

				// Looking for food so go to food
				if(!hasFood && SimState.getWorld().getLocationValue(currX, currY) == Tile.FOOD) {
					bestLoc[0] += smellRange/2 -(currX -x);
					bestLoc[1] += smellRange/2 - (currY - y);
				// looking for food so don't go home
				} else if (!hasFood && SimState.getWorld().getLocationValue(currX, currY) == Tile.ANT_TRAIL)  {
					xRange += smellRange/2 - (x - currX);
					bestLoc[0] += smellRange/2 - (x - currX);
					bestLoc[1] += smellRange/2 - (y - currY);
				// looking to go home
				} else if (hasFood && SimState.getWorld().getLocationValue(currX, currY) == Tile.ANT_TRAIL) {
					bestLoc[0] += smellRange/2 -(currX -x);
					bestLoc[1] += smellRange/2 - (currY - y);
				// going home so don't go towards food
				} else if (hasFood && SimState.getWorld().getLocationValue(currX, currY) == Tile.FOOD){
					bestLoc[0] += smellRange/2 - (x - currX);
					bestLoc[1] += smellRange/2 - (y - currY);
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
	
	public int[] getLocation() {
		return new int[] {x, y};
	}
	
}
