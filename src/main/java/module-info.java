module bomman {
    requires javafx.controls;
    requires javafx.fxml;


    opens bomman to javafx.fxml;
    exports bomman;
}