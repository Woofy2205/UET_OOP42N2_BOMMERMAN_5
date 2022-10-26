package bomman.manager;

import bomman.MainGame;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuManager {
    private Stage stage;

    public void switchToGame(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        MainGame bomman = new MainGame();
        bomman.start(window);
    }
}
