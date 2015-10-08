package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;

public interface GameObject {
	public void render(Graphics g, Canvas canvas);
	public void tick();
	public void animate();
}
