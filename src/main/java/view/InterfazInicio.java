package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.*;

public class InterfazInicio extends Application {
    Controller controller;

    public InterfazInicio() {
        controller = new Controller();
    }

    public InterfazInicio(Controller controller) {
        this.controller = controller;
    }
    public void start(Stage primaryStage) {
        BuilderEscenaInicio builderEscenaInicio = new BuilderEscenaInicio(primaryStage, controller);
        Scene escena2 = builderEscenaInicio.crearEscena(null);
        Scene escena1 = builderEscenaInicio.crearEscena(escena2);
        primaryStage.setScene(escena1);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
}
