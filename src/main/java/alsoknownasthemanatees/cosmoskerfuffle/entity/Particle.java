package alsoknownasthemanatees.cosmoskerfuffle.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Particle extends Entity {
	
	public double x, y, vx, vy;
	public Color colour;
	public boolean fade;
	
	public Particle(Universe universe, double x, double y, double vx, double vy, Color colour) {
		super(universe);
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.colour = colour;
	}
	
	@Override
	public void update(double dt) {
		if (fade && new Random().nextDouble() * 10 < 0.1)
			universe.removeEntity(this);
		x += vx * dt;
		y += vy * dt;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(colour);
		g.fillRect((int) x, (int) y, 1, 1);
	}
	
}
