package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.util.Random;

public class Entity {
	
	public final Sprite sprite;
	public double x, y, vx, vy, rotation;
	
	public Entity(Sprite sprite) {
		this.sprite = sprite;
		x = 500;
		y = 500;
	}
	
	public void thrust (double dt) {
		vx += Math.sin(rotation) * dt * 100;
		vy -= Math.cos(rotation) * dt * 100;
		
		if (vx * vx + vy * vy > 10000) {
			vx *= 0.8;
			vy *= 0.8;
		}
	}
	
}
