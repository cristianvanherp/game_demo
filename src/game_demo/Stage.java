package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Stage extends Space {
	public Entity entity;
	
	public Stage(double FPS, double TPS, double APS, String backgroundPath, int gravity, int maxFallingSpeed) {
		super(FPS, TPS, APS, backgroundPath, gravity, maxFallingSpeed);
		this.entity = new Entity(50, 70, 100, 100, 3, new SpriteSheet("/spritesheet_1_player.png", 4, 3, 32, 32, 1, 2, 4, 0), true, 17);
		this.getMap().getGameObjects().add(entity);
		this.getMap().getGameObjects().add(new Block(40, 40, 182, 177, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), true, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 141, 217, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), true, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 100, 7, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), true, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 100, 318, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 100, 359, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 100, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 141, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 182, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 223, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 264, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 305, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 346, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 387, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 428, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		this.getMap().getGameObjects().add(new Block(40, 40, 469, 400, 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		this.map.render(g, this);
		
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		this.map.tick();
	}
	
	public void animate() {
		this.map.animate();
	}
	
	public void keyEvent() {
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.stop();
		}
		this.entity.keyEvent(this.getKeyboardInputListener());
	}
}
