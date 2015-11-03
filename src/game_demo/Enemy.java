package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

public class Enemy extends Entity {

	private static final long serialVersionUID = 8526821172606415336L;

	public Enemy(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
		super(width, height, x, y, spriteSheet, affectedByGravity);
		this.setVelx(this.getSpeed());
		this.getSpriteSheet().setCurrentRow(this.getSpriteSheet().getRowRight());
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		
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
				this.setVelx(this.getSpeed());
				this.getSpriteSheet().setCurrentRow(this.getSpriteSheet().getRowRight());
			}
			
			if(this.isRightColliding(gameObject) && gameObject != this) {
				this.setX((int)gameObject.getMinX() - this.width);
				this.setVelx(-this.getSpeed());
				this.getSpriteSheet().setCurrentRow(this.getSpriteSheet().getRowLeft());
			}
		}
	}

}
