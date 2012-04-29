package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Graphics2D;
import java.util.Random;

public class Entity {
	
	public final Sprite sprite;
	public double x, y, vx, vy, rotation;
	
	public Entity(Sprite sprite) {
		this.sprite = sprite;
		Random r = new Random();
	}
	
	public void thrust (double dt) {
		vx += Math.sin(rotation) * dt * 100;
		vy -= Math.cos(rotation) * dt * 100;
	}
	
}
