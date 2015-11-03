package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public abstract class Thing extends GameObject {
	
	public Thing(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
		super(width, height, x, y, spriteSheet, affectedByGravity);
	}
	
	//Game Object methods
	public void tick(List<GameObject> gameObjects, float gravity, int maxFallSpeed) {
		if(this.isFalling() && this.getVely() <= maxFallSpeed)
			this.setVely(this.getVely() + gravity);
		
		this.setX(this.x + (int)this.getVelx());
		this.setY(this.y + (int)this.getVely());
		
		if(this.getVelx() != 0 || this.getVely() != 0)
			this.handleCollision(gameObjects);
	}
	
	public void render(Graphics g, Canvas canvas, Camera camera) {
		g.drawImage(this.getSpriteSheet().getCurrent(), (int)this.getX() - camera.getCurrentOffsetX(), (int)this.getY() - camera.getCurrentOffsetY(), (int)this.getWidth(), (int)this.getHeight(), canvas);
	}
	
	public void animate() {
		this.getSpriteSheet().animate();
	}
	
	public abstract void keyEvent(KeyboardInputListener keyboardInputListener);
	public abstract void handleCollision(List<GameObject> gameObjects);
	
}
