module bomman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens bomman to javafx.fxml;
    exports bomman;
}