package bomman.entity;

import bomman.manager.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will manage all the entities' action.
 * Add, Remove, ...
 */
public class EntityManager {

	public static List<CommonEntity> entities = new ArrayList<CommonEntity>();
	public static MainCharacter bomberman;

	public EntityManager() {
		// entities = new ArrayList<CommonEntity>();
	}

//	public static void createBombs(Bomb newBomb) {
//		bombs.add(newBomb);
//	}

//	public static void bombCountdown() {
//		List<Integer> removingIndexes = new ArrayList<>();
//		explosionList.clear();
//		int index = 0;
//		for (Bomb b: bombs) {
//			b.setExplosionTime(b.getExplosionTime() - 1);
//			if (b.getExplosionTime() == 0) {
//				removingIndexes.add(index);
//				explosionList.add(b);
//			}
//			index++;
//		}
//		for (int i: removingIndexes) {
//			bombs.remove(i);
//		}
//	}

	public void createEntity() {
		entities.add (new Bomb(5, 5, Sprite.player_right.getFxImage(), 10));
	}

	public void createMainCharacter() {
		bomberman = new MainCharacter(MainCharacter.PLAYER_START_X, MainCharacter.PLAYER_START_Y, Sprite.player_right.getFxImage());
	}
}
