package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuilderEscenaTablero {

        private final Stage primaryStage;
        private final Controller controller;

        public BuilderEscenaTablero(Stage primaryStage, Controller controller){
            this.primaryStage = primaryStage;
            this.controller = controller;
        }
        public Scene crearEscena(Scene escenaSiguiente) {
            FlowPane vcaja = new FlowPane();
            TextField textField = new TextField();
            Menu menuDeMazos = new Menu();
            MenuItem mazoGuerrero = new MenuItem();
            MenuItem mazoAlquimista = new MenuItem();
            MenuBar menuBar = new MenuBar();
            ChoiceBox<Object> choiceBox = new ChoiceBox<>();
            HBox hbox = new HBox();
            Label label = new Label();
            Button botonSalir = new Button();
            Button botonContinuar = new Button();

            label.setText("Jugador 1/2 ingrese su nombre y elija un mazo");
            botonSalir.setText("Salir");
            botonContinuar.setText("Continuar");
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

    }
