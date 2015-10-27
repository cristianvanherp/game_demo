package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public abstract class GameObject extends Bound {
	private Bound boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	private int velx, vely, speed;
	private SpriteSheet spriteSheet;
	private boolean falling;
	private int jumpSpeed;
	private boolean canJump;
	private boolean affectedByGravity;
	
	public GameObject(int width, int height, int x, int y, int speed, SpriteSheet spriteSheet, boolean affectedByGravity, int jumpSpeed) {
		this.width = width;
		this.height = height;
		this.boundaryLeft = new Bound();
		this.boundaryRight = new Bound();
		this.boundaryTop = new Bound();
		this.boundaryBottom = new Bound();
		this.velx = this.vely = 0;
		this.speed = speed;
		this.spriteSheet = spriteSheet;
		this.falling = false;
		this.jumpSpeed = jumpSpeed;
		this.canJump = false;
		this.x = x;
		this.y = y;
		this.affectedByGravity = affectedByGravity;
		if(this.affectedByGravity)
			this.vely = -1;

		
		try {
			this.validateDimensions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.adjustBoundaries();
	}
	
	public abstract void tick(List<GameObject> gameObjects, int gravity, int maxFallSpeed);
	public abstract void render(Graphics g, Canvas canvas, Camera camera);
	public abstract void animate();
	public abstract void keyEvent(KeyboardInputListener keyboardInputListener);
	
	public void renderBoundaries(Graphics g, Canvas canvas) {
		g.setColor(new Color(255, 255, 255));
		g.drawRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
		g.setColor(new Color(0, 255, 0));
		g.drawRect((int)this.boundaryLeft.getX(), (int)this.boundaryLeft.getY(), (int)this.boundaryLeft.getWidth(), (int)this.boundaryLeft.getHeight());
		g.drawRect((int)this.boundaryRight.getX(), (int)this.boundaryRight.getY(), (int)this.boundaryRight.getWidth(), (int)this.boundaryRight.getHeight());
		g.setColor(new Color(255, 0, 0));
		g.drawRect((int)this.boundaryTop.getX(), (int)this.boundaryTop.getY(), (int)this.boundaryTop.getWidth(), (int)this.boundaryTop.getHeight());
		g.drawRect((int)this.boundaryBottom.getX(), (int)this.boundaryBottom.getY(), (int)this.boundaryBottom.getWidth(), (int)this.boundaryBottom.getHeight());
	}
	
	//Getters and Setters
	public void setX(int x) {
		this.x = x;
		this.adjustBoundaries();
	}
	
	public void setY(int y) {
		this.y = y;
		this.adjustBoundaries();
	}
	
	public void setVelx(int velx) {
		this.velx = velx;
	}
	
	public int getVelx() {
		return this.velx;
	}
	
	public void setVely(int vely) {
		this.vely = vely;
	}
	
	public int getVely() {
		return this.vely;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		if(!falling)
			this.setCanJump(true);
		
		this.falling = falling;
	}

	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public boolean isAffectedByGravity() {
		return affectedByGravity;
	}

	public void setAffectedByGravity(boolean affectedByGravity) {
		this.affectedByGravity = affectedByGravity;
	}

	public boolean canJump() {
		return this.canJump;
	}
	
	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	public Bound getBoundaryLeft() {
		return this.boundaryLeft;
	}
	
	public Bound getBoundaryRight() {
		return this.boundaryRight;
	}
	
	public Bound getBoundaryTop() {
		return this.boundaryTop;
	}
	
	public Bound getBoundaryBottom() {
		return this.boundaryBottom;
	}
	
	//Utility methods
	private void adjustBoundaryLeft() {
		this.boundaryLeft.width = (int)((this.getWidth()/100) * GameObject.sideBoundWidthRate);
		this.boundaryLeft.height = (int)((this.getHeight()/100) * GameObject.sideBoundHeightRate);
		this.boundaryLeft.x = (int)this.getX();
		this.boundaryLeft.y = (int)this.getY() + (int)(this.getHeight() - this.boundaryLeft.getHeight()) / 2;
	}
	
	private void adjustBoundaryRight() {
		this.boundaryRight.width = (int)((this.getWidth()/100) * GameObject.sideBoundWidthRate);
		this.boundaryRight.height = (int)((this.getHeight()/100) * GameObject.sideBoundHeightRate);
		this.boundaryRight.x = (int)(this.getX() + this.getWidth()) - (int)this.boundaryRight.getWidth();
		this.boundaryRight.y = (int)this.getY() + (int)(this.getHeight() - this.boundaryRight.getHeight()) / 2; 
	}
	
	private void adjustBoundaryTop() {
		this.boundaryTop.width = (int)((this.getWidth()/100) * GameObject.centerBoundWidthRate);
		this.boundaryTop.height = (int)((this.getHeight()/100) * GameObject.centerBoundHeightRate);
		this.boundaryTop.x = (int)(this.getX() + (this.getWidth() / 2)) - (int)(this.boundaryTop.getWidth() / 2);
		this.boundaryTop.y = (int)this.getY();
	}
	
	private void adjustBoundaryBottom() {
		this.boundaryBottom.width = (int)((this.getWidth()/100) * GameObject.centerBoundWidthRate);
		this.boundaryBottom.height = (int)((this.getHeight()/100) * GameObject.centerBoundHeightRate);
		this.boundaryBottom.x = (int)(this.getX() + (this.getWidth() / 2)) - (int)(this.boundaryBottom.getWidth() / 2);
		this.boundaryBottom.y = (int)this.getY() + (int)(this.getHeight() / 2);
	}
		
	private void adjustBoundaries() {
		this.adjustBoundaryLeft();
		this.adjustBoundaryRight();
		this.adjustBoundaryTop();
		this.adjustBoundaryBottom();
	}
	
	protected boolean isBottomColliding(GameObject gameObject) {
		if(this.getBoundaryBottom().intersects(gameObject))
			return true;
		return false;
	}
	
	protected boolean isTopColliding(GameObject gameObject) {
		if(this.getBoundaryTop().intersects(gameObject))
			return true;
		return false;
	}
	
	protected boolean isLeftColliding(GameObject gameObject) {
		if(this.getBoundaryLeft().intersects(gameObject))
			return true;
		return false;
	}
	
	protected boolean isRightColliding(GameObject gameObject) {
		if(this.getBoundaryRight().intersects(gameObject))
			return true;
		return false;
	}
	
	public void jump() {
		if(this.canJump()) {
			this.setCanJump(false);
			this.setVely(-this.jumpSpeed);
		}
	}
	
	//Validation methods
	private void validateDimensions() throws Exception {
		if(this.getWidth() % 2 != 0 || this.getHeight() % 2 != 0) {
			throw new Exception("Invalid dimensions");
		}
	}
}
