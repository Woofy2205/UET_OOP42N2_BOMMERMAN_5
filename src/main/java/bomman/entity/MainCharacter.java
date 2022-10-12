package bomman.entity;

import bomman.event.EventHandling;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This will control the main character =))))))) like, obviously =))))))))
 */
public class MainCharacter extends CommonEntity {
    // Start point of main character (can be changed so not 'final' type)
    private static int PLAYER_START_X;
    private static int PLAYER_START_Y;

    // Speed of the main character
    private static int CHARACTER_STEP = 16;

    // Other attributes
    private int explosionRadius;
    private int bombDamage;

    public MainCharacter(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Getters.
     */
    public int getBombDamage() {
        return bombDamage;
    }

    public static int getCharacterStep() {
        return CHARACTER_STEP;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    @Override
    public int getXPosition() {
        return super.getXPosition();
    }

    @Override
    public int getYPosition() {
        return super.getYPosition();
    }

    /**
     * Setters.
     */
    public static void setPlayerStartX(int playerStartX) {
        PLAYER_START_X = playerStartX;
    }

    public static void setPlayerStartY(int playerStartY) {
        PLAYER_START_Y = playerStartY;
    }

    @Override
    public void update() {
        if (EventHandling.currentlyActiveKeys.contains("LEFT")) {
            this.move(DIRECTION.LEFT);
        }
        if (EventHandling.currentlyActiveKeys.contains("RIGHT")) {
            this.move(DIRECTION.RIGHT);
        }
        if (EventHandling.currentlyActiveKeys.contains("UP")) {
            this.move(DIRECTION.UP);
        }
        if (EventHandling.currentlyActiveKeys.contains("DOWN")) {
            this.move(DIRECTION.DOWN);
        }
    }

    @Override
    public void render(GraphicsContext gc, double t) {
        double frame = (int) (t / 0.083) % 12 % 3;
        if (this.getDirect() == DIRECTION.LEFT) {
            if (EventHandling.currentlyActiveKeys.size() == 0) {
                this.setImg(Sprite.player_left.getFxImage());
            } else {
                if (frame == 0) this.setImg(Sprite.player_left.getFxImage());
                if (frame == 1) this.setImg(Sprite.player_left_1.getFxImage());
                if (frame == 2) this.setImg(Sprite.player_left_2.getFxImage());
            }
        }
        if (this.getDirect() == DIRECTION.RIGHT) {
            if (EventHandling.currentlyActiveKeys.size() == 0) {
                this.setImg(Sprite.player_right.getFxImage());
            } else {
                if (frame == 0) this.setImg(Sprite.player_right.getFxImage());
                if (frame == 1) this.setImg(Sprite.player_right_1.getFxImage());
                if (frame == 2) this.setImg(Sprite.player_right_2.getFxImage());
            }
        }
        if (this.getDirect() == DIRECTION.UP) {
            if (EventHandling.currentlyActiveKeys.size() == 0) {
                this.setImg(Sprite.player_up.getFxImage());
            } else {
                if (frame == 0) this.setImg(Sprite.player_up.getFxImage());
                if (frame == 1) this.setImg(Sprite.player_up_1.getFxImage());
                if (frame == 2) this.setImg(Sprite.player_up_2.getFxImage());
            }
        }
        if (this.getDirect() == DIRECTION.DOWN) {
            if (EventHandling.currentlyActiveKeys.size() == 0) {
                this.setImg(Sprite.player_down.getFxImage());
            } else {
                if (frame == 0) this.setImg(Sprite.player_down.getFxImage());
                if (frame == 1) this.setImg(Sprite.player_down_1.getFxImage());
                if (frame == 2) this.setImg(Sprite.player_down_2.getFxImage());
            }
        }
        gc.drawImage(getImg(), getXPosition(), getYPosition());
    }
}
