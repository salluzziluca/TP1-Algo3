package view;

import controller.ObserverPasarTurno;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Jugador;

import java.util.ArrayList;

public class BuilderEscenaTablero {

    ArrayList<ObserverPasarTurno> ObserversPasarTurno = new ArrayList<>();

    public Scene crearEscenaTablero(Jugador Jugador1, Jugador Jugador2) {
        BorderPane borderPane = new BorderPane();

        Label label = new Label();
        Label label2 = new Label();
        Label label3 = new Label();
        Button botonSalir = new Button();
        Button botonPasarTurno = new Button();
        HBox cajaH = new HBox();
        cajaH.maxHeight(400);
        ArrayList<Button> cartas = new ArrayList<>();

        cajaH.alignmentProperty().set(Pos.CENTER);
        label.setText("Juegen capos");
        label2.setText(Jugador1.getNombre());
        label3.setText(Jugador2.getNombre());
        botonSalir.setText("Salir");
        botonPasarTurno.setText("Pasar turno");
        botonSalir.setOnAction(e -> System.exit(0));
        botonPasarTurno.setOnAction(e -> {
            for (ObserverPasarTurno observer : ObserversPasarTurno) {
                observer.pasarTurno();
            }
        });

        for (int i = 0; i < Jugador1.getMano().getCartas().size(); i++) {
            Button cartaActual = new Button();
            cartaActual.prefWidthProperty().bind(borderPane.widthProperty());
            cartaActual.setMinHeight(100);
            cartaActual.setText(Jugador1.getMano().getCartas().get(i).getNombre());
            cartas.add(cartaActual);
/*            cartas.get(i).setOnAction(e -> {
                for (int j = 0; j < cartas.size(); j++) {
                    if (e.getSource() == cartas.get(j)) {
                        Jugador1.getMazo().getCartas().get(j).accionar();
                    }
                }
            });*/
            cajaH.getChildren().add(cartas.get(i));
        }

        borderPane.setBottom(cajaH);
        borderPane.setStyle("-fx-background-color: cyan;");
        borderPane.setTop(label);
        borderPane.setLeft(label2);
        borderPane.setRight(botonPasarTurno);

        return new Scene(borderPane, 500, 300);
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno) {
        ObserversPasarTurno.add(observerPasarTurno);
    }
}
