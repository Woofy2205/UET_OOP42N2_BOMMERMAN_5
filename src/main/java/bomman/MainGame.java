package bomman;

import bomman.entity.CommonEntity;
import bomman.entity.MainCharacter;
import bomman.entity.tiles.HiddenTiles;
import bomman.entity.tiles.UnbreakableTiles;
import bomman.event.EventHandling;
import bomman.manager.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainGame extends Application {
    static Scene mainScene;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<CommonEntity> entities = new ArrayList<CommonEntity>();
    private List<CommonEntity> stillObjects = new ArrayList<>();

    private static MainCharacter bomman;

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
                int frame = (int) (t * 100);
                if(frame % 10 == 0) {
                    System.out.println();
                    update();
                    render();;
                }
            }
        };
        timer.start();

        createMap();

        bomman = new MainCharacter(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                CommonEntity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new UnbreakableTiles(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new HiddenTiles(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(CommonEntity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}