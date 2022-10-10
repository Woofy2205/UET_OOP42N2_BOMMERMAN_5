package bomman.entity.tiles;

import bomman.entity.CommonEntity;
import javafx.scene.image.Image;

/**
 * Hidden tiles that can magically appear.
 */
public class HiddenTiles extends CommonEntity {
    public HiddenTiles(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
