package game_demo;

import java.util.List;

import game_demo.bases.Space;
import game_demo.primitives.Window;
import game_demo.spaces.Stage;

public class Game {
	public static double FPS = 90;
	public static double TPS = 85;
	public static double APS = 7;
	
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
