package bomman.manager;

import bomman.entity.Bomb;
import bomman.entity.CommonEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will manage all the attributes, functions and operations in the game.
 */
public class GameManager {

    public static int[][] map = new int[GameManager.GAME_HEIGHT][GameManager.GAME_WIDTH];

    // Title of the game using in main
    public static final String GAME_TITLE = "BOMMAN";
    // Path to the stage map, used to load map from the function.
    private static String[] mapPath = new String[GameManager.STAGE_NUMBER];

    // Each sprite size (pixels)
    private static final int DEFAULT_SPRITE_SIZE = 32;
    public static final int GAME_WIDTH = 31;
    public static final int GAME_HEIGHT = 21;

    // In-game private attribute
    private static final int STAGE_NUMBER = 5;
    public static int currentStage = 1;
    // private static int score = 0;
    // private static int coin = 0;
    public static boolean nextStage = false;
    private static boolean won = false;
    private static boolean restart = false;
    public static boolean lost = false;

    // List of entity that will be rendered.
    public List<CommonEntity> stillObjects = new ArrayList<>();

    // public variables that can be changed in game
    public static int bommanHealth = 5;
    public static int enemyHealth = 3;
    public int enemyNumber = 5;
    // public int bossHealth = 5;

    /**
     * Constructor for the game manager, basically load the main sprites.
     */
    private static void setMapPath() {
        mapPath[0] = "src/main/resources/bomman/maps/map0.txt";
        mapPath[1] = "src/main/resources/bomman/maps/map1.txt";
        mapPath[2] = "src/main/resources/bomman/maps/map2.txt";
        mapPath[3] = "src/main/resources/bomman/maps/map3.txt";
        mapPath[4] = "src/main/resources/bomman/maps/map4.txt";
    }

    /**
     * This function read a file and load the images from that file to the real map.
     */
    public static void createMap() {
        setMapPath();
        String path = mapPath[currentStage];
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                for (int i = 0; i < map.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        map[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nextStage() {
        if (GameManager.nextStage) {
            EntityManager.entities.clear();
            EntityManager.createMainCharacter();
            EntityManager.createEntity();
            GameManager.createMap();
            TilesManager.createTiles();
            GameManager.nextStage = false;
        }
    }

    /**
     * reset function for the next stage.
     */
    public static void reset() {
        bommanHealth = 5;
        enemyHealth = 3;
        nextStage = false;
        restart = false;
        won = false;
        lost = false;
        EntityManager.entities.clear();
    }

    /**
     * restart function for the game.
     */
    public static void restart() {
        Bomb.explosionRadius = 1;
        Bomb.bombLimit = 5;
        currentStage = 1;
        // score = 0;
        bommanHealth = 5;
        enemyHealth = 3;
        nextStage = false;
        restart = false;
        won = false;
        lost = false;
        EntityManager.entities.clear();
    }

    /**
     * Getters.
     */

    public static boolean isLost() {
        return lost;
    }

    public static boolean isWon() {
        return won;
    }

    public boolean isRestart() {
        return restart;
    }

    public boolean isNextStage() {
        return nextStage;
    }
}
