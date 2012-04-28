package alsoknownasthemanatees.cosmoskerfuffle.scene;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.Stack;

public interface Scene extends KeyListener {
	
	public static final Stack<Scene> stack = new Stack<Scene>();
	
	public abstract void paint(Graphics2D g);
	
}
