package alsoknownasthemanatees.cosmoskerfuffle.scene;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SplashScreen extends Scene {
	
	private BufferedImage pressAnyKey;
        private BufferedImage splash;
	private double elapsedTime;
	
	public SplashScreen() {
		try {
			pressAnyKey = ImageIO.read(SplashScreen.class.getResource("/pressAnyKey.png"));
                        splash = ImageIO.read(SplashScreen.class.getResource("/splash.png"));

		} catch (IOException ex) {
			ex.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	@Override
	public void update(double dt) {
		elapsedTime += dt;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
                g.drawImage(splash, GamePanel.instance.getWidth() / 2 - splash.getWidth() / 2, GamePanel.instance.getHeight() / 2 - splash.getHeight() / 2, null);

		if ((int) elapsedTime % 2 == 0)
			g.drawImage(pressAnyKey, GamePanel.instance.getWidth() / 2 - pressAnyKey.getWidth() / 2, GamePanel.instance.getHeight() / 2 - pressAnyKey.getHeight() / 2, null);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		super.keyPressed(ke);
		Scene.stack.push(new MainScene());
	}
	
}
