package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Entity extends GameObject {
	public Entity(int width, int height, int speed) {
		super(width, height, speed);
	}
	
	public Entity(int width, int height, int x, int y, int speed) {
		super(width, height, x, y, speed);
	}
	
	//Game Object methods
	public void tick(Block block) {
		this.setX(this.x + this.getVelx());
		this.setY(this.y + this.getVely());
		
		this.handleCollision(block);
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
	
	//Utility methods
	private boolean isBottomColliding(Block block) {
		if(this.getBoundaryBottom().collides(block))
			return true;
		return false;
	}
	
	private boolean isTopColliding(Block block) {
		if(this.getBoundaryTop().collides(block))
			return true;
		return false;
	}
	
	private boolean isLeftColliding(Block block) {
		if(this.getBoundaryLeft().collides(block))
			return true;
		return false;
	}
	
	private boolean isRightColliding(Block block) {
		if(this.getBoundaryRight().collides(block))
			return true;
		return false;
	}
	
	private void handleCollision(Block block) {
		if(this.isTopColliding(block)) {
			this.setY((int)block.getMaxY() + 1);
		}
		
		if(this.isBottomColliding(block)) {
			this.setY((int)block.getMinY() - 1 - this.height);
		}
		
		if(this.isLeftColliding(block)) {
			this.setX((int)block.getMaxX() + 1);
		}
		
		if(this.isRightColliding(block)) {
			this.setX((int)block.getMinX() - 1 - this.width);
		}
	}
}
