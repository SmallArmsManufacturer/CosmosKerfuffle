package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {
	
	public static final int SIZE = 1000;
	public static int NUM_STARS = 1000;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();
	public Ship player1, player2;
	
	public Universe() {
		player1 = new Ship(this, new Sprite(Sprite.Type.PARROT));
		player2 = new Ship(this, new Sprite(Sprite.Type.TURTLE));
		player2.x += 100;
		entities.add(player1);
		entities.add(player2);
		for (int i = 0; i < NUM_STARS; i++)
			entities.add(new Star(this));
	}
	
	public void explode(int x, int y) {
		for (int i = 0; i < NUM_STARS; i++) {
			Random r = new Random();
			double angle = r.nextDouble() * Math.PI;
			double vel = r.nextDouble() * 200 - 100;
			double vx = vel * Math.sin(angle);
			double vy = vel * Math.cos(angle);
			entities.add(new Particle(this, x, y, vx, vy, Color.RED));
		}
	}
	
	public void update(double dt) {
		double x = player2.x - player1.x;
		double y = player2.y - player1.y;
		
		if (x * x + y * y < Sprite.SIZE * Sprite.SIZE) {
			explode((int) (player1.x + x + Sprite.SIZE / 2), (int) (player1.y + y + Sprite.SIZE / 2));
			player1.x = SIZE / 2;
			player2.x = player1.x + 100;
			player1.y = player2.y = SIZE / 2;
			player1.vx = player1.vy = player1.rotation = player2.vx = player2.vy = player2.rotation = 0;
		}
		
		for (Entity e : entities) {
			e.update(dt);
		}
		
		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();
	}
	
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		
		double min_x = Math.min(player1.x, player2.x);
		double min_y = Math.min(player1.y, player2.y);
		double max_x = Math.max(player1.x, player2.x) + Sprite.SIZE;
		double max_y = Math.max(player1.y, player2.y) + Sprite.SIZE;
		
		double zoomX = (max_x - min_x) / (GamePanel.instance.getWidth() - 100);
		double zoomY = (max_y - min_y) / (GamePanel.instance.getHeight() - 100);
		double zoom = 1 / Math.max(zoomX, zoomY);
		if (zoom > 5) zoom = 5;
		
		double center_x = (min_x + max_x) / 2;
		double center_y = (min_y + max_y) / 2;
		AffineTransform t = AffineTransform.getTranslateInstance(GamePanel.instance.getWidth() / 2.0, GamePanel.instance.getHeight() / 2.0);
		t.scale(zoom, zoom);
		t.translate(-center_x, -center_y);
		g.setTransform(t);
		
		g.setColor(Color.WHITE);
		g.drawRect(0, 0, SIZE, SIZE);
		
		for (Entity e : entities) {
			e.paint(g);
		}
	}
	
	public void removeEntity(Entity e) {
		entitiesToRemove.add(e);
	}
	
}
