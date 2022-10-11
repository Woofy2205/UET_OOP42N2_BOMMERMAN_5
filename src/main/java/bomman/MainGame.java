package bomman;

import bomman.entity.EntityManager;
import bomman.entity.MainCharacter;
import bomman.entity.tiles.HiddenTiles;
import bomman.entity.tiles.UnbreakableTiles;
import bomman.manager.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;


import bomman.entity.CommonEntity;
import bomman.manager.Sprite;

public class MainGame extends Application {
	// gameManager to control the game, create map, entities and other stuffs.
	GameManager gameManager = new GameManager();
	EntityManager entityManger = new EntityManager();

	public static final int WIDTH = 20;
	public static final int HEIGHT = 15;

	private GraphicsContext gc;
	private Canvas canvas;


	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		// Canvas creating
		canvas = new Canvas(Sprite.SCALED_SIZE * gameManager.GAME_WIDTH, Sprite.SCALED_SIZE * gameManager.GAME_HEIGHT);
		gc = canvas.getGraphicsContext2D();

		// Root container creating
		Group root = new Group();
		root.getChildren().add(canvas);

		// Scene creating
		Scene scene = new Scene(root);

		// Add scene into stage
		stage.setScene(scene);
		stage.show();

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				render();
				update();
			}
		};
		timer.start();

		gameManager.createMapFromFile();
		entityManger.createEntity();

//		createMap();
//		gameManager.createEntity();

//		CommonEntity bomberman = new MainCharacter(1, 1, Sprite.player_right.getFxImage());
//		gameManager.entities.add(bomberman);
	}

//	public void createMap() {
//		for (int i = 0; i < WIDTH; i++) {
//			for (int j = 0; j < HEIGHT; j++) {
//				CommonEntity object;
//				if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//					object = new UnbreakableTiles(i, j, Sprite.wall.getFxImage());
//				}
//				else {
//					object = new HiddenTiles(i, j, Sprite.grass.getFxImage());
//				}
//				gameManager.stillObjects.add(object);
//			}
//		}
//	}

	public void update() {
		entityManger.entities.forEach(CommonEntity::update);
	}

	public void render() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gameManager.stillObjects.forEach(g -> g.render(gc));
		entityManger.entities.forEach(g -> g.render(gc));
	}
}