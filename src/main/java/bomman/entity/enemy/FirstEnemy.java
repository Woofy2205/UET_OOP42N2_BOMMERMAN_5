package bomman.entity.enemy;

import bomman.entity.CommonEntity;
import bomman.entity.MainCharacter;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * First type of enemy.
 */
public class FirstEnemy extends CommonEntity {
    private static int characterVelocity = 2;

    public FirstEnemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static int getCharacterVelocity(){
        return characterVelocity;
    }

    public static void setCharacterVelocity(int characterVelocity) {
        FirstEnemy.characterVelocity = characterVelocity;
    }

    @Override
    public void update() {

    }
}
