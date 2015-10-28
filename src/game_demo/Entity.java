package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Entity extends GameObject {
	
	private static final long serialVersionUID = -529082995350931837L;

	public Entity(int width, int height, int x, int y, int speed, SpriteSheet spriteSheet, boolean affectedByGravity, int jumpSpeed) {
		super(width, height, x, y, speed, spriteSheet, affectedByGravity, jumpSpeed);
	}
	
	//Game Object methods
	public void tick(List<GameObject> gameObjects, int gravity, int maxFallSpeed) {
		if(this.isFalling() && this.getVely() <= maxFallSpeed)
			this.setVely(this.getVely() + gravity);
		
		this.setX(this.x + this.getVelx());
		this.setY(this.y + this.getVely());
		
		if(this.getVelx() != 0 || this.getVely() != 0)
			this.handleCollision(gameObjects);
	}
	
	public void render(Graphics g, Canvas canvas, Camera camera) {
		g.drawImage(this.getSpriteSheet().slice(this.getSpriteSheet().getCurrentCol(), this.getSpriteSheet().getCurrentRow(), this.getSpriteSheet().getSpriteWidth(), this.getSpriteSheet().getSpriteHeight()), (int)this.getX() - camera.getCurrentOffsetX(), (int)this.getY() - camera.getCurrentOffsetY(), (int)this.getWidth() + 1, (int)this.getHeight()+1, canvas);	
	}
	
	public void animate() {
		if(this.getVelx() != 0) {
			this.getSpriteSheet().animate();
		}
		else {
			this.getSpriteSheet().setCurrentCol(1);
		}
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_LEFT)) {
			this.setVelx(-this.getSpeed());
			this.getSpriteSheet().setCurrentRow(this.getSpriteSheet().getRowLeft());
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.setVelx(this.getSpeed());
			this.getSpriteSheet().setCurrentRow(this.getSpriteSheet().getRowRight());
		}
		else {
			this.setVelx(0);
		}
		
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_SPACE)) {
			this.jump();
		}
	}
	
	//Utility methods
	private void handleCollision(List<GameObject> gameObjects) {
		for(GameObject gameObject: gameObjects) {
			if(this.isTopColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMaxY());
				this.setVely(0);
			}
			
			if(this.isBottomColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMinY() - this.height);
				this.setVely(0);
				this.setFalling(false);
			}
			else {
				this.setFalling(true);
			}
			
			if(this.isLeftColliding(gameObject) && gameObject != this) {
				this.setX((int)gameObject.getMaxX());
			}
			
			if(this.isRightColliding(gameObject) && gameObject != this) {
				this.setX((int)gameObject.getMinX() - this.width);
			}
		}
	}
}
