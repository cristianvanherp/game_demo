package game_demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		List<Stage> stages = new ArrayList();
		stages.add(new Stage(Game.FPS, Game.TPS, Game.APS, new Color(0, 0, 0)));
		stages.add(new Stage(Game.FPS, Game.TPS, Game.APS, new Color(0, 0, 0)));
		stages.add(new Stage(Game.FPS, Game.TPS, Game.APS, new Color(0, 0, 0)));
		
		try {
			Game game = new Game(stages);
			game.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
