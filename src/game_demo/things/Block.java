package game_demo.things;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import game_demo.bases.GameObject;
import game_demo.bases.Thing;
import game_demo.listeners.KeyboardInputListener;
import game_demo.primitives.SpriteSheet;

public class Block extends Thing {

	public Block(int width, int height, int x, int y, SpriteSheet spriteSheet, boolean affectedByGravity) {
		super(width, height, x, y, spriteSheet, affectedByGravity);
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
