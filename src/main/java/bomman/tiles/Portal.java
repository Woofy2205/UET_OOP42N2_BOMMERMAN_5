package bomman.tiles;

import bomman.manager.Sprite;
import javafx.scene.image.Image;

/**
 * Portal to move to another map.
 */
public class Portal extends CommonTiles {

	public Portal(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.portal.getFxImage());
	}

	@Override
	public void update() {

	}
}
