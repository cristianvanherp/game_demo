package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends GameObject {
	
	private static final long serialVersionUID = -529082995350931837L;

	public Entity(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
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
		g.drawImage(this.getSpriteSheet().slice(this.getSpriteSheet().getCurrentCol(), this.getSpriteSheet().getCurrentRow(), this.getSpriteSheet().getSpriteWidth(), this.getSpriteSheet().getSpriteHeight()), (int)this.getX() - camera.getCurrentOffsetX(), (int)this.getY() - camera.getCurrentOffsetY(), (int)this.getWidth(), (int)this.getHeight(), canvas);	
	}
	
	public void animate() {
		if(this.getVelx() != 0) {
			this.getSpriteSheet().animate();
		}
		else {
			this.getSpriteSheet().setCurrentCol(1);
		}
	}
	
	public abstract void keyEvent(KeyboardInputListener keyboardInputListener);
	
	//Utility methods
	protected abstract void handleCollision(List<GameObject> gameObjects);
}
