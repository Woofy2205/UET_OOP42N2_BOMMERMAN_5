package bomman.entity.enemy;

import bomman.entity.CommonEntity;
import bomman.entity.MainCharacter;
import javafx.scene.image.Image;

/**
 * Second type of enemy.
 */
public class SecondEnemy extends CommonEntity {
    private static int characterVelocity = 2;

    public SecondEnemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static int getCharacterVelocity(){
        return characterVelocity;
    }

    public static void setCharacterVelocity(int characterVelocity) {
        SecondEnemy.characterVelocity = characterVelocity;
    }

    @Override
    public void update() {

    }
}
