package src.view;

import javafx.application.Application;
import javafx.scene.Scene;

public class Interfaz extends Application {
    @Override
    public void helloWorld(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            // hello world
            Label label = new Label("Hello World");
            root.setCenter(label);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
