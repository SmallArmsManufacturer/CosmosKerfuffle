package alsoknownasthemanatees.cosmoskerfuffle.scene;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import alsoknownasthemanatees.cosmoskerfuffle.entity.Entity;
import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class MainScene extends Scene {
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public MainScene() {
		entities.add(new Entity(new Sprite(Sprite.Type.PARROT)));
		entities.add(new Entity(new Sprite(Sprite.Type.TURTLE)));
		entities.get(0).x += 16;
		entities.get(0).y += 16;
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
		
		g.translate(GamePanel.instance.getWidth() / 2.0, GamePanel.instance.getHeight() / 2.0);
		g.transform(AffineTransform.getScaleInstance(zoom, zoom));
		
		for (Entity e : entities) {
			e.sprite.paint(g, (int) e.x - Sprite.SIZE, (int) e.y - Sprite.SIZE, Sprite.SIZE);
		}
	}
	
}
