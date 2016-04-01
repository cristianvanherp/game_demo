package game_demo.primitives;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window extends JFrame {
	public static int WIDTH = 1440;
	public static int HEIGHT = 900;
	public Canvas currentCanvas;
	public String title;
	
	public Window(String title) {
		super();
		this.title = title;
		this.getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(new Point(0, 0));
		this.setVisible(true);
		this.requestFocus();
	}
	
	public void changeCanvas(Canvas newCanvas) {
		if(this.currentCanvas != null)
			this.remove(this.currentCanvas);
		
		this.currentCanvas = newCanvas;
		this.add(this.currentCanvas);
		this.revalidate();
		this.repaint();
		this.pack();
	}
	
	public Canvas getCurrentCanvas() {
		return this.currentCanvas;
	}
}
