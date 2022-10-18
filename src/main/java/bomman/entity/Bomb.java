package bomman.entity;

import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class will control the bomb, like, of course =)))))))))))
 */
public class Bomb extends CommonEntity {
	private int explosionTime;

	public Bomb(int xUnit, int yUnit, Image img, int explosionTime) {
		super(xUnit, yUnit, img);
		this.explosionTime = explosionTime;
	}

	public static List<Bomb> bombs = new ArrayList<Bomb>();
	public static List<Bomb> explosionList = new ArrayList<Bomb>();

	/**
	 * Getters and Setters.
	 */
	public int getExplosionTime() {
		return explosionTime;
	}

	public void setExplosionTime(int explosionTime) {
		this.explosionTime = explosionTime;
	}

//	public void countDown() {
//		List<Integer> removingIndexes = new ArrayList<>();
//		explosionList.clear();
//		int index = 0;
//		for (Bomb b: bombs) {
//			b.setExplosionTime(b.getExplosionTime() - 1);
//			System.out.print(b.getExplosionTime() + "\n");
//			if (b.getExplosionTime() == 0) {
//				removingIndexes.add(index);
//				explosionList.add(b);
//			}
//			index++;
//		}
////		for (int i: removingIndexes) {
////			bombs.remove(i);
////			System.out.print("Bomb is removed \n");
////
////		}
//		bombs.removeAll(removingIndexes);
//	}

	@Override
	public int getXPosition() {
		return super.getXPosition();
	}

	@Override
	public int getYPosition() {
		return super.getYPosition();
	}

	/**
	 * Update function
	 */
	@Override
	public void update() {
		//Bomb.bombs.forEach(Bomb::countDown);
	}

	@Override
	public void render(GraphicsContext gc, double t) {
		double frame = (int) (t / 0.083) % 12 % 3;
		if (explosionTime == 0) {
			this.setImg(Sprite.bomb_exploded.getFxImage());
			gc.drawImage(getImg(), getXPosition(), getYPosition());
		} else if (explosionTime <= 0) {

		} else {
			if (frame == 0) this.setImg(Sprite.bomb.getFxImage());
			if (frame == 1) this.setImg(Sprite.bomb_1.getFxImage());
			if (frame == 2) this.setImg(Sprite.bomb_2.getFxImage());
			gc.drawImage(getImg(), getXPosition(), getYPosition());
		}
	}

	public void countDown() {
	}
}
