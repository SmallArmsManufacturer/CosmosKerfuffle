package alsoknownasthemanatees.cosmoskerfuffle;

import alsoknownasthemanatees.cosmoskerfuffle.scene.Scene;
import alsoknownasthemanatees.cosmoskerfuffle.scene.SplashScreen;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
	
	public static final GamePanel instance = new GamePanel();
	
	private GamePanel() {
		setPreferredSize(new Dimension(800, 600));
	}
	
	@Override
	public void paint(Graphics g) {
		Scene.stack.peek().paint((Graphics2D) g);
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame("Cosmos Kerfuffle");
		
		Scene.stack.push(new SplashScreen());
		
		window.addKeyListener(instance);
		window.add(instance);
		window.pack();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		new Timer(1, new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				instance.repaint();
			}
		
		}).start();
	}

	public void keyTyped(KeyEvent ke) {
		Scene.stack.peek().keyTyped(ke);
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Scene.stack.pop();
			if (Scene.stack.empty())
				System.exit(0);
		} else
			Scene.stack.peek().keyPressed(ke);
	}

	public void keyReleased(KeyEvent ke) {
		Scene.stack.peek().keyReleased(ke);
	}
	
}
