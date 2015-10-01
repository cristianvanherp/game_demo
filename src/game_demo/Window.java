package game_demo;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Window extends JFrame {
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public Canvas currentCanvas;
	public String title;
	
	public Window(String title) {
		super();
		this.title = title;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
