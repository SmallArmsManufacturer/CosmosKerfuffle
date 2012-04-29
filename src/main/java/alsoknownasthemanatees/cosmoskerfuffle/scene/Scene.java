package alsoknownasthemanatees.cosmoskerfuffle.scene;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class Scene implements KeyListener {
	
	public static final Stack<Scene> stack = new Stack<Scene>();
	
	public void update(double dt) {}
	public void paint(Graphics2D g) {}
	
	public void keyTyped(KeyEvent ke) {}
	public void keyPressed(KeyEvent ke) {}
	public void keyReleased(KeyEvent ke) {}
	
}
