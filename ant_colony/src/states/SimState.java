package states;

import java.awt.Graphics;

import entities.Ant;
import entities.Colony;
import world.World;

public class SimState extends State {
	
	private Ant ants[];
	private static World world;
	
	private Colony colony;
	// number of ants in the system
	private int numAnts = 1000;
	
	public SimState(int width, int height) {
		world = new World(width, height);
		ants = new Ant[numAnts];
		
		generate();
	}
	
	private void generate() {
		for(int i = 0; i < numAnts; i++) {
			ants[i] = new Ant(400+1, 400+i);
		}
		colony = new Colony(400, 400);
	}

	@Override
	public void update() {
		world.update();
		for(Ant ant : ants) {
			if(ant != null)
				ant.update();
		}
	}

	@Override
	public void render(Graphics g) {
		// Render world and ants first, then the colonies
		world.render(g);
		for(Ant ant : ants) {
			//if(ant != null)
				//ant.render(g);
		}
		colony.render(g);
	}
	
	public static World getWorld() {
		return world;
	}
}
