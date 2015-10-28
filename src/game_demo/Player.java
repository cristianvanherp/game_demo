package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity {

	private static final long serialVersionUID = 8526821172606415336L;

	public Player(int width, int height, int x, int y, int speed, SpriteSheet spriteSheet, boolean affectedByGravity, int jumpSpeed) {
		super(width, height, x, y, speed, spriteSheet, affectedByGravity, jumpSpeed);
	}

}
