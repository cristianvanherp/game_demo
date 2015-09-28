package game_demo;

public class Stage implements Runnable {
	private double FPS, TPS, APS;
	private boolean running;
	private Thread thread;
	
	public Stage(double FPS, double TPS, double APS) {
		this.FPS = FPS;
		this.TPS = TPS;
		this.APS = APS;
	}
	
	public void init() {
		
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
		
		try {
			this.thread.join();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public void run() {
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
		
	}
	
	public void tick() {
		
	}
	
	public void animate() {
		
	}
}
