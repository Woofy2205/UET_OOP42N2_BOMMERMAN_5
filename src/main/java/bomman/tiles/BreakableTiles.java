package bomman.tiles;

import bomman.entity.Flame;
import bomman.manager.Sprite;
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
			if (frame == 0) count++;
			if (count == 10) {
				this.setImg(Sprite.brick_exploded.getFxImage());
			} else if (count == 20) {
				this.setImg(Sprite.brick_exploded1.getFxImage());
			} else if (count == 30) {
				this.setImg(Sprite.brick_exploded1.getFxImage());
			} else if (count == 50) {
				this.setImg(Sprite.grass.getFxImage());
			}
		}
		gc.drawImage(getImg(), getXTile(), getYTile());
	}
}
