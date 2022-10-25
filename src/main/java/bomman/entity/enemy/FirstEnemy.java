package bomman.entity.enemy;

import bomman.entity.Bomb;
import bomman.entity.CommonEntity;
import bomman.entity.EntityManager;
import bomman.entity.Flame;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.TilesManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * First type of enemy.
 */
public class FirstEnemy extends CommonEntity {
    private static int characterVelocity = 1;

    private int count = 0;

    private List<DIRECTION> canMove;

    public FirstEnemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        canMove = new ArrayList<>();
    }

    public static int getCharacterVelocity() {
        return characterVelocity;
    }

    public static void setCharacterVelocity(int characterVelocity) {
        FirstEnemy.characterVelocity = characterVelocity;
    }

    @Override
    public void update() {
        if (getAlive() == 0) {
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
                    if (collideBlocks(this, GameManager.map, TilesManager.gameTiles)) {
                        this.setDirect(this.getOppositeDirect());
                    }
                    if (GameManager.map[row][col] == 0 && !EntityManager.hasBomb(col, row)) {
                        canMove.add(this.getDirect());
                    }
                    if (canMove.size() != 0) {
                        int rand_dir = (int) (Math.random() * (canMove.size()));
                        this.setDirect(canMove.get(rand_dir));
                    }
                    for (Bomb b: Bomb.bombs) {
                        if (collisionWithEntity(this, b)) {
                            this.setDirect(this.getOppositeDirect());
                        }
                    }
                }
            }

            this.move(this.getDirect(), characterVelocity);
            canMove.clear();


            for (Flame i : Flame.flames) {
                if (collisionWithFlame(this, i)) {
                    this.setAlive(1);
                }
            }
        }
    }

    @Override
    public void render(GraphicsContext gc, double t) {
        double frame = (int) (t / 0.083) % 12 % 3;
        if (getAlive() == 1) {
            if (frame == 0) count++;
            if (count == 10) {
                this.setImg(Sprite.balloon_dead.getFxImage());
            }
            if (count == 20) {
                this.setImg(Sprite.mob_dead1.getFxImage());
            }
            if (count == 30) {
                this.setImg(Sprite.mob_dead2.getFxImage());
            }
            if (count == 40) {
                this.setImg(Sprite.mob_dead3.getFxImage());
            }
            if (count == 50) {
                setAlive(2);
            }
        } else {
            if (this.getDirect() == DIRECTION.LEFT) {
                if (frame == 0) this.setImg(Sprite.balloon_left1.getFxImage());
                if (frame == 1) this.setImg(Sprite.balloon_left2.getFxImage());
                if (frame == 2) this.setImg(Sprite.balloon_left3.getFxImage());
            }
            if (this.getDirect() == DIRECTION.RIGHT) {
                if (frame == 0) this.setImg(Sprite.balloon_right1.getFxImage());
                if (frame == 1) this.setImg(Sprite.balloon_right2.getFxImage());
                if (frame == 2) this.setImg(Sprite.balloon_right3.getFxImage());
            }
            if (this.getDirect() == DIRECTION.UP) {
                if (frame == 0) this.setImg(Sprite.balloon_left1.getFxImage());
                if (frame == 1) this.setImg(Sprite.balloon_left2.getFxImage());
                if (frame == 2) this.setImg(Sprite.balloon_left3.getFxImage());
            }
            if (this.getDirect() == DIRECTION.DOWN) {
                if (frame == 0) this.setImg(Sprite.balloon_right1.getFxImage());
                if (frame == 1) this.setImg(Sprite.balloon_right2.getFxImage());
                if (frame == 2) this.setImg(Sprite.balloon_right3.getFxImage());
            }
        }
        gc.drawImage(getImg(), getXPosition(), getYPosition());
    }
}
