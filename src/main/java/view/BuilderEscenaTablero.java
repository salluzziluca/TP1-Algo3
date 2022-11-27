package view;

import controller.ObserverPasarTurno;
import controller.ObserverRecargarEscena;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class BuilderEscenaTablero {

    ObserverPasarTurno observerPasarTurno;
    ObserverSetCartaEnJuego observerSetCartaEnJuego;
    ObserverRecargarEscena observerRecargarEscena;

    public Scene crearEscenaTablero(Jugador jugadorActual, Jugador jugadorOponente) {
        BorderPane borderPane = new BorderPane();

        Label jugadorLabel = new Label("Turno de " + jugadorActual.getNombre());
        jugadorLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        Label vidaLabel = new Label("Vida: " + jugadorActual.getVida());
        vidaLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
        Label manaLabel = new Label(String.format("Mana: %d/%d", jugadorActual.getManaMaximo(), jugadorActual.getManaActual()));
        manaLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        VBox estadisticasJugadorActual = new VBox(jugadorLabel, vidaLabel, manaLabel);
        estadisticasJugadorActual.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");
        estadisticasJugadorActual.setAlignment(Pos.CENTER);

        Label vidaOponente = new Label(String.format("Vida %s: %d", jugadorOponente.getNombre(), jugadorOponente.getVida()));
vidaOponente. setFont(Font.font("verdana", FontPosture.REGULAR, 15));


        HBox hBoxEfectosSecretosOponente = new HBox();

        for (Efecto efecto : jugadorOponente.getEfectos()) {
            final Label efectoActual = new Label(String.format("%s(%d)", efecto.getNombre(), efecto.getDuracion()));
            efectoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            efectoActual.setWrapText(true);
            efectoActual.setPadding(new Insets(10));

            efectoActual.setTooltip(new Tooltip(efecto.getDescripcion()));

            hBoxEfectosSecretosOponente.getChildren().add(efectoActual);
        }
        for (Secreto secreto : jugadorOponente.getSecretos()) {
            Label secretoActual = new Label("???");
            secretoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            secretoActual.setWrapText(true);
            secretoActual.setPadding(new Insets(10));


            secretoActual.setTooltip(new Tooltip("???"));

            hBoxEfectosSecretosOponente.getChildren().add(secretoActual);
        }

        VBox vboxOponente = new VBox(vidaOponente, hBoxEfectosSecretosOponente);
        vboxOponente.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");
        vboxOponente.setAlignment(Pos.TOP_CENTER);

        VBox vboxEfectosSecretos = new VBox();
        vboxEfectosSecretos.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");
        Button botonPasarTurno = new Button("Pasar turno");
        vboxEfectosSecretos.getChildren().add(botonPasarTurno);

        for (Efecto efecto : jugadorActual.getEfectos()) {
            final Label efectoActual = new Label(String.format("%s(%d)", efecto.getNombre(), efecto.getDuracion()));
            efectoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            efectoActual.setWrapText(true);
            efectoActual.setPadding(new Insets(10));

            efectoActual.setTooltip(new Tooltip(efecto.getDescripcion()));

            vboxEfectosSecretos.getChildren().add(efectoActual);
        }
        for (Secreto secreto : jugadorActual.getSecretos()) {
            Label secretoActual = new Label(secreto.getNombre());
            secretoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            secretoActual.setWrapText(true);
            secretoActual.setPadding(new Insets(10));

            secretoActual.setTooltip(new Tooltip(secreto.getDescripcion()));

            vboxEfectosSecretos.getChildren().add(secretoActual);
        }

        HBox cajaHcartas = new HBox();
        cajaHcartas.maxHeight(300);

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));
        botonPasarTurno.setOnAction(e -> observerPasarTurno.pasarTurno());

        ArrayList<Carta> cartasJugador = jugadorActual.getMano().getCartas();
        for (int i = 0; i < cartasJugador.size(); i++) {
            Carta carta = cartasJugador.get(i);

            BorderPane borderPCarta = new BorderPane();
            borderPCarta.setStyle("-fx-background-color: #7c6666; -fx-border-color: rgb(0,0,0);-fx-border-insets: 5;-fx-border-width: 3; -fx-border-radius: 5;");
            borderPCarta.setPrefWidth(150);
            borderPCarta.setPrefHeight(250);

            Label nombreCarta = new Label(carta.getNombre());
            nombreCarta.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            nombreCarta.setWrapText(true);
            nombreCarta.setTextAlignment(TextAlignment.CENTER);

            Label descripcionCarta = new Label(carta.getDescripcion());
            descripcionCarta.setWrapText(true);
            descripcionCarta.setPadding(new Insets(10));


            Button botonMana = new Button(String.valueOf(carta.getCosto()));
            botonMana.prefWidthProperty().bind(borderPCarta.widthProperty());

            borderPCarta.setTop(nombreCarta);
            borderPCarta.setCenter(descripcionCarta);
            borderPCarta.setBottom(botonMana);

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

            cajaHcartas.getChildren().add(borderPCarta);
            cajaHcartas.setAlignment(Pos.BOTTOM_CENTER);
        }

        borderPane.setBackground(
                new Background(
                        Collections.singletonList(new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(0),
                                new Insets(0))),
                        Collections.singletonList(new BackgroundImage(
                                new Image("file:src/main/java/view/images/foto.jpg", 1000, 700, false, true),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(1.0, 1.0, true, true, false, false)
                        ))));
        borderPane.setBottom(cajaHcartas);
        borderPane.setTop(vboxOponente);
        borderPane.setLeft(estadisticasJugadorActual);
        borderPane.setRight(vboxEfectosSecretos);

        return new Scene(borderPane, 1000, 700);
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno, ObserverSetCartaEnJuego observerSetCartaEnJuego, ObserverRecargarEscena observerRecargarEscena) {
        this.observerPasarTurno = observerPasarTurno;
        this.observerSetCartaEnJuego = observerSetCartaEnJuego;
        this.observerRecargarEscena = observerRecargarEscena;
    }
}
