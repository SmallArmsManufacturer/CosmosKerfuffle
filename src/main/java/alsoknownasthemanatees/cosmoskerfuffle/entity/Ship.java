package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Graphics2D;

public class Ship extends Entity {
	
	public final Sprite sprite;
	public double rotation;
	
	public Ship(Universe universe, Sprite sprite) {
		super(universe);
		this.sprite = sprite;
		x = y = Universe.SIZE / 2;
	}
	
	public void thrust(double dt) {
		vx += Math.sin(rotation) * dt * 100;
		vy -= Math.cos(rotation) * dt * 100;
		
		if (vx * vx + vy * vy > 10000) {
			vx *= 0.8;
			vy *= 0.8;
		}
	}
	
	@Override
	public void update(double dt) {
		x += vx * dt;
		y += vy * dt;
		if (x < 0) vx = Math.abs(vx);
		if (y < 0) vy = Math.abs(vy);
		if (x > Universe.SIZE - Sprite.SIZE) vx = -Math.abs(vx);
		if (y > Universe.SIZE - Sprite.SIZE) vy = -Math.abs(vy);
	}

	@Override
	public void paint(Graphics2D g) {
		sprite.paint(g, rotation, (int) x, (int) y, Sprite.SIZE);
	}
	
}