package game_demo;

import java.awt.Rectangle;

public class Bound extends Rectangle {
	
	public Bound() {
		super();
	}
	
	public Bound(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
//	public boolean collides(Bound bound) {
//		if(this.getMaxX() >= bound.getMinX() && this.getMinX() <= bound.getMaxX()) {
//			if((this.getMinY() <= bound.getMaxY() && this.getMinY() >= bound.getMinY()) || (this.getMaxY() >= bound.getMinY() && this.getMaxY() <= bound.getMaxY())) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
//	public boolean intersects(int x, int y) {
//		if(x >= this.getMinX() && x <= this.getMaxX()) {
//			if(y >= this.getMinY() && y <= this.getMaxY()) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
}
