package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

public class Entity extends GameObject {
	public Entity(int width, int height, int speed) {
		super(width, height, speed);
	}
	
	public Entity(int width, int height, int x, int y, int speed) {
		super(width, height, x, y, speed);
	}
	
	//Game Object methods
	public void tick(List<GameObject> gameObjects) {
		this.setX(this.x + this.getVelx());
		this.setY(this.y + this.getVely());
		this.handleCollision(gameObjects);
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
	private void handleCollision(List<GameObject> gameObjects) {
		for(GameObject gameObject: gameObjects) {
			if(this.isTopColliding(gameObject)) {
				this.setY((int)gameObject.getMaxY() + 1);
			}
			
			if(this.isBottomColliding(gameObject)) {
				this.setY((int)gameObject.getMinY() - 1 - this.height);
			}
			
			if(this.isLeftColliding(gameObject)) {
				this.setX((int)gameObject.getMaxX() + 1);
			}
			
			if(this.isRightColliding(gameObject)) {
				this.setX((int)gameObject.getMinX() - 1 - this.width);
			}
		}
	}
}
