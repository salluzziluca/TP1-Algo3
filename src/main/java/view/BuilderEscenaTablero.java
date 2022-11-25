package view;

import controller.ObserverPasarTurno;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.*;
import java.util.ArrayList;
import java.util.Optional;

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

            VBox vboxCarta = new VBox();
            vboxCarta.setStyle("-fx-background-color: #62dc41; -fx-border-color: rgb(0,0,0);-fx-border-insets: 5;-fx-border-width: 3; -fx-border-radius: 5;");
            Label nombreCarta = new Label(carta.getNombre());
            Label barrita = new Label("------");
            Label descripcionCarta = new Label(carta.getDescripcion());
            descripcionCarta.setWrapText(true);
            descripcionCarta.setTextAlignment(TextAlignment.CENTER);
            nombreCarta.setWrapText(true);
            nombreCarta.setTextAlignment(TextAlignment.CENTER);
            Button botonMana = new Button(String.valueOf(carta.getCosto()));
            vboxCarta.getChildren().addAll(nombreCarta,barrita, descripcionCarta, botonMana);
            botonMana.prefWidthProperty().bind(vboxCarta.widthProperty());
            botonMana.setAlignment(Pos.BOTTOM_CENTER);
            vboxCarta.setMaxWidth(100);


            int finalI = i;
            botonMana.setOnAction(e -> {
                        if (carta.puedeJugarse(jugadorActual.getManaActual())) {
                            jugadorActual.getMano().jugarCarta(finalI, jugadorActual, jugadorOponente, observerSetCartaEnJuego);
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

            cajaHcartas.getChildren().add(vboxCarta);
            cajaHcartas.setAlignment(Pos.BOTTOM_CENTER);
        }


        borderPane.setBottom(cajaH);
        borderPane.setStyle("-fx-background-color: cyan;");
        borderPane.setTop(label);
        borderPane.setLeft(label2);
        borderPane.setRight(botonPasarTurno);

        return new Scene(borderPane, 1000, 700);
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno, ObserverSetCartaEnJuego observerSetCartaEnJuego, ObserverRecargarEscena observerRecargarEscena) {
        this.observerPasarTurno = observerPasarTurno;
        this.observerSetCartaEnJuego = observerSetCartaEnJuego;
        this.observerRecargarEscena = observerRecargarEscena;
    }
}
