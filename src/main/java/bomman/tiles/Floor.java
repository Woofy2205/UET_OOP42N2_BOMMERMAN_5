package bomman.tiles;

import bomman.manager.Sprite;
import bomman.manager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Floor extends CommonTiles{

	public static int generateRandomX() {
		int max = 7;
		int min = 0;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}

	public static int generateRandomY() {
		int max = 15;
		int min = 0;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}

	public Floor(int xUnit, int yUnit) {
		super(xUnit, yUnit, (new Sprite(Sprite.DEFAULT_SIZE, generateRandomX(), generateRandomY(), SpriteSheet.grassTiles, 16, 16)).getFxImage());
	}

	//public Floor(int xUnit, int yUnit) {
	//	super(xUnit, yUnit, Sprite.grass.getFxImage());
	//}

	@Override
	public void update() {

	}

	@Override
	public void render(GraphicsContext gc, double t) {
		super.render(gc, t);
	}
}
