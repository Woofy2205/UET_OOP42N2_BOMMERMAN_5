package bomman;

import bomman.manager.SoundManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

import static bomman.manager.GameManager.*;

public class MainGame extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MainGame.class.getResource("hello-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), GAME_WIDTH, GAME_LENGTH);
		stage.setTitle(GAME_TITLE);
		stage.setScene(scene);
		SoundManager.init();
		SoundManager.walk.play();

		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}