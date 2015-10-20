package game_demo;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage image;
	private int numRows, numCols;
	private int currentRow, currentCol;
	
	public SpriteSheet(String imagePath, int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.currentRow = this.currentCol = 0;
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
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public void animate() {
		if(this.getCurrentCol() < this.getNumCols() - 1)
			this.setCurrentCol(this.getCurrentCol() + 1);
		else {
			this.setCurrentCol(0);
		}
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public void setCurrentCol(int currentCol) {
		this.currentCol = currentCol;
	}
	
}
