package game_demo.things;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

import game_demo.bases.GameObject;
import game_demo.listeners.KeyboardInputListener;
import game_demo.primitives.SpriteSheet;

public class Player extends Entity {

	private static final long serialVersionUID = 8526821172606415336L;

	public Player(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
		super(width, height, x, y, spriteSheet, affectedByGravity);
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
	protected void handleCollision(List<GameObject> gameObjects) {
		for(GameObject gameObject: gameObjects) {
			if(this.isTopColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMaxY());
				this.setVely(0);
			}
			
			if(this.isBottomColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMinY() - this.height);
				this.setFalling(false);
				if(gameObject instanceof Entity)
					this.setVely(-this.getJumpSpeed());
				else
					this.setVely(0);
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
