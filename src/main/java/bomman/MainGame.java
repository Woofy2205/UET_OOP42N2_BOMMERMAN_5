package bomman;

import bomman.entity.Bomb;
import bomman.entity.CommonEntity;
import bomman.entity.EntityManager;
import bomman.entity.Flame;
import bomman.event.EventHandling;
import bomman.manager.GameManager;
import bomman.manager.Sprite;
import bomman.tiles.TilesManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainGame extends Application {
    static Scene mainScene;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 30;

    private GraphicsContext gc;
    private Canvas canvas;
    final long startNanoTime = System.nanoTime();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(MainGame.class.getResource("/bomman/fxml/bomman.Menu.fxml")));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        mainScene = new Scene(root);
        EventHandling.prepareActionHandlers(mainScene);

        // Add scene to stage
        stage.setScene(mainScene);
        stage.show();

        GameManager.createMap();
        TilesManager.createTiles();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                GameManager.nextStage();
                if (GameManager.isLost()) {

                }
                if (GameManager.isWon()) {

                }
                update();
                render(t);
            }
        };
        timer.start();


        EntityManager.createMainCharacter();
        EntityManager.createEntity();
        //Bomb.bombs.forEach(Bomb::countDown);
    }

    public void update() {
        //Bomb.bombs.forEach(Bomb::countDown);
        Bomb.countDown();
        Bomb.countDown();
        Bomb.createFlame();
        Flame.flameCountdown();
        EntityManager.removeDeathEntity();
        EntityManager.bomberman.update();
        //Bomb.bombs.forEach(Bomb::update);
        EntityManager.entities.forEach(CommonEntity::update);
    }

    public void render(double t) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // gameManager.stillObjects.forEach(g -> g.render(gc, t));
        for (int i = 0; i < GameManager.GAME_HEIGHT; i++) {
            for (int j = 0; j < GameManager.GAME_WIDTH; j++) {
                TilesManager.gameTiles[i][j].render(gc, t);
            }
        }
        EntityManager.entities.forEach(g -> g.render(gc, t));
        Bomb.bombs.forEach(g -> g.render(gc, t));
        Flame.flames.forEach(g -> g.render(gc, t));
        //gameManager.gameTiles.forEach(g -> g.render(gc, t));
        EntityManager.bomberman.render(gc, t);
    }
}