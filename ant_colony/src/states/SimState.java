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
	private int numAnts = 20;
	
	public SimState(int width, int height) {
		world = new World(width, height);
		ants = new Ant[numAnts];
		
		generate();
	}
	
	private void generate() {
		ants[0] = new Ant(40, 40);
		/*
		 * ants[1] = new Ant(42,42); ants[2] = new Ant(34, 42); ants[3] = new
		 * Ant(39,39);
		 */
		colony = new Colony(40, 40);
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
			if(ant != null)
				ant.render(g);
		}
		colony.render(g);
	}
	
	public static World getWorld() {
		return world;
	}
}
