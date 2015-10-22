package game_demo;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Map {
	private String backgroundPath;
	private BufferedImage background;
	private List<GameObject> gameObjects;
	private int gravity, maxFallingSpeed;
	
	public Map(String backgroundPath, int gravity, int maxFallingSpeed) {
		this.backgroundPath = backgroundPath;
		this.gravity = gravity;
		this.maxFallingSpeed = maxFallingSpeed;
		this.gameObjects = new ArrayList<GameObject>();
		
		try {
			this.background = ImageIO.read(getClass().getResource(this.backgroundPath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map(int gravity, int maxFallingSpeed) {
		this.backgroundPath = backgroundPath;
		this.gravity = gravity;
		this.maxFallingSpeed = maxFallingSpeed;
		this.gameObjects = new ArrayList<GameObject>();
	}
	
	//Utility methods
	public void render(Graphics g, Canvas canvas) {
		if(this.background != null)
			g.drawImage(this.background, 0, 0, canvas);
		
		for(GameObject gameObject: this.getGameObjects()) {
			gameObject.render(g, canvas);
		}
	}
	
	public void tick() {
		for(GameObject gameObject: this.getGameObjects()) {
			gameObject.tick(this.getGameObjects(), this.getGravity(), this.getMaxFallingSpeed());
		}
	}
	
	public void animate() {
		for(GameObject gameObject: this.getGameObjects()) {
			gameObject.animate();
		}
	}
	
	public void keyEvent(KeyboardInputListener keyboardInputListener) {
		
	}
	
	public void reload() {
		try {
			this.background = ImageIO.read(getClass().getResource(this.backgroundPath));
		}
		catch (IOException e) {
			e.printStackTrace();
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

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(List<GameObject> gameObjects) {
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
	
}
