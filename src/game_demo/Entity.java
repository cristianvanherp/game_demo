package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Entity extends GameObject {
	private Rectangle boundaryLeft, boundaryRight, boundaryTop, boundaryBottom;
	private static int sideBoundHeightRate = 75;
	private static int sideBoundWidthRate = 15;
	private static int centerBoundHeightRate = 50;
	private static int centerBoundWidthRate = 60;
	
	public Entity(int width, int height) {
		super(width, height);
	}
	
	public Entity(int width, int height, int x, int y) {
		super(width, height, x, y);
	}
	
	//Game Object methods
	public void tick() {
		
	}

	public void animate() {
		
	}
	
	public void render(Graphics g, Canvas canvas) {
		
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
}
