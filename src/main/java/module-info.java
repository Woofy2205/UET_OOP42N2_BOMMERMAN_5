module bomman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens bomman.manager to javafx.fxml;
    opens bomman to javafx.fxml;
    exports bomman;
}