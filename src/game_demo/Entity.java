package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Entity extends Rectangle implements GameObject {
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	
	public Entity(int width, int height) {
		this.width = width;
		this.height = height;
		this.boundaryLeft = new Rectangle();
		this.boundaryRight = new Rectangle();
		this.boundaryTop = new Rectangle();
		this.boundaryBottom = new Rectangle();
		this.x = this.y = 0;

		try {
			this.validateDimensions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.adjustBoundaries();
	}
	
	public Entity(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.boundaryLeft = new Rectangle();
		this.boundaryRight = new Rectangle();
		this.boundaryTop = new Rectangle();
		this.boundaryBottom = new Rectangle();
		this.x = x;
		this.y = y;
		
		try {
			this.validateDimensions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.adjustBoundaries();
	}
	
	//Game Object methods
	public void tick() {
		
	}

	public void animate() {
		
	}
	
	public void render(Graphics g, Canvas canvas) {
		
	}
	
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
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_LEFT)) {
			
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_RIGHT)) {
		
		}
		else {
			
		}
		
		if(keyboardInputListener.isKeyDown(KeyEvent.VK_UP)) {
			
		}
		else if(keyboardInputListener.isKeyDown(KeyEvent.VK_DOWN)) {
		
		}
		else {
			
		}
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
	
	//Utility methods
	private void adjustBoundaryLeft() {
		this.boundaryLeft.width = (int)((this.getWidth()/100) * Entity.sideBoundWidthRate);
		this.boundaryLeft.height = (int)((this.getHeight()/100) * Entity.sideBoundHeightRate);
		this.boundaryLeft.x = (int)this.getX();
		this.boundaryLeft.y = (int)this.getY() + (int)(this.getHeight() - this.boundaryLeft.getHeight()) / 2;
	}
	
	private void adjustBoundaryRight() {
		this.boundaryRight.width = (int)((this.getWidth()/100) * Entity.sideBoundWidthRate);
		this.boundaryRight.height = (int)((this.getHeight()/100) * Entity.sideBoundHeightRate);
		this.boundaryRight.x = (int)(this.getX() + this.getWidth()) - (int)this.boundaryRight.getWidth();
		this.boundaryRight.y = (int)this.getY() + (int)(this.getHeight() - this.boundaryRight.getHeight()) / 2; 
	}
	
	private void adjustBoundaryTop() {
		this.boundaryTop.width = (int)((this.getWidth()/100) * Entity.centerBoundWidthRate);
		this.boundaryTop.height = (int)((this.getHeight()/100) * Entity.centerBoundHeightRate);
		this.boundaryTop.x = (int)(this.getX() + (this.getWidth() / 2)) - (int)(this.boundaryTop.getWidth() / 2);
		this.boundaryTop.y = (int)this.getY();
	}
	
	private void adjustBoundaryBottom() {
		this.boundaryBottom.width = (int)((this.getWidth()/100) * Entity.centerBoundWidthRate);
		this.boundaryBottom.height = (int)((this.getHeight()/100) * Entity.centerBoundHeightRate);
		this.boundaryBottom.x = (int)(this.getX() + (this.getWidth() / 2)) - (int)(this.boundaryBottom.getWidth() / 2);
		this.boundaryBottom.y = (int)this.getY() + (int)(this.getHeight() / 2);
	}
	
	private void adjustBoundaries() {
		this.adjustBoundaryLeft();
		this.adjustBoundaryRight();
		this.adjustBoundaryTop();
		this.adjustBoundaryBottom();
	}
	
	//Validation methods
	private void validateDimensions() throws Exception {
		if(this.getWidth() % 2 != 0 || this.getHeight() % 2 != 0) {
			throw new Exception("Invalid dimensions");
		}
	}
	
}
