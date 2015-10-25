package game_demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		List<Space> spaces = new ArrayList();
		spaces.add(new MapEditor(Game.FPS, Game.TPS, Game.APS, "/basic_bg.png", 1, 10, 40, 40));
//		spaces.add(new Stage(Game.FPS, Game.TPS, Game.APS, "/basic_bg.png", 1, 10));
//		spaces.add(new Stage(Game.FPS, Game.TPS, Game.APS, "/basic_bg.png", 1, 10));
//		spaces.add(new Stage(Game.FPS, Game.TPS, Game.APS, "/basic_bg.png", 1, 10));
		
		try {
			Game game = new Game(spaces);
			game.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
