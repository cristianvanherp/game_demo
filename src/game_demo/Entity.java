package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Entity extends GameObject {
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	
	public Entity(int width, int height, int speed) {
		super(width, height, speed);
	}
	
	public Entity(int width, int height, int x, int y, int speed) {
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
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_LEFT)) {
			this.setVelx(-this.getSpeed());
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.setVelx(this.getSpeed());
		}
		else {
			this.setVelx(0);
		}
		
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_UP)) {
			this.setVely(-this.getSpeed());
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_DOWN)) {
			this.setVely(this.getSpeed());
		}
		else {
			this.setVely(0);
		}
	}
}
