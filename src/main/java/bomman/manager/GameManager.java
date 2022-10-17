package bomman.manager;

import bomman.entity.CommonEntity;
import bomman.entity.EntityManager;
import bomman.tiles.BreakableTiles;
import bomman.tiles.CommonTiles;
import bomman.tiles.HiddenTiles;
import bomman.tiles.UnbreakableTiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will manage all the attributes, functions and operations in the game.
 */
public class GameManager {

	// private game manager object to manage the game

	public GameManager() {
		String path = "src/main/resources/bomman/maps/map1.txt";
		File file = new File(path);
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
			for (int i = 0; i < GAME_HEIGHT; i++) {
				for (int j = 0; j < GAME_WIDTH; j++) {
					if (map[i][j] == 1) {
						gameTiles[i][j] = new HiddenTiles(j, i, Sprite.wall.getFxImage());
					} else {
						gameTiles[i][j] = new HiddenTiles(j, i, Sprite.grass.getFxImage());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final GameManager gameManager = new GameManager();

	public int[][] map = new int[GAME_HEIGHT][GAME_WIDTH];

	// Title of the game using in main
	public static final String GAME_TITLE = "BOMMAN";
	// Path to the stage map, used to load map from the function.
	private String[] mapPath = new String [STAGE_NUMBER];

	// Each sprite size (pixels)
	private static final int DEFAULT_SPRITE_SIZE = 32;
	public static final int GAME_WIDTH = 20;
	public static final int GAME_HEIGHT = 15;

	// In-game private attribute
	private static final int STAGE_NUMBER = 5;
	private static int currentStage = 1;
	// private static int score = 0;
	// private static int coin = 0;
	private boolean nextStage = false;
	private boolean won = false;
	private boolean restart = false;
	private boolean lost = false;

	// List of entity that will be rendered.
	public List<CommonEntity> stillObjects = new ArrayList<>();
	//public List<CommonTiles> gameTiles = new ArrayList<>();
	public CommonTiles[][] gameTiles = new CommonTiles[GAME_HEIGHT][GAME_WIDTH];

	// public variables that can be changed in game
	public int bommanHealth = 5;
	public int enemyHealth = 3;
	public int enemyNumber = 5;
	// public int bossHealth = 5;

	/**
	 * Constructor for the game manager, basically load the main sprites.
	 */
	private void setMapPath() {
		mapPath[0] = "";
		mapPath[1] = "src/main/resources/bomman/maps/map1.txt";
		mapPath[2] = "src/main/resources/bomman/maps/map2.txt";
		mapPath[3] = "";
		mapPath[4] = "";
	}

	/**
	 * This function read a file and load the images from that file to the real map.
	 */
	public void createMapFromFile() {
		setMapPath();
		String path = mapPath[currentStage];
		File file = new File(path);
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
			for (int i = 0; i < GAME_HEIGHT; i++) {
				for (int j = 0; j < GAME_WIDTH; j++) {
					System.out.print(map[i][j] + " ");
					if (map[i][j] == 1) {
						gameTiles[i][j] = new HiddenTiles(j, i, Sprite.wall.getFxImage());
					} else {
						gameTiles[i][j] = new HiddenTiles(j, i, Sprite.grass.getFxImage());
					}
				}
				System.out.print("\n");
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
		// score = 0;
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
