package view;

import controller.ObserverPasarTurno;
import controller.ObserverRecargarEscena;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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

    public Scene crearEscenaTablero(Jugador jugadorActual, Jugador jugadorOponente) {
        BorderPane borderPane = new BorderPane();
        Label manayVidaActual = new Label(String.format("Turno de %s\nVida: %d \nMana: %d", jugadorActual.getNombre(), jugadorActual.getVida(), jugadorActual.getManaActual()));
        Label vidaOponente = new Label(String.format("Vida %s: %d", jugadorOponente.getNombre(), jugadorOponente.getVida()));

        VBox vboxEfectosSecretos = new VBox();
        Button botonPasarTurno = new Button("Pasar turno");
        vboxEfectosSecretos.getChildren().add(botonPasarTurno);
        for (Efecto efecto : jugadorActual.getEfectos()) {
            Label efectosySecretosActual = new Label(String.format("%s(%d)", efecto.getNombre(), efecto.getDuracion()));
            vboxEfectosSecretos.getChildren().add(efectosySecretosActual);
        }
        for (Secreto secreto : jugadorActual.getSecretos()) {
            Label efectosySecretosActual = new Label(String.format("%s", secreto.getNombre()));
            vboxEfectosSecretos.getChildren().add(efectosySecretosActual);
        }


        HBox cajaHcartas = new HBox();
        cajaHcartas.maxHeight(400);


        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));
        botonPasarTurno.setOnAction(e -> observerPasarTurno.pasarTurno());


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
            vboxCarta.getChildren().addAll(nombreCarta, barrita, descripcionCarta, botonMana);
            botonMana.prefWidthProperty().bind(vboxCarta.widthProperty());
            botonMana.setAlignment(Pos.BOTTOM_CENTER);
            vboxCarta.setMaxWidth(100);


            int finalI = i;
            botonMana.setOnAction(e -> {
                        if (carta.puedeJugarse(jugadorActual.getManaActual())) {
                            jugadorActual.getMano().jugarCarta(finalI, jugadorActual, jugadorOponente, observerSetCartaEnJuego);
                            observerRecargarEscena.recargarEscena();
                            if (!jugadorOponente.estaVivo()) {
                                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                alerta.setContentText(String.format("El jugador %s ha perdido", jugadorOponente.getNombre()));
                                Optional<ButtonType> boton = alerta.showAndWait();
                                if (boton.get() == ButtonType.OK) {
                                    System.exit(0);
                                } else {
                                    System.exit(0);
                                }
                                alerta.show();
                            }

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("No se puede jugar la carta");
                            alert.setHeaderText("No tenes suficiente mana para jugar esta carta");
                            alert.show();
                        }
                    }
            );

            cajaHcartas.getChildren().add(vboxCarta);
            cajaHcartas.setAlignment(Pos.BOTTOM_CENTER);
        }

        BorderPane.setAlignment(cajaHcartas, Pos.CENTER);
        BorderPane.setAlignment(manayVidaActual, Pos.CENTER);
        BorderPane.setAlignment(vidaOponente, Pos.CENTER);
        BorderPane.setAlignment(vboxEfectosSecretos, Pos.CENTER);
        borderPane.setBottom(cajaHcartas);
        borderPane.setStyle("-fx-background-color: cyan;");
        borderPane.setTop(vidaOponente);
        borderPane.setLeft(manayVidaActual);
        borderPane.setRight(vboxEfectosSecretos);

        return new Scene(borderPane, 1000, 700);
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno, ObserverSetCartaEnJuego observerSetCartaEnJuego, ObserverRecargarEscena observerRecargarEscena) {
        this.observerPasarTurno = observerPasarTurno;
        this.observerSetCartaEnJuego = observerSetCartaEnJuego;
        this.observerRecargarEscena = observerRecargarEscena;
    }
}
