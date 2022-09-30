package bomman.entity;

/**
 * This class will control the common attributes, operations and functions of all entities.
 */
public class CommonEntity {
	// Character size
	private static final int CHARACTER_SIZE = 32;

	// Variables for identifying the position of an entity
	private int xPosition;
	private int yPosition;

	// Enum class to decide which direction the living entities choose to move.
	public enum DIRECTION {
		// Enum for direction
		DOWN (0, 1),
		UP (0, -1),
		RIGHT(1, 0),
		LEFT(1,0);

		// Private int for declaration
		private int moveX;
		private int moveY;

		// Constructor for direction
		DIRECTION(final int _moveX, final int _moveY){
			moveX = _moveX;
			moveY = _moveY;
		}
	}

	// Moving function for the entities.
	public void move(DIRECTION direct) {
		xPosition += direct.moveX + 32;
		yPosition += direct.moveY + 32;
	}

	/**
	 * Getters.
	 */
	public static int getCharacterSize() {
		return CHARACTER_SIZE;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}
}
