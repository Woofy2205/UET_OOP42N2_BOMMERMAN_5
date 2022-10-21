package bomman.tiles;

import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grass extends CommonTiles{

	public Grass(int xUnit, int yUnit) {
		super(xUnit, yUnit, Sprite.grass.getFxImage());
	}

	@Override
	public void update() {

	}

	@Override
	public void render(GraphicsContext gc, double t) {
		super.render(gc, t);
	}
}
