package game_demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class MapEditor extends Space {
	private int blockWidth, blockHeight;

	public MapEditor(double FPS, double TPS, double APS, String backgroundPath, int gravity, int maxFallingSpeed, int blockWidth, int blockHeight) {
		super(FPS, TPS, APS, backgroundPath, gravity, maxFallingSpeed);
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
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
		this.map.keyEvent(this.getKeyboardInputListener());
	}
	
	public void mouseEvent(MouseEvent mouseEvent) {
		Point point;
		
		if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
			point = getClosestValidPoint(mouseEvent.getX(), mouseEvent.getY());
			this.getMap().getGameObjects().add(new Block(40, 40, (int)point.getX(), (int)point.getY(), 5, new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false, 10));
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON2) {
			
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
			point = getClosestValidPoint(mouseEvent.getX(), mouseEvent.getY());
			this.getMap().getGameObjects().add(new Entity(50, 70, (int)point.getX(), (int)point.getY(), 3, new SpriteSheet("/spritesheet_1_player.png", 4, 3, 32, 32, 1, 2, 4, 0), true, 17));
		}
	}
	
	//Getters and Setters
	public int getBlockWidth() {
		return blockWidth;
	}

	public void setBlockWidth(int blockWidth) {
		this.blockWidth = blockWidth;
	}

	public int getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(int blockHeight) {
		this.blockHeight = blockHeight;
	}
	
	//Utility Methods
	public boolean isPointValid(int x, int y) {
		if(x % this.getBlockWidth() != 0 || y % this.getBlockHeight() != 0) {
			return false;
		}
		
		return true;
	}
	
	public Point getClosestValidPoint(int x, int y) {
		if(this.isPointValid(x, y))
			return new Point(x, y);
		
		int validX = x - (x % this.getBlockWidth());
		int validY = y - (y % this.getBlockHeight());

		return new Point(validX, validY);
	}

}
