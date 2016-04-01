package game_demo.primitives;

public class Camera {
	private int maxOffsetX, maxOffsetY, currentOffsetX, currentOffsetY;
	
	public Camera() {
		this.maxOffsetX = -1;
		this.maxOffsetY = -1;
	}
	
	public Camera(int maxOffsetX, int maxOffsetY) {
		this.maxOffsetX = maxOffsetX;
		this.maxOffsetY = maxOffsetY;
	}

	//Getters and Setters
	public int getMaxOffsetX() {
		return maxOffsetX;
	}

	public void setMaxOffsetX(int maxOffsetX) {
		this.maxOffsetX = maxOffsetX;
	}

	public int getMaxOffsetY() {
		return maxOffsetY;
	}

	public void setMaxOffsetY(int maxOffsetY) {
		this.maxOffsetY = maxOffsetY;
	}

	public int getCurrentOffsetX() {
		return currentOffsetX;
	}
	
	public int getCurrentOffsetY() {
		return currentOffsetY;
	}
	
	public void setCurrentOffsetX(int currentOffsetX) {
		this.currentOffsetX = currentOffsetX;
	}

	public void setCurrentOffsetY(int currentOffsetY) {
		this.currentOffsetY = currentOffsetY;
	}

	//Utility methods
	public void moveRight(int pixels) {
		if(this.maxOffsetX != -1) {
			if(this.currentOffsetX + pixels > this.maxOffsetX)
				return;
		}
		this.currentOffsetX += pixels;
	}
	
	public void moveLeft(int pixels) {
		if(this.currentOffsetX - pixels < 0)
			return;
		this.currentOffsetX -= pixels;
	}
	
	public void moveDown(int pixels) {
		if(this.maxOffsetY != -1) {
			if(this.currentOffsetY + pixels > this.maxOffsetY)
				return;
		}
		this.currentOffsetY += pixels;
	}
	
	public void moveUp(int pixels) {
		if(this.currentOffsetY - pixels < 0)
			return;
		this.currentOffsetY -= pixels;
	}
}
