package bomman.entity;

import bomman.entity.enemy.*;
import bomman.manager.GameManager;
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

    public static boolean hasBomb(int row, int col) {
        for (Bomb b : Bomb.bombs) {
            if (b.getXPosition() / Sprite.SCALED_SIZE == row && b.getYPosition() / Sprite.SCALED_SIZE == col) {
                System.out.println("true");
                return true;
            }
        }
        return false;
    }

    public static int generateRandomX() {
		int max = 38;
		int min = 1;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}

	public static int generateRandomY() {
		int max = 18;
		int min = 1;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
	}

    public static void createEntity() {
        //entities.add(new FirstEnemy(generateRandomX(), generateRandomY(), Sprite.balloon_left1.getFxImage()));
	    //entities.add(new SecondEnemy(generateRandomX(), generateRandomY(), Sprite.balloon_left1.getFxImage()));
	    entities.add(new DoubleLifeEnemy(generateRandomX(), generateRandomY(), Sprite.balloon_left1.getFxImage()));
	    entities.add(new SuicidalEnemy(generateRandomX(), generateRandomY(), Sprite.balloon_left1.getFxImage()));
        //entities.add(new SmartEnemy(generateRandomX(), generateRandomY(), Sprite.doll_left1.getFxImage()));
		//entities.add(new InvisibleEnemy(generateRandomX(), generateRandomY(), Sprite.balloon_left1.getFxImage()));
    }

    public static void createMainCharacter() {
        bomberman = new MainCharacter(MainCharacter.PLAYER_START_X, MainCharacter.PLAYER_START_Y, Sprite.player_right.getFxImage());
    }

    public static void removeDeathEntity() {
        entities.removeIf(i -> i.getAlive() == 2);
    }
}
