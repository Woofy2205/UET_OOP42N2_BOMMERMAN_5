package bomman.entity;

/**
 * This will control the main character =))))))) like, obviously =))))))))
 */
public class MainCharacter extends CommonEntity {
	// Start point of main character (can be changed so not 'final' type)
	private static int PLAYER_START_X;
	private static int PLAYER_START_Y;

	// Speed of the main character
	private static int CHARACTER_STEP = 16;

	// Other attributes
	private int explosionRadius;
	private int bombDamage;

	/**
	 * Getters.
	 */
	public int getBombDamage() {
		return bombDamage;
	}

	public static int getCharacterStep() {
		return CHARACTER_STEP;
	}

	public int getExplosionRadius() {
		return explosionRadius;
	}

	/**
	 * Setters.
	 */
	public static void setPlayerStartX(int playerStartX) {
		PLAYER_START_X = playerStartX;
	}

	public static void setPlayerStartY(int playerStartY) {
		PLAYER_START_Y = playerStartY;
	}
}
