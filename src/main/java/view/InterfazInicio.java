package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InterfazInicio extends Application {
    public void start(Stage primaryStage) {

        VBox vcaja = new VBox();
        TextField textField = new TextField();
        Menu menuDeMazos = new Menu();
        MenuItem mazoGuerrero = new MenuItem();
        MenuItem mazoAlquimista = new MenuItem();
        MenuBar menuBar = new MenuBar();
        ChoiceBox<Object> choiceBox = new ChoiceBox<>();
        Label label = new Label("Jugador 1/2 ingrese su nombre y elija un mazo");
        HBox hbox = new HBox();
        Button botonSalir = new Button("Salir");
        Button botonContinuar = new Button("Continuar");

        textField.setMaxWidth(200);

        primaryStage.setTitle("Hello World");
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
        Scene escena = new Scene(vcaja, 500, 300);
        primaryStage.setScene(escena);
        primaryStage.show();
    }

    public static void start() {
        launch();
    }
}
