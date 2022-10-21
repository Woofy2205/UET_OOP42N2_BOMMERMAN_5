package bomman.entity.enemy;

import bomman.entity.CommonEntity;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Second type of enemy.
 */
public class SecondEnemy extends CommonEntity {
    private static int characterVelocity = 2;

    private List<DIRECTION> canMove;

    public SecondEnemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        canMove = new ArrayList<>();
    }

    public static int getCharacterVelocity() {
        return characterVelocity;
    }

    public static void setCharacterVelocity(int characterVelocity) {
        SecondEnemy.characterVelocity = characterVelocity;
    }

    @Override
    public void update() {
        if (getXPosition() % Sprite.SCALED_SIZE == 0 && getYPosition() % Sprite.SCALED_SIZE == 0) {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    default -> this.setDirect(DIRECTION.LEFT);
                    case 1 -> this.setDirect(DIRECTION.RIGHT);
                    case 2 -> this.setDirect(DIRECTION.UP);
                    case 3 -> this.setDirect(DIRECTION.DOWN);
                }
                int col = (getXPosition() / Sprite.SCALED_SIZE) + this.getDirect().moveX;
                int row = (getYPosition() / Sprite.SCALED_SIZE) + this.getDirect().moveY;
                if (GameManager.getGameManager().map[row][col] == 0) {
                    canMove.add(this.getDirect());
                }
            }
        }
        if (canMove.size() != 0) {
            System.out.println(canMove.size());
            int rand_dir = (int) (Math.random() * (canMove.size()));
            this.setDirect(canMove.get(rand_dir));
        }
        this.move(this.getDirect(), characterVelocity);
        canMove.clear();
    }


    @Override
    public void render(GraphicsContext gc, double t) {
        double frame = (int) (t / 0.083) % 12 % 3;
        if (this.getDirect() == DIRECTION.LEFT) {
            if (frame == 0) this.setImg(Sprite.oneal_left1.getFxImage());
            if (frame == 1) this.setImg(Sprite.oneal_left2.getFxImage());
            if (frame == 2) this.setImg(Sprite.oneal_left3.getFxImage());
        }
        if (this.getDirect() == DIRECTION.RIGHT) {
            if (frame == 0) this.setImg(Sprite.oneal_right1.getFxImage());
            if (frame == 1) this.setImg(Sprite.oneal_right2.getFxImage());
            if (frame == 2) this.setImg(Sprite.oneal_right3.getFxImage());
        }
        if (this.getDirect() == DIRECTION.UP) {
            if (frame == 0) this.setImg(Sprite.oneal_left1.getFxImage());
            if (frame == 1) this.setImg(Sprite.oneal_left2.getFxImage());
            if (frame == 2) this.setImg(Sprite.oneal_left3.getFxImage());
        }
        if (this.getDirect() == DIRECTION.DOWN) {
            if (frame == 0) this.setImg(Sprite.oneal_right1.getFxImage());
            if (frame == 1) this.setImg(Sprite.oneal_right2.getFxImage());
            if (frame == 2) this.setImg(Sprite.oneal_right3.getFxImage());
        }
        gc.drawImage(getImg(), getXPosition(), getYPosition());
    }
}
