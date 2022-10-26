package bomman.tiles;

import bomman.entity.Flame;
import bomman.manager.Sprite;
import bomman.manager.SpriteSheet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Tiles that can be destroyed by flame.
 */
public class BreakableTiles extends CommonTiles {

	private int count = 0;



	public BreakableTiles(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.brick.getFxImage());
	}

	@Override
	public void update(){

	}

	@Override
	public void render(GraphicsContext gc, double t) {
		double frame = (int) (t / 0.083) % 12 % 3;
		if (!isExist) {
			this.setImg((new Sprite(Sprite.DEFAULT_SIZE, 0,0, SpriteSheet.grassTiles, 16, 16)).getFxImage());
		}
		gc.drawImage(getImg(), getXTile(), getYTile());
	}
}
