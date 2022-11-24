package view;

import controller.Juego;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import model.Jugador;

public class BuilderEscenaTablero {

        public Scene crearEscenaTablero(Jugador Jugador1, Jugador Jugador2) {
            FlowPane vcaja = new FlowPane();
            Label label = new Label();
            Label label2 = new Label();
            Label label3 = new Label();
            Button botonSalir = new Button();
            Button botonContinuar = new Button();

            label.setText("Juegen capos");
            label2.setText(Jugador1.getNombre());
            label3.setText(Jugador2.getNombre());
            botonSalir.setText("Salir");
            botonContinuar.setText("Continuar");
            botonSalir.setOnAction(e -> System.exit(0));
            vcaja.setAlignment(Pos.CENTER);
            vcaja.setStyle("-fx-background-color: cyan;");
            vcaja.getChildren().addAll(label, label2, label3, botonSalir, botonContinuar);

            return new Scene(vcaja, 500, 300);
        }

    }
