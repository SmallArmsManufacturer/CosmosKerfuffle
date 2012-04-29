package alsoknownasthemanatees.cosmoskerfuffle.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Star extends Particle {
	
	private static Random r = new Random();

	public Star(Universe universe) {
		super(universe, r.nextDouble() * Universe.SIZE, r.nextDouble() * Universe.SIZE, 0, 0, Color.WHITE);
	}
	
	@Override
	public void update(double dt) {}
	
	@Override
	public void paint(Graphics2D g) {
		g.fillRect((int) x, (int) y, 1, 1);
	}
	
}
