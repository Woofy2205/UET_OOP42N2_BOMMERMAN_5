package bomman.entity;

import bomman.event.EventHandling;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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

	public MainCharacter(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}

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

	@Override
	public int getXPosition() {
		return super.getXPosition();
	}

	@Override
	public int getYPosition() {
		return super.getYPosition();
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

	@Override
	public void update() {
		if (EventHandling.currentlyActiveKeys.contains("LEFT")) {
			this.move(CommonEntity.DIRECTION.LEFT);
		}
		if (EventHandling.currentlyActiveKeys.contains("RIGHT")) {
			this.move(CommonEntity.DIRECTION.RIGHT);
		}
		if (EventHandling.currentlyActiveKeys.contains("UP")) {
			this.move(CommonEntity.DIRECTION.UP);
		}
		if (EventHandling.currentlyActiveKeys.contains("DOWN")) {
			this.move(CommonEntity.DIRECTION.DOWN);
		}
	}

//	@Override
//	public void render(GraphicsContext gc) {
//		if(this.DIRECTION == DIRECTION.UP)
//	}
}
