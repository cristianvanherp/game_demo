package game_demo.primitives;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class SpriteSheet implements Serializable {
	private String imagePath;
	private transient BufferedImage image;
	private transient List<List<BufferedImage>> imageGrid;
	private int numRows, numCols;
	private int currentRow, currentCol;
	private int spriteWidth, spriteHeight;
	private int rowLeft, rowRight, rowTop, rowBottom;
	
	public SpriteSheet(String imagePath, int numRows, int numCols, int spriteWidth, int spriteHeight, int rowLeft, int rowRight, int rowTop, int rowBottom) {
		this.imagePath = imagePath;
		this.numRows = numRows;
		this.numCols = numCols;
		this.currentRow = this.currentCol = 0;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.rowLeft = rowLeft;
		this.rowRight = rowRight;
		this.rowTop = rowTop;
		this.rowBottom = rowBottom;
		
		try {
			this.image = ImageIO.read(getClass().getResource(this.imagePath));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.configureGrid();
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
	
	//Utility methods
	public void reload() {
		try {
			this.image = ImageIO.read(getClass().getResource(this.imagePath));
			this.configureGrid();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void configureGrid() {
		this.imageGrid = new ArrayList<List<BufferedImage>>();
		
		for(int row = 0 ; row < this.numRows ; row++) {
			this.imageGrid.add(new ArrayList<BufferedImage>());
		}
		
		for(int row = 0 ; row < this.numRows ; row++) {
			for(int col = 0 ; col < this.numCols ; col++) {
				this.imageGrid.get(row).add(this.slice(col, row, this.spriteWidth, this.spriteHeight));
			}
		}
	}
	
	public BufferedImage getCurrent() {
		return this.imageGrid.get(this.currentRow).get(this.currentCol);
	}
	
	public SpriteSheet clone() {
		return new SpriteSheet(this.imagePath, this.numRows, this.numCols, this.spriteWidth, this.spriteHeight, this.rowLeft, this.rowRight, this.rowTop, this.rowBottom);
	}
	
	//	Getters and Setters
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

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}

	public int getRowLeft() {
		return rowLeft;
	}

	public void setRowLeft(int rowLeft) {
		this.rowLeft = rowLeft;
	}

	public int getRowRight() {
		return rowRight;
	}

	public void setRowRight(int rowRight) {
		this.rowRight = rowRight;
	}

	public int getRowTop() {
		return rowTop;
	}

	public void setRowTop(int rowTop) {
		this.rowTop = rowTop;
	}

	public int getRowBottom() {
		return rowBottom;
	}

	public void setRowBottom(int rowBottom) {
		this.rowBottom = rowBottom;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
