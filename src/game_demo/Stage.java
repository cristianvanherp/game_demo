package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Stage extends Space {
	private static final long serialVersionUID = -5457552461161287550L;
	Player player;

	public Stage(double FPS, double TPS, double APS, String backgroundPath, float gravity, int maxFallingSpeed, String mapPath) {
		super(FPS, TPS, APS, backgroundPath, gravity, maxFallingSpeed, mapPath);
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setPlayer();
	}
	
	public void setPlayer() {
		this.player = this.getMap().getPlayer();
	}
	
	public void adjustPlayerBasedOffset() {
		if(this.player.getX() > Window.WIDTH/2)
			this.getCamera().setCurrentOffsetX((int)this.player.getX() - Window.WIDTH/2);
		if(this.player.getY() > Window.HEIGHT/2)
		this.getCamera().setCurrentOffsetY((int)this.player.getY() - Window.HEIGHT/2);
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
		this.map.tick(this.getCamera());
		if(this.player != null)
			this.adjustPlayerBasedOffset();
	}
	
	public void animate() {
		this.map.animate(this.getCamera());
	}
	
	public void keyEvent() {
		if(this.getKeyboardInputListener().isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.stop();
		}
		this.map.keyEvent(this.getKeyboardInputListener(), this.getCamera());
	}
	
	public void mouseEvent(MouseEvent mouseEvent) {
		
	}
	
	public void mouseMoved(MouseEvent mouseEvent) {
		
	}
}
