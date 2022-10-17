package bomman.tiles;

import javafx.scene.image.Image;

/**
 * Tiles that are unbreakable by the flame (of the bomb).
 */
public class UnbreakableTiles extends CommonTiles {
    public UnbreakableTiles(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
