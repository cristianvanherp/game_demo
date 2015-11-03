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
	
	public Block createBlock(String spritePath, int widthRate, int heightRate, int spriteWidth, int spriteHeight) {
		SpriteSheet spriteSheet = new SpriteSheet(spritePath, 1, 1, spriteWidth, spriteHeight, 0, 0, 0, 0);
		return new Block(factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, -100, -100, spriteSheet, false);
	}
	
	public Block createJumpingBlock(String spritePath, int widthRate, int heightRate, int spriteWidth, int spriteHeight) {
		SpriteSheet spriteSheet = new SpriteSheet(spritePath, 1, 1, spriteWidth, spriteHeight, 0, 0, 0, 0);
		return new Block(factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, -100, -100, spriteSheet, true);
	}
	
	public Player createPlayer(String spritePath, int widthRate, int heightRate, int spriteWidth, int spriteHeight) {
		SpriteSheet spriteSheet = new SpriteSheet(spritePath, 4, 3, 32, 32, 1, 2, 4, 0);
		return new Player(factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, -100, -100, spriteSheet, true);
	}
	
	public Enemy createEnemy(String spritePath, int widthRate, int heightRate, int spriteWidth, int spriteHeight) {
		SpriteSheet spriteSheet = new SpriteSheet(spritePath, 4, 3, 32, 32, 1, 2, 4, 0);
		return new Enemy(factoryMap.getMinItemWidth()*widthRate, factoryMap.getMinItemHeight()*heightRate, -100, -100, spriteSheet, true);
	}
}
