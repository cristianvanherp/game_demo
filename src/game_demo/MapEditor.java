package game_demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

public class MapEditor extends Space {
	private int blockWidth, blockHeight, moveSpeed;
	private GameObject selectedGameObject;

	public MapEditor(double FPS, double TPS, double APS, String backgroundPath, int gravity, int maxFallingSpeed, int blockWidth, int blockHeight, int moveSpeed, String mapPath) {
		super(FPS, TPS, APS, backgroundPath, gravity, maxFallingSpeed, mapPath);
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		this.moveSpeed = moveSpeed;
		GameObjectFactory.configure(this.getMap());
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
		this.map.render(g, this, this.getCamera());
		
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
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_S)) {
			this.getMap().serialize(this.getMapPath());
		}
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_RIGHT)) {
			this.getCamera().moveRight(this.moveSpeed);
		}
		else if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_LEFT)) {
			this.getCamera().moveLeft(this.moveSpeed);
		}
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_UP)) {
			this.getCamera().moveUp(this.moveSpeed);
		}
		else if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_DOWN)) {
			this.getCamera().moveDown(this.moveSpeed);
		}
//		this.map.keyEvent(this.getKeyboardInputListener());
	}
	
	public void mouseEvent(MouseEvent mouseEvent) {
		Point point;
		
		if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
			Block block = new Block(40, 40, mouseEvent.getX() + this.getCamera().getCurrentOffsetX(), mouseEvent.getY() + this.getCamera().getCurrentOffsetY(), new SpriteSheet("/block_grass_1.png", 1, 1, 40, 40, 0, 0, 0, 0), false);
			block = GameObjectFactory.getInstance().createBlock("/block_grass_1.png", 1, 1);
			block.setX(mouseEvent.getX() + this.getCamera().getCurrentOffsetX());
			block.setY(mouseEvent.getY() + this.getCamera().getCurrentOffsetY());
			this.map.addGameObject(block);
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON2) {
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
			Player player = new Player(40, 40, mouseEvent.getX() + this.getCamera().getCurrentOffsetX(), mouseEvent.getY() + this.getCamera().getCurrentOffsetY(), new SpriteSheet("/spritesheet_1_player.png", 4, 3, 32, 32, 1, 2, 4, 0), true);
			this.map.addGameObject(player);
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

}
