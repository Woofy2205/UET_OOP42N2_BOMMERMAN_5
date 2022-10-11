package bomman;

import bomman.entity.CommonEntity;
import bomman.entity.EntityManager;
import bomman.entity.MainCharacter;
import bomman.entity.tiles.HiddenTiles;
import bomman.entity.tiles.UnbreakableTiles;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainGame extends Application {

    // gameManager to control the game, create map, entities and other stuffs.
    GameManager gameManager = new GameManager();
    static EntityManager entityManager = new EntityManager();

    static Scene mainScene;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    //private List<CommonEntity> entities = new ArrayList<CommonEntity>();
    //private List<CommonEntity> stillObjects = new ArrayList<>();

//  private static CommonEntity bomberman;

    static HashSet<String> currentlyActiveKeys;

    final long startNanoTime = System.nanoTime();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        mainScene = new Scene(root);
        prepareActionHandlers();

        // Them scene vao stage
        stage.setScene(mainScene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                tickAndRender();
                render();
                update();
            }
        };
        timer.start();

//      createMap();
        gameManager.createMapFromFile();
        entityManager.createEntity();
    }

//    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                CommonEntity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new UnbreakableTiles(i, j, Sprite.wall.getFxImage());
//                } else {
//                    object = new HiddenTiles(i, j, Sprite.grass.getFxImage());
//                }
//                gameManager.stillObjects.add(object);
//            }
//        }
//    }

    public void update() {
        entityManager.entities.forEach(CommonEntity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameManager.stillObjects.forEach(g -> g.render(gc));
        entityManager.entities.forEach(g -> g.render(gc));
    }

    private static void prepareActionHandlers() {
        // use a set so duplicates are not possible
        currentlyActiveKeys = new HashSet<String>();
        mainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.add(event.getCode().toString());
            }
        });
        mainScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                currentlyActiveKeys.remove(event.getCode().toString());
            }
        });
    }

    private static void tickAndRender() {
        if (currentlyActiveKeys.contains("LEFT")) {
            entityManager.bomberman.move(CommonEntity.DIRECTION.LEFT);
        }
        if (currentlyActiveKeys.contains("RIGHT")) {
            entityManager.bomberman.move(CommonEntity.DIRECTION.RIGHT);
        }
        if (currentlyActiveKeys.contains("UP")) {
            entityManager.bomberman.move(CommonEntity.DIRECTION.UP);
        }
        if (currentlyActiveKeys.contains("DOWN")) {
            entityManager.bomberman.move(CommonEntity.DIRECTION.DOWN);
        }
    }
}