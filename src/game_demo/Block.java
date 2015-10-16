package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Block extends GameObject {
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	
	public Block(int width, int height, int speed) {
		super(width, height, speed);
	}
	
	public Block(int width, int height, int x, int y, int speed) {
		super(width, height, x, y, speed);
	}
	
	//Game Object methods
	public void tick() {
		this.setX(this.x + this.getVelx());
		this.setY(this.y + this.getVely());
	}

	public void animate() {
		
	}
	
	public void render(Graphics g, Canvas canvas) {
		
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		
	}

	public void handleCollision(List<GameObject> gameObjects) {
		
	}
	
}
