package alsoknownasthemanatees.cosmoskerfuffle.scene;

import alsoknownasthemanatees.cosmoskerfuffle.entity.Universe;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class MainScene extends Scene {
	
	private Universe universe = new Universe();
	
	@Override
	public void update(double dt) {
		if (keys.contains(KeyEvent.VK_W))
			universe.player1.thrust(dt);
		if (keys.contains(KeyEvent.VK_A))
			universe.player1.rotation -= Math.PI * dt;
		if (keys.contains(KeyEvent.VK_D))
			universe.player1.rotation += Math.PI * dt;

		if (keys.contains(KeyEvent.VK_UP))
			universe.player2.thrust(dt);
		if (keys.contains(KeyEvent.VK_LEFT))
			universe.player2.rotation -= Math.PI * dt;
		if (keys.contains(KeyEvent.VK_RIGHT))
			universe.player2.rotation += Math.PI * dt;

		universe.update(dt);
	}

	@Override
	public void paint(Graphics2D g) {
		universe.paint(g);
	}
	
}
