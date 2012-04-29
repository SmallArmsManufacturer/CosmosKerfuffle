package alsoknownasthemanatees.cosmoskerfuffle.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprite {
	
	public static final int SIZE = 16;
	private static BufferedImage spriteSheet;
	
	private int x, y;
	
	static {
		try {
			spriteSheet = ImageIO.read(Sprite.class.getResource("/sprites.png"));
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public Sprite(Type type) {
		int width = spriteSheet.getWidth() / SIZE;
		int col = type.ordinal() % width;
		int row = type.ordinal() / width;
		x = col * SIZE;
		y = row * SIZE;
	}
	
	public void paint(Graphics2D g, int x, int y, int size) {
		g.drawImage(spriteSheet,
					x,      y,      x + size,      y + size,      // Destination
					this.x, this.y, this.x + SIZE, this.y + SIZE, // Source
					null);
	}
	
	public static enum Type { PARROT, MANTA_RAY, TURTLE }
	
}
