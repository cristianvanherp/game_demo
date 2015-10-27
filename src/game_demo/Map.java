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
	private List<List<GameObject>> gameObjects;
	private int gravity, maxFallingSpeed, minItemWidth, minItemHeight;
	
	public Map(String backgroundPath, int gravity, int maxFallingSpeed, int minItemWidth, int minItemHeight) {
		this.backgroundPath = backgroundPath;
		this.gravity = gravity;
		this.maxFallingSpeed = maxFallingSpeed;
		this.minItemWidth = minItemWidth;
		this.minItemHeight = minItemHeight;
		this.gameObjects = new ArrayList<List<GameObject>>();
		
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
		this.gameObjects = new ArrayList<List<GameObject>>();
		this.minItemWidth = minItemWidth;
		this.minItemHeight = minItemHeight;
	}
	
	//Utility methods
	public void render(Graphics g, Canvas canvas, Camera camera) {
		if(this.background != null)
			g.drawImage(this.background, 0, 0, canvas);
		
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null)
					gameObjectCol.render(g, canvas, camera);
//					gameObjectCol.renderBoundaries(g, canvas);
			}
		}
	}
	
	public void tick() {
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null) {
					gameObjectCol.tick(this.getGameObjectsCollision(gameObjectCol), this.getGravity(), this.getMaxFallingSpeed());	
				}
			}
		}
	}
	
	public void animate() {
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null)
					gameObjectCol.animate();
			}
		}
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null)
					gameObjectCol.keyEvent(keyboardInputListener);
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
		
		int row = (int)gameObject.getY() / this.getMinItemHeight();
		int col = (int)gameObject.getX() / this.getMinItemWidth();
		List<GameObject> auxRow;
		GameObject auxCol;
		
		try {
			this.gameObjects.get(row);
		}
		catch(IndexOutOfBoundsException e) {
			for(int counter = 0 ; counter <= row ; counter++) {
				this.gameObjects.add(new ArrayList<GameObject>());
			}
		}
		
		try {
			this.gameObjects.get(row).get(col);
			if(this.gameObjects.get(row).get(col) == null)
				this.gameObjects.get(row).set(col, gameObject);
		}
		catch(IndexOutOfBoundsException e) {
			for(int counter = 0 ; counter <= col ; counter++) {
				this.gameObjects.get(row).add(null);
			}
			if(this.gameObjects.get(row).get(col) == null)
				this.gameObjects.get(row).set(col, gameObject);
		}
		
		return true;
	}
	
	private boolean isPositionValid(int x, int y, int width, int height) {
		Bound testBound = new Bound(x, y, width, height);
		
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null) {
					if(gameObjectCol.contains(x, y) || gameObjectCol.intersects(testBound)) {
						return false;
					}
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
		
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null)
					gameObjects.add(gameObjectCol);
			}
		}
		
		return gameObjects;
	}

	public int countGameObjects() {
		int count = 0;
		
		for(List<GameObject> gameObjectRow: this.gameObjects) {
			for(GameObject gameObjectCol: gameObjectRow) {
				if(gameObjectCol != null)
					count++;
			}
		}
		
		return count;
	}
	
	public void reload() {
		try {
			this.background = ImageIO.read(getClass().getResource(this.backgroundPath));
			for(List<GameObject> gameObjectRow: this.gameObjects) {
				for(GameObject gameObjectCol: gameObjectRow) {
					if(gameObjectCol != null)
						gameObjectCol.getSpriteSheet().reload();
				}
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

	public List<List<GameObject>> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(List<List<GameObject>> gameObjects) {
		this.gameObjects = gameObjects;
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
	
}