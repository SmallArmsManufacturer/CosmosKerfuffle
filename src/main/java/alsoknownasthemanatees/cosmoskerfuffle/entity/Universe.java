package alsoknownasthemanatees.cosmoskerfuffle.entity;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import alsoknownasthemanatees.cosmoskerfuffle.graphics.Sprite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Universe {
	
	public static final int SIZE = 1000;
	public static int NUM_STARS = 1000;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private List<Entity> entitiesToRemove = new ArrayList<Entity>();
	public Ship player1, player2;
        public Planet planet1, planet2, planet3;
	
	public Universe() {
		player1 = new Ship(this, new Sprite(Sprite.Type.PARROT));
		player2 = new Ship(this, new Sprite(Sprite.Type.TURTLE));
		player2.x += 100;
		entities.add(player1);
		entities.add(player2);
		Random r = new Random();
		for (int i = 0; i < NUM_STARS; i++)
			entities.add(new Particle(this, r.nextDouble() * Universe.SIZE, r.nextDouble() * Universe.SIZE, 0, 0, Color.WHITE));
		planet1 = new Planet(this, new Sprite(Sprite.Type.PLANET1), 50.0, 700.0, 200, 200);
                planet2 = new Planet(this, new Sprite(Sprite.Type.PLANET2), 30.0, 400.0, 600, 700);
		planet3 = new Planet(this, new Sprite(Sprite.Type.PLANET3), 70.0, 700.0, 800, 200);

                entities.add(planet1);
                entities.add(planet2);
                entities.add(planet3);

	}
	
	public void explode(int x, int y) {
		for (int i = 0; i < NUM_STARS; i++) {
			Random r = new Random();
			double angle = r.nextDouble() * Math.PI;
			double vel = r.nextDouble() * 200 - 100;
			double vx = vel * Math.sin(angle);
			double vy = vel * Math.cos(angle);
			Particle particle = new Particle(this, x, y, vx, vy, Color.YELLOW);
			particle.fade = true;
			entitiesToAdd.add(particle);
		}
	}
	
	public void update(double dt) {
		double x = player2.x - player1.x;
		double y = player2.y - player1.y;
		
		if (x * x + y * y < Sprite.SIZE * Sprite.SIZE) {
			explode((int) (player1.x + x + Sprite.SIZE / 2), (int) (player1.y + y + Sprite.SIZE / 2));
			player1.reset();
			player2.reset();
			player2.x += 100;
			player1.score--;
			player2.score--;
		}
		
		for (Entity e : entities) {
			if (e instanceof Particle) {
				Particle p = (Particle) e;
				if (p.colour == Color.RED) {
					double dx = player1.x + Sprite.SIZE / 2 - p.x;
					double dy = player1.y + Sprite.SIZE / 2 - p.y;
					if (dx * dx + dy * dy < Sprite.SIZE * Sprite.SIZE) {
						explode((int) (player1.x + Sprite.SIZE / 2), (int) (player1.y + Sprite.SIZE / 2));
						removeEntity(p);
						player1.reset();
						player2.score++;
					}
					double dx2 = player2.x + Sprite.SIZE / 2 - p.x;
					double dy2 = player2.y + Sprite.SIZE / 2 - p.y;
					if (dx2 * dx2 + dy2 * dy2 < Sprite.SIZE * Sprite.SIZE) {
						explode((int) (player2.x + Sprite.SIZE / 2), (int) (player2.y + Sprite.SIZE / 2));
						removeEntity(p);
						player2.reset();
						player2.x += 100;
						player1.score++;
					}
				}
			}
                        
                        if (e instanceof Planet) {
                            for (Entity trains : entities) {
                                if (trains instanceof Particle) {
                                    Particle particle = (Particle) trains;
                                    if (particle.colour != Color.WHITE)
                                    {
                                        Planet p = (Planet) e;
                                        double distanceX = (double)particle.x - (double)e.x - p.radius;
                                        double distanceY = (double)particle.y - (double)e.y - p.radius;
                                        double distance = distanceX * distanceX + distanceY * distanceY;
                                        double force = (e.mass) / (distance * 0.1);


                                        force = force / (Math.abs(distanceX) + Math.abs(distanceY));
                                        particle.vx -= force * distanceX;
                                        particle.vy -= force * distanceY;
                                    }
                                }
                            }

                            Planet p = (Planet) e;
                            double distanceX = (double)player1.x - (double)e.x - p.radius;
                            double distanceY = (double)player1.y - (double)e.y - p.radius;
                            double distance = distanceX * distanceX + distanceY * distanceY;
                            double force = (e.mass) / (distance * 0.1);

                            force = force / (Math.abs(distanceX) + Math.abs(distanceY));
                            player1.vx -= force * distanceX;
                            player1.vy -= force * distanceY;
                            if (Math.sqrt(distance) < p.radius)
                                player1.reset();
                            
                            distanceX = (double)player2.x - (double)e.x - p.radius;
                            distanceY = (double)player2.y - (double)e.y - p.radius;
                            distance = distanceX * distanceX + distanceY * distanceY;
                            force = (e.mass) / (distance * 0.1);

                            force = force / (Math.abs(distanceX) + Math.abs(distanceY));
                            player2.vx -= force * distanceX;
                            player2.vy -= force * distanceY;
                            if (Math.sqrt(distance) < p.radius)
                            {
                                player2.reset();
                                player2.x += 100;
                            }
                        }
			
			e.update(dt);
		}
		
		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();
		
		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();
	}
	
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		
		double min_x = Math.min(player1.x, player2.x);
		double min_y = Math.min(player1.y, player2.y);
		double max_x = Math.max(player1.x, player2.x) + Sprite.SIZE;
		double max_y = Math.max(player1.y, player2.y) + Sprite.SIZE;
		
		double zoomX = (max_x - min_x) / (GamePanel.instance.getWidth() - 300);
		double zoomY = (max_y - min_y) / (GamePanel.instance.getHeight() - 300);
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
		
		g.setTransform(new AffineTransform());
		g.setColor(Color.WHITE);
		g.drawString("Player 1 score: " + player1.score, 0, GamePanel.instance.getHeight());
		String p2score = "Player 2 score: " + player2.score;
		g.drawString(p2score, GamePanel.instance.getWidth() - g.getFontMetrics().stringWidth(p2score), GamePanel.instance.getHeight());
	}
	
	public void addEntity(Entity e) {
		entitiesToAdd.add(e);
	}
	
	public void removeEntity(Entity e) {
		entitiesToRemove.add(e);
	}
	
}
