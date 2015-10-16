package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Stage extends Canvas implements Runnable, InputSensible {
	private double FPS, TPS, APS;
	private boolean running;
	private KeyboardInputListener keyboardInputListener;
	public Thread thread;
	public Entity entity;
	public List<GameObject> gameObjects;
	
	public Stage(double FPS, double TPS, double APS) {
		this.FPS = FPS;
		this.TPS = TPS;
		this.APS = APS;
		this.entity = new Entity(40, 40, 100, 100, 2);
		this.gameObjects = new ArrayList<GameObject>();
		this.gameObjects.add(this.entity);
		this.gameObjects.add(new Block(40, 40, 100, 400, 5));
		this.gameObjects.add(new Block(40, 40, 140, 400, 5));
		this.gameObjects.add(new Block(40, 40, 180, 400, 5));
		this.gameObjects.add(new Block(40, 40, 100, 400, 5));
		this.gameObjects.add(new Block(40, 40, 100, 360, 5));
		this.gameObjects.add(new Block(40, 40, 100, 320, 5));
		
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
	}
	
	public void init() {
		this.keyboardInputListener = new KeyboardInputListener(this);
		this.addKeyListener(this.keyboardInputListener);
	}
	
	public void start() {
		if(!this.running)
			this.running = true;
		
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	public void stop() {
		if(this.running)
			this.running = false;
	}
	
	public void run() {
		this.init();
		double deltaFrames = 0, deltaTicks = 0, deltaAnimations = 0;
		double fpsNs = 1000000000 / this.FPS;
		double tpsNs = 1000000000 / this.TPS;
		double apsNs = 1000000000 / this.APS;
		int frames = 0, ticks = 0, animations = 0;
		long timer = System.currentTimeMillis();
		long now = System.nanoTime(), lastTime = System.nanoTime();
		
		while(this.running) {
			now = System.nanoTime();
			deltaFrames += (now - lastTime) / fpsNs;
			deltaTicks += (now - lastTime) / tpsNs;
			deltaAnimations += (now - lastTime) / apsNs;
			lastTime = now;
			
			if(deltaFrames >= 1) {
				deltaFrames--;
				this.render();
				frames++;
			}
			
			if(deltaTicks >= 1) {
				deltaTicks--;
				this.tick();
				ticks++;
			}
			
			if(deltaAnimations >= 1) {
				deltaAnimations--;
				this.animate();
				animations++;
			}
			
			if((System.currentTimeMillis() - timer) > 1000) {
				timer = System.currentTimeMillis();
				System.out.printf("FPS: %d, TPS: %d, APS: %d\n", frames, ticks, animations);
				frames = ticks = animations = 0;
			}
		}
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
		for(GameObject gameObject: this.gameObjects) {
			gameObject.renderBoundaries(g, this);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		for(GameObject gameObject: this.gameObjects) {
			gameObject.tick();
		}
		for(GameObject gameObject: this.gameObjects) {
			gameObject.handleCollision(this.gameObjects);
		}
	}
	
	public void animate() {
		
	}
	
	public void keyEvent() {
		if(this.keyboardInputListener.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.stop();
		}
		this.entity.keyEvent(this.keyboardInputListener);
	}
}
