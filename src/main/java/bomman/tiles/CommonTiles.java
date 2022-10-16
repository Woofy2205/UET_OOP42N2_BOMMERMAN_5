package bomman.tiles;

import bomman.entity.CommonEntity;
import bomman.entity.MainCharacter;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class will control all common attributes, functions and operations of tiles.
 */
public abstract class CommonTiles {

	public static final int TILES_SIZE = 32;

	public int xTile;
	public int yTile;

	private Image img;

	public CommonTiles (int xUnit, int yUnit, Image img) {
		this.xTile = xUnit * Sprite.SCALED_SIZE;
		this.yTile = yUnit * Sprite.SCALED_SIZE;
		this.img = img;
	}

	public void render(GraphicsContext gc, double t) {
		gc.drawImage(img, xTile, yTile);
	}

	public abstract void update();

}
