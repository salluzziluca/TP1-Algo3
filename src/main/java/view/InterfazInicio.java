package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.*;

public class InterfazInicio extends Application {
    Controller controller = new Controller();

    public Scene crearEscena(Stage primaryStage, Scene escenaSiguiente) {
        VBox vcaja = new VBox();
        TextField textField = new TextField();
        Menu menuDeMazos = new Menu();
        MenuItem mazoGuerrero = new MenuItem();
        MenuItem mazoAlquimista = new MenuItem();
        MenuBar menuBar = new MenuBar();
        ChoiceBox<Object> choiceBox = new ChoiceBox<>();
        HBox hbox = new HBox();
        Label label = new Label("Jugador 1/2 ingrese su nombre y elija un mazo");
        Button botonSalir = new Button("Salir");
        Button botonContinuar = new Button("Continuar");

        botonSalir.setOnAction(e -> System.exit(0));

        botonContinuar.setOnAction(e -> {

            String nombreJugador = textField.getText();
            Object mazoElegido = choiceBox.getSelectionModel().getSelectedItem();
            controller.RecibirNombreYMazo(nombreJugador, mazoElegido.toString());
            primaryStage.setScene(escenaSiguiente);
            primaryStage.show();
        });

        textField.setMaxWidth(200);

        primaryStage.setTitle("Hola");
        mazoGuerrero.setText("Mazo Guerrero");
        mazoGuerrero.setText("Mazo Alquimista");
        menuDeMazos.getItems().addAll(mazoGuerrero, mazoAlquimista);
        menuBar.getMenus().add(menuDeMazos);

        choiceBox.getItems().add("Mazo Guerrero");
        choiceBox.getItems().add("Mazo Alquimista");

        hbox.getChildren().addAll(botonSalir, botonContinuar);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        vcaja.setAlignment(Pos.CENTER);
        vcaja.setStyle("-fx-background-color: cyan;");
        vcaja.getChildren().addAll(label, textField, choiceBox, hbox);
        VBox.setVgrow(hbox, Priority.ALWAYS);
        return new Scene(vcaja, 500, 300);
    }
    public void start(Stage primaryStage) {
        Scene escena2 = crearEscena(primaryStage, null);

        Scene escena1 = crearEscena(primaryStage, escena2);
        primaryStage.setScene(escena1);
        primaryStage.show();
    }

    public void start() {
        launch();
    }
}
