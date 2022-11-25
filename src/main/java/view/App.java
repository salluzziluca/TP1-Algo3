package view;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BuilderMazos;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BuilderEscenaInicio builderEscenaInicio = new BuilderEscenaInicio();
        BuilderEscenaTablero builderEscenaTablero = new BuilderEscenaTablero();
        BuilderMazos builderMazos = new BuilderMazos();
        Juego juego = new Juego(primaryStage, builderEscenaInicio, builderEscenaTablero, builderMazos);
        builderEscenaInicio.subscribe(juego);
        juego.jugarse();


    }
}
