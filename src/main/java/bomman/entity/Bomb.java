package bomman.entity;

import javafx.scene.image.Image;

/**
 * This class will control the bomb, like, of course =)))))))))))
 */
public class Bomb extends CommonEntity {

	private final static int BOMB_SIZE = 16;
	private final static int BOMB_COUNTDOWN = 100;
	private int explosionTime = BOMB_COUNTDOWN;


	public Bomb(int xUnit, int yUnit, Image img, int explosionTime) {
		super(xUnit, yUnit, img);
		this.explosionTime = explosionTime;
	}

	/**
	 * Getters and Setters.
	 */

	public int getExplosionTime() {
		return explosionTime;
	}

	public void setExplosionTime(int explosionTime) {
		this.explosionTime = explosionTime;
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
	 * Update function
	 */

	@Override
	public void update() {

	}
}
