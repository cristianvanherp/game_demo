package game_demo;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Entity implements GameObject {
	private int posX, posY;
	private Dimension dimension;
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;

	public Entity(int width, int height) {
		this.dimension = new Dimension(width, height);
		this.boundaryLeft = new Rectangle();
		this.boundaryRight = new Rectangle();
		this.boundaryTop = new Rectangle();
		this.boundaryBottom = new Rectangle();
		this.setPosX(0);
		this.setPosY(0);
		
		try {
			this.validateDimensions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.adjustBoundaryLeft();
		this.adjustBoundaryRight();
		this.adjustBoundaryTop();
		this.adjustBoundaryBottom();
	}
	
	public Entity(int width, int height, int posX, int posY) {
		this.dimension = new Dimension(width, height);
		this.boundaryLeft = new Rectangle();
		this.boundaryRight = new Rectangle();
		this.boundaryTop = new Rectangle();
		this.boundaryBottom = new Rectangle();
		this.setPosX(posX);
		this.setPosY(posY);
		
		try {
			this.validateDimensions();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.adjustBoundaryLeft();
		this.adjustBoundaryRight();
		this.adjustBoundaryTop();
		this.adjustBoundaryBottom();
	}
	
	//Game Object methods
	public void render(Graphics g, Canvas canvas) {
	
	}

	public void tick() {
		
	}

	public void animate() {
		
	}
	
	//Getters and Setters
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	//Utility methods
	private void adjustBoundaryLeft() {
	
	}
	
	private void adjustBoundaryRight() {
	
	}
	
	private void adjustBoundaryTop() {
		
	}
	
	private void adjustBoundaryBottom() {
		
	}
	
	//Validation methods
	private void validateDimensions() throws Exception {
		if(this.dimension.getWidth() % 2 != 0 || this.dimension.getHeight() % 2 != 0) {
			throw new Exception("Invalid dimensions");
		}
	}
	
}
