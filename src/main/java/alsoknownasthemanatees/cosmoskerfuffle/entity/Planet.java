
package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Planet extends Entity {
	
	public final Sprite sprite;
        public double radius;
	
	public Planet(Universe universe, Sprite sprite, double radius, double mass, int x ,int y) {
		super(universe);
		this.sprite = sprite;
                this.radius = radius;
                this.mass = mass;
                this.x = x;
                this.y = y;
	}
	
	
	

	@Override
	public void paint(Graphics2D g) {
		sprite.paint(g, 0, (int) x, (int) y, (int) radius * 2);
	}
	
}
