package alsoknownasthemanatees.cosmoskerfuffle.entity;

import java.awt.Color;
import java.util.Random;

public class Particle {
	
	public double x, y, vx, vy;
	public Color colour;
	
	public Particle(double x, double y, Color colour) {
		this.x = x;
		this.y = y;
		this.colour = colour;
		Random r = new Random();
		double angle = r.nextDouble() * Math.PI;
		double vel = r.nextDouble() * 200 - 100;
		vx = vel * Math.sin(angle);
		vy = vel * Math.cos(angle);
	}
	
}
