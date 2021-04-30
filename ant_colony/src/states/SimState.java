package states;

import java.awt.Graphics;

public class SimState extends State {

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.fillRect(0, 0, 100, 200);
	}

}
