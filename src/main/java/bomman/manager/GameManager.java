package bomman.manager;

/**
 * This class will manage all the attributes, functions and operations in the game.
 */
public class GameManager {

	// private game manager object to manage the game
	private static final GameManager gameManager = new GameManager();

	// Title of the game using in main
	public static final String GAME_TITLE = "BOMMAN";

	// Each sprite size (pixels)
	private static final int DEFAULT_SPRITE_SIZE = 16;

	// 20 sprite per attribute
	private static final int GAME_WIDTH = 20 * DEFAULT_SPRITE_SIZE;
	private static final int GAME_LENGTH = 20 * DEFAULT_SPRITE_SIZE;

	// In-game private attribute
	private static int stage = 0;
	private static int score = 0;
	// private static int coin = 0;
	private boolean nextStage = false;
	private boolean won = false;
	private boolean restart = false;
	private boolean lost = false;

	// public variables that can be changed in game
	public int bommanHealth = 5;
	public int enemyHealth = 3;
	// public int bossHealth = 5;

	/**
	 * Constructor for the game manager, basically load the main sprites.
	 */
	private GameManager() {
		// Load sprites of entities (haven't got yet)
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
		stage = 0;
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
