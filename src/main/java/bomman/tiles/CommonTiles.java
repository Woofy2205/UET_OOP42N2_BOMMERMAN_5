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

	public static boolean collision(CommonEntity entity, int x, int y) {

		int left = x;
		int right = x + TILES_SIZE;
		int top = y;
		int bottom = y + TILES_SIZE;

		int entityLeft = entity.getXPosition();
		int entityRight = entity.getXPosition() + entity.CHARACTER_SIZE;
		int entityTop = entity.getYPosition();
		int entityBottom = entity.getYPosition() + entity.CHARACTER_SIZE;

		return(bottom <= entityTop || top >= entityBottom || right <= entityLeft || left >= entityRight);
	}

	public static void collide(MainCharacter mainCharacter, int[][] map) {
		for (int i = 0; i < GameManager.GAME_WIDTH; i++) {
			for (int j = 0; j < GameManager.GAME_HEIGHT; j++) {
				if (map[i][j] != 0 && collision(mainCharacter, i, j)) {
					int val = map[i][j];
					if (mainCharacter.getCharacterVelocity() > 0) {
						mainCharacter.setCharacterVelocity(0);
					}
				} else {
					mainCharacter.setCharacterVelocity(8);
				}
			}
		}
	}
	public void render(GraphicsContext gc, double t) {
		gc.drawImage(img, xTile, yTile);
	}

	public abstract void update();

}
