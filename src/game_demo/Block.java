package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Block extends GameObject {
	
	public Block(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
		super(width, height, x, y, spriteSheet, affectedByGravity);
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
		g.drawImage(this.getSpriteSheet().slice(0, 0, this.getSpriteSheet().getSpriteWidth(), this.getSpriteSheet().getSpriteHeight()), (int)this.getX() - camera.getCurrentOffsetX(), (int)this.getY() - camera.getCurrentOffsetY(), (int)this.getWidth(), (int)this.getHeight(), canvas);
	}
	
	public void animate() {
		this.getSpriteSheet().animate();
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		
	}
	
	public void handleCollision(List<GameObject> gameObjects) {
		for(GameObject gameObject: gameObjects) {
			if(this.isBottomColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMinY() - this.height);
				this.setVely(0);
				if(this.isAffectedByGravity()) {
					this.setFalling(false);
					this.jump();
				}
			}
			else {
				if(this.isAffectedByGravity())
					this.setFalling(true);
			}
		}
	}
	
}
