package bomman.entity;

import bomman.manager.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will manage all the entities' action.
 * Add, Remove, ...
 */
public class EntityManager {

	public List<CommonEntity> entities = new ArrayList<CommonEntity>();

	public EntityManager() {
		// entities = new ArrayList<CommonEntity>();
	}

	public void createEntity() {
		CommonEntity bomberman = new MainCharacter(1, 1, Sprite.player_right.getFxImage());
		CommonEntity bomb = new Bomb(1,2, Sprite.bomb.getFxImage(), 100);
		entities.add(bomberman);
		entities.add(bomb);
	}

}
