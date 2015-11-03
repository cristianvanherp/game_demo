package game_demo;

import java.util.List;

public class Game {
	public static double FPS = 2000;
	public static double TPS = 2000;
	public static double APS = 2000;
	
	public List<Space> spaces;
	public Stage currentStage;
	public Window window;
	
	public Game(List<Space> stages) throws Exception {
		if(stages.size() < 1) {
			throw new Exception("Empty stage array");
		}
		this.spaces = stages;
		this.window = new Window("Game Demo");
		this.currentStage = (Stage)this.window.getCurrentCanvas();
	}
	
	public void start() {
		for(Space space: this.spaces) {
			this.window.changeCanvas(space);
			space.start();
			try {
				space.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
