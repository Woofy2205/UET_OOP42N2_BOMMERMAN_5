package bomman.entity;

import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.BreakableTiles;
import bomman.tiles.TilesManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will control the flame created from the bomb.
 */
public class Flame extends CommonEntity{
	private int xPos;
	private int yPos;
	private int timeExplode = 50;

	public static List<Flame> flames = new ArrayList<Flame>();

	public Flame(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
//		this.timeExplode = timeExplode;
	}

	/**
	 * Getters and setters
	 */
	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public int getTimeExplode() {
		return timeExplode;
	}

	public void setTimeExplode(int timeExplode) {
		this.timeExplode = timeExplode;
	}

	public static boolean checkFlameValid(int xPos, int yPos) {
		// if (GameManager.getGameManager().map[yPos][xPos] == 1) return false;
		if (GameManager.map[yPos][xPos] == 2) {
			GameManager.map[yPos][xPos] = 0;
			TilesManager.gameTiles[yPos][xPos].setImg(Sprite.grass.getFxImage());
			return true;
		} else if (GameManager.map[yPos][xPos] != 0) {
			return false;
		}
		else return true;
	}

	public static void flameCountdown() {
		List<Flame> removingFlame = new ArrayList<>();
		for (Flame f: flames) {
			f.setTimeExplode(f.timeExplode - 1);
			if (f.timeExplode == 0) {
				removingFlame.add(f);
			}
		}
		for (Flame f: removingFlame) {
			flames.remove(f);
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void render(GraphicsContext gc, double t) {
		double frame = (int) (t / 0.083) % 12 % 3;
		if (timeExplode >= 0) {
			if (frame == 0) this.setImg(Sprite.bomb_exploded.getFxImage());
			if (frame == 1) this.setImg(Sprite.bomb_exploded1.getFxImage());
			if (frame == 2) this.setImg(Sprite.bomb_exploded2.getFxImage());
		}
		gc.drawImage(getImg(), getXPosition(), getYPosition());
	}
}
