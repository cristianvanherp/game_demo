package game_demo;

import java.util.List;

public class Game {
	public static double FPS = 60;
	public static double TPS = 65;
	public static double APS = 7;
	
	public List<Stage> stages;
	public Stage currentStage;
	public Window window;
	
	public Game(List<Stage> stages) throws Exception {
		if(stages.size() < 1) {
			throw new Exception("Empty stage array");
		}
		this.stages = stages;
		this.window = new Window("Game Demo");
		this.currentStage = (Stage)this.window.getCurrentCanvas();
	}
	
	public void start() {
		for(Stage stage: this.stages) {
			this.window.changeCanvas(stage);
			stage.start();
			try {
				stage.thread.join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
