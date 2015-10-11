package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface GameObject {
	public void tick();
	public void animate();
	public void render(Graphics g, Canvas canvas);
	public void renderBoundaries(Graphics g, Canvas canvas);
	public void keyEvent(KeyboardInputListener keyboardInputListener);
}
