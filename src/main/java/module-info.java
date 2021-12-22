module com.example.snakesladder {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.snakesladder to javafx.fxml;
    exports com.example.snakesladder;
}