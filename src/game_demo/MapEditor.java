package game_demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MapEditor extends Space {
	private int blockWidth, blockHeight, moveSpeed;
	private List<Thing> things;
	private List<Entity> entities;
	private boolean listingThings;
	private boolean isDeleting;
	int currentSelectionIndex;
	private GameObject selectedGameObject;
	private Point currentCursorLocation;
	private int gameObjectsNum;

	public MapEditor(double FPS, double TPS, double APS, String backgroundPath, int gravity, int maxFallingSpeed, int blockWidth, int blockHeight, int moveSpeed, String mapPath) {
		super(FPS, TPS, APS, backgroundPath, gravity, maxFallingSpeed, mapPath);
		this.blockWidth = blockWidth;
		this.blockHeight = blockHeight;
		this.moveSpeed = moveSpeed;
		this.entities = new ArrayList<Entity>();
		this.things = new ArrayList<Thing>();
		this.currentCursorLocation = new Point(-999, -999);
		GameObjectFactory.configure(this.getMap());
		
		this.configureThings();
		this.configureEntities();
		this.currentSelectionIndex = 0;
		this.gameObjectsNum = 1;
		this.listingThings = true;
		this.selectedGameObject = this.things.get(this.currentSelectionIndex);
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
		try {
			this.map.render(g, this, this.getCamera());	
		}
		catch(ConcurrentModificationException e){}
		
		g.setColor(new Color(255, 255, 255));
		if(this.isDeleting) {
			for(int i = 0 ; i < this.gameObjectsNum ; i++) {
				if(this.isDeleting)
					g.fillRect((int)this.currentCursorLocation.getX() + (this.map.getMinItemWidth() * i), (int)this.currentCursorLocation.getY(), this.map.getMinItemWidth(), this.map.getMinItemHeight());
			}
		}
		else {
			if(this.listingThings) {
				for(int i = 0 ; i < this.gameObjectsNum ; i++)
					g.drawImage(this.selectedGameObject.getSpriteSheet().slice(this.selectedGameObject.getSpriteSheet().getCurrentCol(), this.selectedGameObject.getSpriteSheet().getCurrentRow(), this.selectedGameObject.getSpriteSheet().getSpriteWidth(), this.selectedGameObject.getSpriteSheet().getSpriteHeight()), (int)this.currentCursorLocation.getX() + ((int)this.selectedGameObject.getWidth() * i), (int)this.currentCursorLocation.getY(), (int)this.selectedGameObject.getWidth(), (int)this.selectedGameObject.getHeight(), this);
			}
			else {
				g.drawImage(this.selectedGameObject.getSpriteSheet().slice(this.selectedGameObject.getSpriteSheet().getCurrentCol(), this.selectedGameObject.getSpriteSheet().getCurrentRow(), this.selectedGameObject.getSpriteSheet().getSpriteWidth(), this.selectedGameObject.getSpriteSheet().getSpriteHeight()), (int)this.currentCursorLocation.getX(), (int)this.currentCursorLocation.getY(), (int)this.selectedGameObject.getWidth(), (int)this.selectedGameObject.getHeight(), this);		
			}
		}
		
		g.dispose();
		bs.show();
	}

	public void tick() {
		try {
			this.map.tick(this.getCamera());
		}
		catch(ConcurrentModificationException e){}
	}

	public void animate() {
		try {
			this.map.animate(this.getCamera());
		}
		catch(ConcurrentModificationException e){}
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
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_D)) {
			this.isDeleting = !this.isDeleting;
		}
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_R)) {
			this.gameObjectsNum++;
		}
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_E)) {
			if(this.gameObjectsNum > 1)
				this.gameObjectsNum--;
		}
//		this.map.keyEvent(this.getKeyboardInputListener());
	}
	
	public void mouseEvent(MouseEvent mouseEvent) {
		Point point;
		
		if(mouseEvent.getButton() == MouseEvent.BUTTON1) {
			if(this.isDeleting) {
				for(int i = 0 ; i < this.gameObjectsNum ; i++) {
					this.map.removeGameObject(this.map.getGameObject(mouseEvent.getX() + this.getCamera().getCurrentOffsetX() + (int)(i * this.map.getMinItemWidth()), mouseEvent.getY() + this.getCamera().getCurrentOffsetY()));
					
				}
			}
			else {
				if(this.listingThings) {
					for(int i = 0 ; i < this.gameObjectsNum ; i++) {
						GameObject gameObject = (GameObject)this.selectedGameObject.clone();
						gameObject.setX((mouseEvent.getX() + this.getCamera().getCurrentOffsetX()) + (int)(i*gameObject.getWidth()));
						gameObject.setY(mouseEvent.getY() + this.getCamera().getCurrentOffsetY());
						this.map.addGameObject(gameObject);
					}
				}
				else {
					GameObject gameObject = (GameObject)this.selectedGameObject.clone();
					gameObject.setX(mouseEvent.getX() + this.getCamera().getCurrentOffsetX());
					gameObject.setY(mouseEvent.getY() + this.getCamera().getCurrentOffsetY());
					this.map.addGameObject(gameObject);
				}
			}
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON2) {
			this.listingThings = !this.listingThings;
			this.currentSelectionIndex = 0;
			if(this.listingThings) {
				this.selectedGameObject = this.things.get(this.currentSelectionIndex);
			}
			else {
				this.selectedGameObject = this.entities.get(this.currentSelectionIndex);
			}
			
		}
		else if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
			if(this.listingThings == true) {
				try {
					this.selectedGameObject = this.things.get(++this.currentSelectionIndex);
				}
				catch(IndexOutOfBoundsException e) {
					this.currentSelectionIndex = 0;
					this.selectedGameObject = this.things.get(this.currentSelectionIndex);
				}
			}
			else {
				try {
					this.selectedGameObject = this.entities.get(this.entities.indexOf(this.selectedGameObject)+1);
					System.out.println(this.currentSelectionIndex);
				}
				catch(IndexOutOfBoundsException e) {
					this.currentSelectionIndex = 0;
					this.selectedGameObject = this.entities.get(this.currentSelectionIndex);
				}
			}
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		this.currentCursorLocation = e.getPoint();
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
	public void configureThings() {
		Block block;
		
		this.things.add(GameObjectFactory.getInstance().createBlock("/block_grass_1.png", 1, 1, 40, 40));
		this.things.add(GameObjectFactory.getInstance().createBlock("/block_mud_1.png", 1, 1, 40, 40));
		this.things.add(GameObjectFactory.getInstance().createBlock("/block_qmark_1.png", 1, 1, 40, 40));
	}
	
	public void configureEntities() {
		this.entities.add(GameObjectFactory.getInstance().createPlayer("/spritesheet_1_player.png", 1, 1, 40, 40));
	}
}
