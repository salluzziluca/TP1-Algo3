package view;

import controller.ObserverPasarTurno;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Carta;
import model.Jugador;
import model.ObserverSetCartaEnJuego;

import java.util.ArrayList;

public class BuilderEscenaTablero {

    ObserverPasarTurno observerPasarTurno;
    ObserverSetCartaEnJuego observerSetCartaEnJuego;
    ObserverRecargarEscena observerRecargarEscena;

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
                observerPasarTurno.pasarTurno();
        });

        ArrayList<Button> cartas = new ArrayList<>();
        ArrayList<Carta> cartasJugador = jugadorActual.getMano().getCartas();
        for (int i = 0; i < cartasJugador.size(); i++) {
                Carta carta = cartasJugador.get(i);
                Button cartaActual = new Button(carta.getNombre());
                cartaActual.prefWidthProperty().bind(borderPane.widthProperty());
                cartaActual.setMinHeight(100);

            int finalI = i;
            cartaActual.setOnAction(e -> {
                        if (carta.puedeJugarse(jugadorActual.getManaActual())) {
                            jugadorActual.getMano().jugarCarta(finalI, jugadorActual, jugadorOponente, observerSetCartaEnJuego);
                            carta.alJugarse(jugadorActual, jugadorOponente);
                            observerRecargarEscena.recargarEscena();

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("No tenes suficiente mana para jugar esta carta");
                            alert.setHeaderText("No tenes suficiente mana para jugar esta carta");
                            alert.setContentText("No tenes suficiente mana para jugar esta carta");
                            alert.show();
                        }
                    }
                );
                cartas.add(cartaActual);
                cartas.add(cartaActual);
                cajaH.getChildren().add(cartaActual);
            }






        borderPane.setBottom(cajaH);
        borderPane.setStyle("-fx-background-color: cyan;");
        borderPane.setTop(label);
        borderPane.setLeft(label2);
        borderPane.setRight(botonPasarTurno);

        return new Scene(borderPane, 500, 300);
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno, ObserverSetCartaEnJuego observerSetCartaEnJuego, ObserverRecargarEscena observerRecargarEscena) {
        this.observerPasarTurno = observerPasarTurno;
        this.observerSetCartaEnJuego = observerSetCartaEnJuego;
        this.observerRecargarEscena = observerRecargarEscena;
    }
}
