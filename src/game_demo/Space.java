package game_demo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public abstract class Space extends Canvas implements Runnable, InputSensible {
	private double FPS, TPS, APS;
	private boolean running;
	private KeyboardInputListener keyboardInputListener;
	private Thread thread;
	Map map;
	
	public Space(double FPS, double TPS, double APS, String backgroundPath, int gravity, int maxFallingSpeed) {
		this.FPS = FPS;
		this.TPS = TPS;
		this.APS = APS;
		this.map = new Map(backgroundPath, gravity, maxFallingSpeed);
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		this.setMaximumSize(new Dimension(Window.WIDTH, Window.HEIGHT));
	}
	
	public void init() {
		this.keyboardInputListener = new KeyboardInputListener(this);
		this.addKeyListener(this.keyboardInputListener);
		this.requestFocus();
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
	
	public abstract void render();
	public abstract void tick();
	public abstract void animate();
	public abstract void keyEvent();
	
	//Getters and Setters
	public double getFPS() {
		return FPS;
	}

	public void setFPS(double fPS) {
		FPS = fPS;
	}

	public double getTPS() {
		return TPS;
	}

	public void setTPS(double tPS) {
		TPS = tPS;
	}

	public double getAPS() {
		return APS;
	}

	public void setAPS(double aPS) {
		APS = aPS;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public KeyboardInputListener getKeyboardInputListener() {
		return keyboardInputListener;
	}

	public void setKeyboardInputListener(KeyboardInputListener keyboardInputListener) {
		this.keyboardInputListener = keyboardInputListener;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
