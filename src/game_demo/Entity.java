package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;

public class Entity extends GameObject {
	public Entity(int width, int height, int speed, String spriteSheetPath, boolean affectedByGravity, int jumpSpeed) {
		super(width, height, speed, spriteSheetPath, affectedByGravity, jumpSpeed);
	}
	
	public Entity(int width, int height, int x, int y, int speed, String spriteSheetPath, boolean affectedByGravity, int jumpSpeed) {
		super(width, height, x, y, speed, spriteSheetPath, affectedByGravity, jumpSpeed);
	}
	
	//Game Object methods
	public void tick(List<GameObject> gameObjects, int gravity, int maxFallSpeed) {
		if(this.isFalling() && this.getVely() <= maxFallSpeed)
			this.setVely(this.getVely() + gravity);
		
		this.setX(this.x + this.getVelx());
		this.setY(this.y + this.getVely());
		this.handleCollision(gameObjects);
	}

	public void animate() {
		if(this.getVelx() != 0) {
			if(this.getCurrentSpriteCol() < 2)
				this.setCurrentSpriteCol(this.getCurrentSpriteCol() + 1);
			else {
				this.setCurrentSpriteCol(0);
			}
		}
	}
	
	public void render(Graphics g, Canvas canvas) {
		g.drawImage(this.getSpriteSheet().slice(this.getCurrentSpriteCol(), this.getCurrentSpriteRow(), this.width, this.height), (int)this.getX(), (int)this.getY(), canvas);	
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_LEFT)) {
			this.setVelx(-this.getSpeed());
			this.setCurrentSpriteRow(1);
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_RIGHT)) {
			this.setVelx(this.getSpeed());
			this.setCurrentSpriteRow(2);
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
				this.setY((int)gameObject.getMaxY() + 1);
				this.setVely(0);
			}
			
			if(this.isBottomColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMinY() - 1 - this.height);
				this.setVely(0);
				this.setFalling(false);
			}
			else {
				this.setFalling(true);
			}
			
			if(this.isLeftColliding(gameObject) && gameObject != this) {
				this.setX((int)gameObject.getMaxX() + 1);
			}
			
			if(this.isRightColliding(gameObject) && gameObject != this) {
				this.setX((int)gameObject.getMinX() - 1 - this.width);
			}
		}
	}
}
