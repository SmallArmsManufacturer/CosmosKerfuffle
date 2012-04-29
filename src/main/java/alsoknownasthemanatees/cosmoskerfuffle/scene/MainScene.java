package alsoknownasthemanatees.cosmoskerfuffle.scene;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import alsoknownasthemanatees.cosmoskerfuffle.entity.Entity;
import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class MainScene extends Scene {
	
	private List<Entity> entities = new ArrayList<Entity>();
	private Entity player1, player2;
	
	public MainScene() {
		player1 = new Entity(new Sprite(Sprite.Type.PARROT));
		player2 = new Entity(new Sprite(Sprite.Type.TURTLE));
		entities.add(player1);
		entities.add(player2);
	}
	
	@Override
	public void update(double dt) {
			if (keys.contains(KeyEvent.VK_UP))
				player1.thrust(dt);
			if (keys.contains(KeyEvent.VK_LEFT))
				player1.rotation -= Math.PI * dt;
			if (keys.contains(KeyEvent.VK_RIGHT))
				player1.rotation += Math.PI * dt;
			
			if (keys.contains(KeyEvent.VK_W))
				player2.thrust(dt);
			if (keys.contains(KeyEvent.VK_A))
				player2.rotation -= Math.PI * dt;
			if (keys.contains(KeyEvent.VK_D))
				player2.rotation += Math.PI * dt;

		for (Entity e : entities) {
			e.x += e.vx * dt;
			e.y += e.vy * dt;
			if (e.x < 0) e.vx = Math.abs(e.vx);
			if (e.y < 0) e.vy = Math.abs(e.vy);
		}
	}

	@Override
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		
		int min_x = (int) entities.get(0).x, min_y = (int) entities.get(0).y, max_x = 0, max_y = 0;
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
		
		double center_x = (min_x + max_x) / 2;
		double center_y = (min_y + max_y) / 2;
		AffineTransform t = AffineTransform.getTranslateInstance(GamePanel.instance.getWidth() / 2.0, GamePanel.instance.getHeight() / 2.0);
		t.scale(zoom, zoom);
		g.setTransform(t);
		
		for (Entity e : entities) {
			e.sprite.paint(g, e.rotation, (int) (e.x - center_x), (int) (e.y - center_y), Sprite.SIZE);
		}
	}
	
}
