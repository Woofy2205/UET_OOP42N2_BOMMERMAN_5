package bomman;

import bomman.entity.Bomb;
import bomman.entity.CommonEntity;
import bomman.entity.EntityManager;
import bomman.event.EventHandling;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.CommonTiles;
import bomman.tiles.TilesManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class MainGame extends Application {

    // gameManager to control the game, create map, entities and other stuffs.
    GameManager gameManager = new GameManager();
    static Scene mainScene;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
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
        EventHandling.prepareActionHandlers(mainScene);

        // Them scene vao stage
        stage.setScene(mainScene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                update();
                render(t);
            }
        };
        timer.start();

        gameManager.createMapFromFile();
        EntityManager.createMainCharacter();
        //Bomb.bombs.forEach(Bomb::countDown);
    }

    public void update() {
        //Bomb.bombs.forEach(Bomb::countDown);
        Bomb.countDown();
        EntityManager.bomberman.update();
        //Bomb.bombs.forEach(Bomb::update);
        EntityManager.entities.forEach(CommonEntity::update);
    }

    public void render(double t) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameManager.stillObjects.forEach(g -> g.render(gc, t));
        for (int i = 0; i < GameManager.GAME_HEIGHT; i++) {
            for (int j = 0; j < GameManager.GAME_WIDTH; j++) {
                gameManager.gameTiles[i][j].render(gc, t);
            }
        }
        EntityManager.entities.forEach(g -> g.render(gc, t));
        Bomb.bombs.forEach(g -> g.render(gc, t));
        //gameManager.gameTiles.forEach(g -> g.render(gc, t));
        EntityManager.bomberman.render(gc,t);
    }
}