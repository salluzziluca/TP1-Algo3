package view;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Interfaz extends Application {
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Hello World");
        primaryStage.show();
        Label label = new Label("Hello World");

    }

    public static void start() {
        launch();
    }
}
