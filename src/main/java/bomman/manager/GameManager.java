package bomman.manager;

import bomman.entity.CommonEntity;
import bomman.entity.MainCharacter;
import bomman.entity.tiles.HiddenTiles;
import bomman.entity.tiles.UnbreakableTiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will manage all the attributes, functions and operations in the game.
 */
public class GameManager {

	// private game manager object to manage the game
	private static final GameManager gameManager = new GameManager();

	// Title of the game using in main
	public static final String GAME_TITLE = "BOMMAN";
	// Path to the stage map, used to load map from the function.
	private String[] map = new String [STAGE_NUMBER];

	// Each sprite size (pixels)
	private static final int DEFAULT_SPRITE_SIZE = 32;
	public static final int GAME_WIDTH = 20;
	public static final int GAME_HEIGHT = 15;

	// In-game private attribute
	private static final int STAGE_NUMBER = 5;
	private static int currentStage = 1;
	private static int score = 0;
	// private static int coin = 0;
	private boolean nextStage = false;
	private boolean won = false;
	private boolean restart = false;
	private boolean lost = false;

	// List of entity that will be rendered.
	public List<CommonEntity> stillObjects = new ArrayList<>();

	// public variables that can be changed in game
	public int bommanHealth = 5;
	public int enemyHealth = 3;
	public int enemyNumber = 5;
	// public int bossHealth = 5;

	/**
	 * Constructor for the game manager, basically load the main sprites.
	 */
	private void setMapPath() {
		map[0] = "";
		map[1] = "D:\\UET_OOP42N2_BOMMERMAN_5\\src\\main\\resources\\bomman\\maps\\map1.txt";
		map[2] = "D:\\UET_OOP42N2_BOMMERMAN_5\\src\\main\\resources\\bomman\\maps\\map2.txt";
		map[3] = "";
		map[4] = "";
	}

	/**
	 * This function read a file and load the images from that file to the real map.
	 */
	public void createMapFromFile() {
		setMapPath();
		String mapPath = map[currentStage];
		File file = new File(mapPath);
		int[][] map = new int[GAME_WIDTH][GAME_HEIGHT];
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				for (int i = 0; i < map.length; i++) {
					String[] line = sc.nextLine().trim().split("," + " ");
					for (int j = 0; j < line.length; j++) {
						map[i][j] = Integer.parseInt(line[j]);
					}
				}
			}
			for (int i = 0; i < GAME_WIDTH; i++) {
				for (int j = 0; j < GAME_HEIGHT; j++) {
					CommonEntity object;
					if (map[i][j] == 1) {
						object = new UnbreakableTiles(i, j, Sprite.wall.getFxImage());
					} else {
						object = new HiddenTiles(i, j, Sprite.grass.getFxImage());
					}
					stillObjects.add(object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * reset function for the next stage.
	 */
	public void reset() {
		bommanHealth = 5;
		enemyHealth = 3;
		nextStage = false;
		restart = false;
		won = false;
		lost = false;
	}

	/**
	 * restart function for the game.
	 */
	public void restart() {
		currentStage = 0;
		score = 0;
		bommanHealth = 5;
		enemyHealth = 3;
		nextStage = false;
		restart = false;
		won = false;
		lost = false;
	}

	/**
	 * Getters.
	 */
	public static GameManager getGameManager() {
		return gameManager;
	}

	public boolean isLost() {
		return lost;
	}

	public boolean isWon() {
		return won;
	}

	public boolean isRestart() {
		return restart;
	}

	public boolean isNextStage() {
		return nextStage;
	}
}
