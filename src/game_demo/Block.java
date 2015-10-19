package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Block extends GameObject {
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	
	public Block(int width, int height, int speed, String spriteSheetPath, boolean affectedByGravity) {
		super(width, height, speed, spriteSheetPath, affectedByGravity, 5);
	}
	
	public Block(int width, int height, int x, int y, int speed, String spriteSheetPath, boolean affectedByGravity) {
		super(width, height, x, y, speed, spriteSheetPath, affectedByGravity, 5);
	}
	
	public Block(int width, int height, int x, int y, int speed, String spriteSheetPath, boolean affectedByGravity, int jumpSpeed) {
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
		
	}
	
	public void render(Graphics g, Canvas canvas) {
		g.drawImage(this.getSpriteSheet().slice(0, 0, 40, 40), (int)this.getX(), (int)this.getY(), canvas);
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		
	}
	
	public void handleCollision(List<GameObject> gameObjects) {
		for(GameObject gameObject: gameObjects) {
			if(this.isBottomColliding(gameObject) && gameObject != this) {
				this.setY((int)gameObject.getMinY() - 1 - this.height);
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
