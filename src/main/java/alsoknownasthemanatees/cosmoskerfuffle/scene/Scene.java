package alsoknownasthemanatees.cosmoskerfuffle.scene;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Scene implements KeyListener {
	
	public static final Stack<Scene> stack = new Stack<Scene>();
	
	protected Set<Integer> keys = new HashSet<Integer>();
	
	public void update(double dt) {}
	public void paint(Graphics2D g) {}
	
	public void keyTyped(KeyEvent ke) {}
	public void keyPressed(KeyEvent ke) {
		keys.add(ke.getKeyCode());
	}
	public void keyReleased(KeyEvent ke) {
		keys.remove(ke.getKeyCode());
	}
	
}
