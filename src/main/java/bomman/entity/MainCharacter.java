package bomman.entity;

import bomman.event.EventHandling;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.CommonTiles;
import bomman.tiles.TilesManager;
import bomman.tiles.buffs.Buff;
import bomman.tiles.buffs.IncreaseRange;
import bomman.tiles.buffs.SpeedUp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * This will control the main character =))))))) like, obviously =))))))))
 */
public class MainCharacter extends CommonEntity {

    // Start point of main character (can be changed so not 'final' type)
    public static int PLAYER_START_X = 5;
    public static int PLAYER_START_Y = 5;

    // Speed of the main character
    private static int characterVelocity = 2;

    // Other attributes
    private int bombDamage;

    // public static List<Buff> buffs = new ArrayList<Buff>();

    public MainCharacter(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Getters.
     */
    public int getBombDamage() {
        return bombDamage;
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

    public static void collideMainCharacter(MainCharacter mainCharacter, int[][] map, CommonTiles[][] tiles) {
        for (int i = 0; i < GameManager.GAME_HEIGHT; i++) {
            for (int j = 0; j < GameManager.GAME_WIDTH; j++) {
                if (map[i][j] != 0 && collisionWithTiles(mainCharacter, tiles[i][j])) {
                    int value = map[i][j];
                    if (value == 3) {
                        GameManager.map[i][j] = 0;
                        GameManager.currentStage++;
                        GameManager.nextStage = true;
                        break;
                    } else if (value == 4) {
                        GameManager.map[i][j] = 0;
                        TilesManager.gameTiles[i][j].setImg(Sprite.grass.getFxImage());
                        Buff.buffs.add(new SpeedUp(j, i));
                        SpeedUp.executeBuff(mainCharacter);
                    } else if (value == 5) {
                        GameManager.map[i][j] = 0;
                        TilesManager.gameTiles[i][j].setImg(Sprite.grass.getFxImage());
                        Buff.buffs.add(new IncreaseRange(j, i));
                        IncreaseRange.executeBuff(mainCharacter);
                    } else {
                        mainCharacter.setDirect(DIRECTION.COLLIDE);
                    }
                }
            }
        }
    }

    public void moveEvent() {
        if (EventHandling.currentlyActiveKeys.contains("LEFT")) {
            this.setDirect(DIRECTION.LEFT);
            collideMainCharacter(MainCharacter.this, GameManager.map, TilesManager.gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("RIGHT")) {
            this.setDirect(DIRECTION.RIGHT);
            collideMainCharacter(MainCharacter.this, GameManager.map, TilesManager.gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("UP")) {
            this.setDirect(DIRECTION.UP);
            collideMainCharacter(MainCharacter.this, GameManager.map, TilesManager.gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
        if (EventHandling.currentlyActiveKeys.contains("DOWN")) {
            this.setDirect(DIRECTION.DOWN);
            collideMainCharacter(MainCharacter.this, GameManager.map, TilesManager.gameTiles);
            this.move(this.getDirect(), characterVelocity);
        }
    }

    public void plantBomb() {
        if (EventHandling.currentlyActiveKeys.contains("SPACE")) {
            int xPos = this.getXPosition() / Sprite.SCALED_SIZE;
            int yPos = this.getYPosition() / Sprite.SCALED_SIZE;
            if (!EntityManager.hasBomb(xPos, yPos)) {
                Bomb.bombs.add(new Bomb(xPos, yPos, Sprite.bomb.getFxImage(), 100));
            }
        }
    }

    public void autocorrect() {
        if (getDirect() == DIRECTION.COLLIDE) {
            if (EventHandling.currentlyActiveKeys.contains("UP") || EventHandling.currentlyActiveKeys.contains("DOWN")) {
                int delta = getXPosition() % Sprite.SCALED_SIZE;
                if (delta < 20) {
                    setXPosition(getXPosition() - delta);
                }
                if (delta > 44) {
                    setXPosition(getXPosition() + (64 - delta));
                }
            } else if (EventHandling.currentlyActiveKeys.contains("LEFT") || EventHandling.currentlyActiveKeys.contains("RIGHT")) {
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

    public void coolDownBuff() {
        Buff.buffs.forEach(Buff::update);
        List<Buff> removeBuffs = new ArrayList<Buff>();
        for (Buff b : Buff.buffs) {
            if (Buff.getCoolDown() <= 0) {
                removeBuffs.add(b);
            }
        }
        for (Buff b : removeBuffs) {
            Buff.buffs.remove(b);
        }
    }

    @Override
    public void update() {
        moveEvent();
        autocorrect();
        plantBomb();
        coolDownBuff();
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
