package bomman.entity;

import bomman.entity.enemy.FirstEnemy;
import bomman.entity.enemy.SecondEnemy;
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

    public static void createEntity() {
        entities.add(new FirstEnemy(1, 1, Sprite.balloon_left1.getFxImage()));
        entities.add(new SecondEnemy(6, 6, Sprite.oneal_left1.getFxImage()));

    }

    public static void createMainCharacter() {
        bomberman = new MainCharacter(MainCharacter.PLAYER_START_X, MainCharacter.PLAYER_START_Y, Sprite.player_right.getFxImage());
    }
}
