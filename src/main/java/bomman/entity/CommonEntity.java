package bomman.entity;

import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.CommonTiles;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class will control the common attributes, operations and functions of all entities.
 */
public abstract class CommonEntity {
    // Character size
    public static final int CHARACTER_SIZE = 64;

    // Variables for identifying the position of an entity
    private int xPosition;
    private int yPosition;

    private DIRECTION direct;


    private Image img;

    public CommonEntity(int xUnit, int yUnit, Image img) {
        this.xPosition = xUnit * Sprite.SCALED_SIZE;
        this.yPosition = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.direct = DIRECTION.DOWN;
    }

    // Enum class to decide which direction the living entities choose to move.
    public enum DIRECTION {
        // Enum for direction
        DOWN(0, 1),
        UP(0, -1),
        RIGHT(1, 0),
        LEFT(-1, 0);

        // Private int for declaration
        private int moveX;
        private int moveY;

        // Constructor for direction
        DIRECTION(final int _moveX, final int _moveY) {
            moveX = _moveX;
            moveY = _moveY;
        }
    }

//    public static boolean canMove (int x, int y, int[][] map) {
//        double xUnit = (double) x / Sprite.SCALED_SIZE;
//        double yUnit = (double) y / Sprite.SCALED_SIZE;
//        System.out.print("This is xUnit: " + xUnit + ", This is yUnit: " + yUnit + "\n");
//        if ((int)xUnit <= 0 || (int)yUnit <= 0 || (int)xUnit >= GameManager.GAME_WIDTH-2 || (int)yUnit >= GameManager.GAME_HEIGHT-2) return false;
//        System.out.print("This is yUnit: " + (int) Math.ceil (yUnit) + ", This is xUnit: " + (int) Math.ceil (xUnit) + "\n");
//        return (map[(int) Math.ceil (yUnit)][(int) Math.ceil (xUnit)] != 1);
//    }

    public static boolean canMove (DIRECTION direct, int x, int y, int[][] map) {
        //int xUnit = (int) Math.round ((double)x / Sprite.SCALED_SIZE);
        //int yUnit = (int) Math.round ((double)y / Sprite.SCALED_SIZE);
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;

        //System.out.print("y: " + yUnit + ", x: " + xUnit + "\n");
        int left = xUnit;
        int right = xUnit + 1;
        int top = yUnit;
        int bottom = yUnit + 1;

        if (direct == DIRECTION.UP) {
            return (map[top][left] != 1);
        }
        if (direct == DIRECTION.DOWN) {
            return (map[top][left] != 1 && map[bottom][left] != 1);
        }
        if (direct == DIRECTION.RIGHT) {
            return (map[top][left] != 1 && map[top][right] != 1);
        }
        if (direct == DIRECTION.LEFT) {
            if (map[top][left] != 1) {
                if (map[bottom][left+1] == 1 && map[bottom][left] == 1) return true;
                else if (map[bottom][left] == 1) return false;
                else return true;
            }
        }
        // return (map[top][left] != 1 && map[bottom][right] != 1 && map[top][right] != 1 && map[bottom][left] != 1);
        // if ((int)xUnit <= 0 || (int)yUnit <= 0 || (int)xUnit >= GameManager.GAME_WIDTH-2 || (int)yUnit >= GameManager.GAME_HEIGHT-2) return false;
        // return ((map[top][left] != 1 && map[bottom][right] != 1 && map[top][right] != 1 && map[bottom][left] != 1));
        return false;
    }

    public static boolean collisionWithTiles (CommonEntity entity, CommonTiles tile) {
        int entityLeft = entity.xPosition;
        int entityRight = entity.xPosition + Sprite.SCALED_SIZE;
        int entityTop = entity.yPosition;
        int entityBottom = entity.yPosition + Sprite.SCALED_SIZE;

        int tileLeft = tile.xTile;
        int tileRight = tile.xTile + Sprite.SCALED_SIZE;
        int tileTop = tile.yTile;
        int tileBottom = tile.yTile + Sprite.SCALED_SIZE;

        if(entityBottom <= tileTop || entityTop >= tileBottom || entityRight <= tileLeft || entityLeft >= tileRight) return false;
        return true;

    }

    public static void collide (CommonEntity entity, int[][] map, CommonTiles[][] tiles) {

        //System.out.print("y: " + yUnit + ", x: " + xUnit + "\n");

        for (int i = 0; i < GameManager.GAME_HEIGHT; i++) {
            for (int j = 0; j < GameManager.GAME_WIDTH; j++) {
                if (map[i][j] != 0 && collisionWithTiles(entity, tiles[i][j])) {
                    int value = map[i][j];
                    if (value == 2) {

                    } else if (MainCharacter.getCharacterVelocity() != 0) {
                        MainCharacter.setCharacterVelocity(0);
                    }
                }
            }
        }

    }

    // Moving function for the entities.
    public void move(DIRECTION direct, int velocity) {
        //System.out.print(xPosition + " " + yPosition + "\n");
        //if (canMove(xPosition + direct.moveX, yPosition + direct.moveY, manager.map)) {
            xPosition += direct.moveX * velocity;
            yPosition += direct.moveY * velocity;
        //}
        this.direct = direct;
    }

    /**
     * Getters and Setters.
     */
    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public DIRECTION getDirect() {
        return direct;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc, double t) {
        gc.drawImage(img, xPosition, yPosition);
    }

    public abstract void update();
}
