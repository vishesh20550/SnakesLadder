module com.example.snakesladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakesladder to javafx.fxml;
    exports com.example.snakesladder;
}