package bomman.entity;

import bomman.event.EventHandling;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This will control the main character =))))))) like, obviously =))))))))
 */
public class MainCharacter extends CommonEntity {

    // Start point of main character (can be changed so not 'final' type)
    public static int PLAYER_START_X = 5;
    public static int PLAYER_START_Y = 5;

    // Speed of the main character
    private static int characterVelocity = 4;

    // Other attributes
    private int explosionRadius;
    private int bombDamage;

    public MainCharacter(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Getters and Setters.
     */
    public static int getCharacterVelocity() {
        return characterVelocity;
    }

    public static void setCharacterVelocity(int characterVelocity) {
        MainCharacter.characterVelocity = characterVelocity;
    }

    public static void setPlayerStartX(int playerStartX) {
        PLAYER_START_X = playerStartX;
    }

    public static void setPlayerStartY(int playerStartY) {
        PLAYER_START_Y = playerStartY;
    }

    /**
     * Getters.
     */
    public int getBombDamage() {
        return bombDamage;
    }

    public int getExplosionRadius() {
        return explosionRadius;
    }

    public void moveEvent() {
        if (EventHandling.currentlyActiveKeys.contains("LEFT")) {
            this.setDirect(DIRECTION.LEFT);
            collide(MainCharacter.this, GameManager.getGameManager().map, GameManager.getGameManager().gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("RIGHT")) {
            this.setDirect(DIRECTION.RIGHT);
            collide(MainCharacter.this, GameManager.getGameManager().map, GameManager.getGameManager().gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("UP")) {
            this.setDirect(DIRECTION.UP);
            collide(MainCharacter.this, GameManager.getGameManager().map, GameManager.getGameManager().gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("DOWN")) {
            this.setDirect(DIRECTION.DOWN);
            collide(MainCharacter.this, GameManager.getGameManager().map, GameManager.getGameManager().gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        System.out.println(getXPosition() + " " + getYPosition());
    }

    public void plantBomb() {
        if (EventHandling.currentlyActiveKeys.contains("SPACE")) {
            int xPos = this.getXPosition() / Sprite.SCALED_SIZE;
            int yPos = this.getYPosition() / Sprite.SCALED_SIZE;
            if (!EntityManager.hasBomb(xPos, yPos)) {
                Bomb.bombs.add(new Bomb(xPos, yPos, Sprite.player_right.getFxImage(), 100));
                System.out.print(Bomb.bombs.size() + "\n");
            }
        }
    }

    public void autocorrect() {
        if (getDirect() == DIRECTION.COLLIDE) {
            System.out.println("collide");
            if (EventHandling.currentlyActiveKeys.contains("UP") || EventHandling.currentlyActiveKeys.contains("DOWN")) {
                int delta = getXPosition() % Sprite.SCALED_SIZE;
                if (delta < 20) {
                    setXPosition(getXPosition() - delta);
                }
                if (delta > 44) {
                    setXPosition(getXPosition() + (64 - delta));
                }
            }
            if (EventHandling.currentlyActiveKeys.contains("LEFT") || EventHandling.currentlyActiveKeys.contains("RIGHT")) {
                int delta = getYPosition() % Sprite.SCALED_SIZE;
                if (delta < 20) {
                    setYPosition(getYPosition() - delta);
                }
                if (delta > 44) {
                    setYPosition(getYPosition() + (64 - delta));
                }
            }
        }
    }

    @Override
    public void update() {
        moveEvent();
        autocorrect();
        plantBomb();
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
