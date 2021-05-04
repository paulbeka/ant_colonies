package states;

import java.awt.Graphics;

import entities.Ant;
import world.World;

public class SimState extends State {
	
	private Ant ants[];
	private World world;
	// number of ants in the system
	private int numAnts = 20;
	
	public SimState(int width, int height) {
		world = new World(width, height);
		ants = new Ant[numAnts];
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
		// Render world first, then the ants
		world.render(g);
		for(Ant ant : ants) {
			if(ant != null)
				ant.render(g);
		}
	}
}
