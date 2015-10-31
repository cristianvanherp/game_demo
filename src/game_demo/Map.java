package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Map implements Serializable {
	private String backgroundPath;
	private transient BufferedImage background;
	private List<List<Thing>> things;
	private List<Entity> entities;
	private int gravity, maxFallingSpeed, minItemWidth, minItemHeight;
	
	public Map(String backgroundPath, int gravity, int maxFallingSpeed, int minItemWidth, int minItemHeight) {
		this.backgroundPath = backgroundPath;
		this.gravity = gravity;
		this.maxFallingSpeed = maxFallingSpeed;
		this.minItemWidth = minItemWidth;
		this.minItemHeight = minItemHeight;
		this.things = new ArrayList<List<Thing>>();
		this.entities = new ArrayList<Entity>();
		
		try {
			this.background = ImageIO.read(getClass().getResource(this.backgroundPath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map(int gravity, int maxFallingSpeed, int minItemWidth, int minItemHeight) {
		this.backgroundPath = backgroundPath;
		this.gravity = gravity;
		this.maxFallingSpeed = maxFallingSpeed;
		this.things = new ArrayList<List<Thing>>();
		this.entities = new ArrayList<Entity>();
		this.minItemWidth = minItemWidth;
		this.minItemHeight = minItemHeight;
	}
	
	public void render(Graphics g, Canvas canvas, Camera camera) {
		if(this.background != null)
			g.drawImage(this.background, 0, 0, canvas);
		
		for(Thing thing: this.getOnScreenThings(camera)) {
			if(thing != null) {
				if(thing != null)
					thing.render(g, canvas, camera);
//					thingCol.renderBoundaries(g, canvas);
			}
		}
		
		for(Entity entity: this.entities) {
			if(entity != null) {
				entity.render(g, canvas, camera);
			}
		}
	}
	
	public void tick(Camera camera) {
		for(Thing thing: this.getOnScreenThings(camera)) {
			if(thing != null) {
				thing.tick(this.getGameObjectsCollision(thing), this.getGravity(), this.getMaxFallingSpeed());	
			}
		}
		for(Entity entity: this.entities) {
			if(entity != null) {
				entity.tick(this.getGameObjectsCollision(entity), this.getGravity(), this.getMaxFallingSpeed());	
			}
		}
		
		this.getOnScreenThings(camera);
	}
	
	public void animate(Camera camera) {
		for(Thing thing: this.getOnScreenThings(camera)) {
			if(thing != null) {
				thing.animate();
			}
		}
		
		for(Entity entity: this.entities) {
			if(entity != null) {
				entity.animate();	
			}
		}
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		for(List<Thing> thingRow: this.things) {
			for(Thing thingCol: thingRow) {
				if(thingCol != null)
					thingCol.keyEvent(keyboardInputListener);
			}
		}
		for(Entity entity: this.entities) {
			if(entity != null) {
				entity.keyEvent(keyboardInputListener);	
			}
		}
	}
	
	//Utility functions
	public boolean addGameObject(GameObject gameObject) {
		Point validPoint = this.getClosestValidPoint((int)gameObject.getX(), (int)gameObject.getY());
		
		if(!this.isPositionValid((int)validPoint.getX(), (int)validPoint.getY(), (int)gameObject.getWidth(), (int)gameObject.getHeight())) {
			return false;
		}
		else {
			gameObject.setX((int)validPoint.getX());
			gameObject.setY((int)validPoint.getY());
		}
		
		if(gameObject instanceof Entity) {
			this.entities.add((Entity)gameObject);
		}
		else if(gameObject instanceof Thing) {
			int row = (int)gameObject.getY() / this.getMinItemHeight();
			int col = (int)gameObject.getX() / this.getMinItemWidth();
			List<GameObject> auxRow;
			GameObject auxCol;
			
			try {
				this.things.get(row);
			}
			catch(IndexOutOfBoundsException e) {
				for(int counter = 0 ; counter <= row ; counter++) {
					this.things.add(new ArrayList<Thing>());
				}
			}
			
			try {
				this.things.get(row).get(col);
				if(this.things.get(row).get(col) == null)
					this.things.get(row).set(col, (Thing)gameObject);
			}
			catch(IndexOutOfBoundsException e) {
				for(int counter = 0 ; counter <= col ; counter++) {
					this.things.get(row).add(null);
				}
				if(this.things.get(row).get(col) == null)
					this.things.get(row).set(col, (Thing)gameObject);
			}
		}
		
		return true;
	}
	
	private boolean isPositionValid(int x, int y, int width, int height) {
		Bound testBound = new Bound(x, y, width, height);
		
		for(List<Thing> thingRow: this.things) {
			for(Thing thingCol: thingRow) {
				if(thingCol != null) {
					if(thingCol.contains(x, y) || thingCol.intersects(testBound)) {
						return false;
					}
				}
			}
		}
		
		for(Entity entity: this.entities) {
			if(entity != null) {
				if(entity.contains(x, y) || entity.intersects(testBound)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private Point getClosestValidPoint(int x, int y) {
		int validX = x - (x % this.getMinItemWidth());
		int validY = y - (y % this.getMinItemHeight());

		return new Point(validX, validY);
	}
	
	public List<GameObject> getGameObjectsCollision(GameObject gameObject) {
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		
		for(List<Thing> thingRow: this.things) {
			for(Thing thingCol: thingRow) {
				if(thingCol != null)
					gameObjects.add(thingCol);
			}
		}
		
		for(Entity entity: this.entities) {
			if(entity != null)
				gameObjects.add(entity);
		}
		
		return gameObjects;
	}

	public int countGameObjects() {
		int count = 0;
		
		for(List<Thing> thingRow: this.things) {
			for(Thing thingCol: thingRow) {
				if(thingCol != null)
					count++;
			}
		}
		
		for(Entity entity: this.entities) {
			if(entity != null)
				count++;
		}
		
		return count;
	}
	
	public void reload() {
		try {
			this.background = ImageIO.read(getClass().getResource(this.backgroundPath));
			for(List<Thing> thingRow: this.things) {
				for(Thing thingCol: thingRow) {
					if(thingCol != null)
						thingCol.getSpriteSheet().reload();
				}
			}
			for(Entity entity: this.entities) {
				if(entity != null)
					entity.getSpriteSheet().reload();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serialize(String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(this);
			objOut.close();
			fileOut.close();
			System.out.println("Map " + path + " saved with success");
		}
		catch(IOException e) {
			System.out.println("Error while saving map: " + e.getMessage());	
		}
	}
	
	public Player getPlayer() {
		for(Entity entity: this.entities) {
			if(entity != null) {
				if(entity instanceof Player)
					return (Player)entity;
			}
		}
		
		return null;
	}
	
	public List<Thing> getOnScreenThings(Camera camera) {
		List<Thing> things = new ArrayList<Thing>();
		Thing thing;
		
		int minCol = camera.getCurrentOffsetX() / this.getMinItemWidth() - 3;
		int maxCol = (camera.getCurrentOffsetX() + Window.WIDTH) / this.getMinItemWidth() + 3;
		int minRow = camera.getCurrentOffsetY() / this.getMinItemHeight() - 3;
		int maxRow = (camera.getCurrentOffsetY() + Window.HEIGHT) / this.getMinItemHeight() + 3;
		
		if(minCol < 0)
			minCol = 0;
		if(minRow < 0)
			minRow = 0;
		
		for(int row = minRow ; row <= maxRow ; row++) {
			for(int col = minCol ; col <= maxCol ; col++) {
				try {
					thing = this.things.get(row).get(col);
					if(thing != null)
						things.add(thing);
				}
				catch(IndexOutOfBoundsException e) {
					
				}
			}
		}
		
//		System.out.printf("minCol: %d, maxCol: %d --- minRow: %d, maxRow: %d -- count: %d\n", minCol, maxCol, minRow, maxRow, things.size());
		
		return things;
	}

	//Getters and Setters
	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public BufferedImage getBackground() {
		return background;
	}

	public void setBackground(BufferedImage background) {
		this.background = background;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public int getMaxFallingSpeed() {
		return maxFallingSpeed;
	}

	public void setMaxFallingSpeed(int maxFallingSpeed) {
		this.maxFallingSpeed = maxFallingSpeed;
	}

	public int getMinItemWidth() {
		return minItemWidth;
	}

	public void setMinItemWidth(int minItemWidth) {
		this.minItemWidth = minItemWidth;
	}

	public int getMinItemHeight() {
		return minItemHeight;
	}

	public void setMinItemHeight(int minItemHeight) {
		this.minItemHeight = minItemHeight;
	}

	public List<List<Thing>> getThings() {
		return things;
	}

	public void setThings(List<List<Thing>> things) {
		this.things = things;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
}