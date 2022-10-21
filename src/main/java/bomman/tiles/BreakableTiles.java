package bomman.tiles;

import bomman.entity.Flame;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Tiles that can be destroyed by flame.
 */
public class BreakableTiles extends CommonTiles{

	public BreakableTiles(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.brick.getFxImage());
	}

	@Override
	public void update(){

	}

	@Override
	public void render(GraphicsContext gc, double t) {
		double frame = (int) (t / 0.083) % 12 % 3;

		gc.drawImage(getImg(), getXTile(), getYTile());
	}
}
