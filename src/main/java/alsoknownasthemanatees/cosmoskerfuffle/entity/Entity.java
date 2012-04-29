package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Graphics2D;
import java.util.Random;

public class Entity {
	
	public final Sprite sprite;
	public double x, y, vx, vy;
	
	public Entity(Sprite sprite) {
		this.sprite = sprite;
		Random r = new Random();
		vx = r.nextDouble() * 20.0 - 10.0;
		vy = r.nextDouble() * 20.0 - 10.0;
	}
	
}
