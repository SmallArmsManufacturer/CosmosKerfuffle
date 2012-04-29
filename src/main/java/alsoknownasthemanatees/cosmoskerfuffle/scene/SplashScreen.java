package alsoknownasthemanatees.cosmoskerfuffle.scene;

import alsoknownasthemanatees.cosmoskerfuffle.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class SplashScreen extends Scene {

	@Override
	public void paint(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.instance.getWidth(), GamePanel.instance.getHeight());
		g.setColor(Color.WHITE);
		g.drawString("Press any key", 0, 10);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		super.keyPressed(ke);
		Scene.stack.push(new MainScene());
	}
	
}
