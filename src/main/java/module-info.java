module bomman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens bomman to javafx.fxml;
    exports bomman;
}