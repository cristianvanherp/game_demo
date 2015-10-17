package game_demo;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage image;
	
	public SpriteSheet(String imagePath) {
		try {
			this.image = ImageIO.read(getClass().getResource(imagePath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage slice(int col, int row, int width, int height) {
		return this.image.getSubimage(col*width, row*height, width, height);
	}
}
