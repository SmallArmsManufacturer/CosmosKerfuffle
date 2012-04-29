package alsoknownasthemanatees.cosmoskerfuffle.entity;

import java.awt.Graphics2D;

public abstract class Entity {
	
	public Universe universe;
	public double x, y, vx, vy;
        public double mass;
	
	public Entity(Universe universe) {
		this.universe = universe;
	}
	
	public void update(double dt) {}
	
	public abstract void paint(Graphics2D g);
	
}
