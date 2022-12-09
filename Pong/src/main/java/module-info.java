module com.example.pong {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.pong to javafx.fxml;
    exports com.example.pong;
}