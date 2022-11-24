package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.*;


public class InterfazTablero extends Application {
    Controller controller;

    public InterfazTablero(Controller controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        BuilderEscenaTablero builderEscenaTablero = new BuilderEscenaTablero(primaryStage, controller);
        Scene escena1 = builderEscenaTablero.crearEscena(null);
        primaryStage.setScene(escena1);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
}
