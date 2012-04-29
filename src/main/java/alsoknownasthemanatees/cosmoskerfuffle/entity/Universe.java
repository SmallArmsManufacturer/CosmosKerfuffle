package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Universe {
	
	public static final int SIZE = 1000;
	public static int NUM_STARS = 1000;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Star> stars = new ArrayList<Star>();
	private List<Particle> particles = new ArrayList<Particle>();
	public Entity player1, player2;
	
	public Universe() {
		player1 = new Entity(new Sprite(Sprite.Type.PARROT));
		player2 = new Entity(new Sprite(Sprite.Type.TURTLE));
		player2.x += 100;
		entities.add(player1);
		entities.add(player2);
		for (int i = 0; i < NUM_STARS; i++)
			stars.add(new Star());
	}
	
	public void explode(int x, int y) {
		for (int i = 0; i < NUM_STARS; i++)
			particles.add(new Particle(x, y, Color.RED));
	}
	
	public void update(double dt) {
		for (Entity e : entities) {
			e.x += e.vx * dt;
			e.y += e.vy * dt;
			if (e.x < 0) e.vx = Math.abs(e.vx);
			if (e.y < 0) e.vy = Math.abs(e.vy);
			if (e.x > 1000 - Sprite.SIZE) e.vx = -Math.abs(e.vx);
			if (e.y > 1000 - Sprite.SIZE) e.vy = -Math.abs(e.vy);
		}
		
		for (Entity e1 : entities) {
			for (Entity e2 : entities) {
				if (e1 != e2) {
					double x = e2.x - e1.x;
					double y = e2.y - e1.y;
					if (x * x + y * y < Sprite.SIZE * Sprite.SIZE) {
						explode((int) (e1.x + x + Sprite.SIZE / 2), (int) (e1.y + y + Sprite.SIZE / 2));
						player1.x = SIZE / 2;
						player2.x = player1.x + 100;
						player1.y = player2.y = SIZE / 2;
						player1.vx = player1.vy = player1.rotation = player2.vx = player2.vy = player2.rotation = 0;
					}
				}
			}
		}
		
		Random r = new Random();
		Iterator<Particle> k = particles.iterator();
		while (k.hasNext()) {
			Particle p = k.next();
			if (r.nextDouble() * 20 < 0.1)
				k.remove();
			p.x += p.vx * dt;
			p.y += p.vy * dt;
		}
	}
	
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		
		int min_x = SIZE, min_y = SIZE, max_x = 0, max_y = 0;
		int size = Math.min(GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		for (Entity e : entities) {
			min_x = Math.min(min_x, (int) e.x);
			min_y = Math.min(min_y, (int) e.y);
			max_x = Math.max(max_x, (int) e.x + Sprite.SIZE);
			max_y = Math.max(max_y, (int) e.y + Sprite.SIZE);
		}
		
		double zoomX = (double) (max_x - min_x) / (GamePanel.instance.getWidth() - 100);
		double zoomY = (double) (max_y - min_y) / (GamePanel.instance.getHeight() - 100);
		double zoom = 1 / Math.max(zoomX, zoomY);
		if (zoom > 5) zoom = 5;
		
		double center_x = (min_x + max_x) / 2;
		double center_y = (min_y + max_y) / 2;
		AffineTransform t = AffineTransform.getTranslateInstance(GamePanel.instance.getWidth() / 2.0, GamePanel.instance.getHeight() / 2.0);
		t.scale(zoom, zoom);
		g.setTransform(t);
		
		g.setColor(Color.WHITE);
		g.drawRect(- (int) center_x, - (int) center_y, 1000, 1000);
		for (Star star : stars) {
			g.fillRect((int) (star.x - center_x), (int) (star.y - center_y), 1, 1);
		}
		
		for (Particle particle : particles) {
			g.setColor(particle.colour);
			g.fillRect((int) (particle.x - center_x), (int) (particle.y - center_y), 1, 1);
		}
		
		for (Entity e : entities) {
			e.sprite.paint(g, e.rotation, (int) (e.x - center_x), (int) (e.y - center_y), Sprite.SIZE);
		}
	}
	
	public static class Star {
		
		public double x, y;
		
		public Star() {
			Random r = new Random();
			this.x = r.nextDouble() * 1000;
			this.y = r.nextDouble() * 1000;
		}
		
	}
	
}
