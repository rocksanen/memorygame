module main.memorygame {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    requires org.controlsfx.controls;
    requires com.almasb.fxgl.all;

    opens main.memorygame to javafx.fxml;
    exports main.memorygame;
}