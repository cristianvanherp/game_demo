package game_demo;

import java.awt.Rectangle;

public class Bound extends Rectangle {
	
	public boolean collides(Bound bound) {
		if(this.getMaxX() >= bound.getMinX() && this.getMinX() <= bound.getMaxX()) {
			if((this.getMinY() <= bound.getMaxY() && this.getMinY() >= bound.getMinY()) || (this.getMaxY() >= bound.getMinY() && this.getMaxY() <= bound.getMaxY())) {
				return true;
			}
		}
		
		return false;
	}
}
