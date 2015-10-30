package game_demo;

public class GameObjectFactory {
	
	private static GameObjectFactory instance;
	private static Map factoryMap;
	
	private GameObjectFactory() {
		
	}
	
	public static GameObjectFactory getInstance() {
		if(instance == null) {
			instance = new GameObjectFactory();
		}
		
		return instance;
	}
	
	public static void configure(Map map) {
		factoryMap = map;
	}
	
	public Block createBlock(String spritePath, int widthRate, int heightRate) {
		SpriteSheet spriteSheet = new SpriteSheet(spritePath, 1, 1, factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, 0, 0, 0, 0);
		return new Block(factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, -100, -100, spriteSheet, false);
	}
}
