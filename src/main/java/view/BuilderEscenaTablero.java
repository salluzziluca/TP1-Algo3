package view;

import controller.Juego;
import controller.ObserverPasarTurno;
import controller.ObserverRecargarEscena;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import model.*;

import java.util.ArrayList;
import java.util.Collections;

public class BuilderEscenaTablero {
    ObserverPasarTurno observerPasarTurno;
    ObserverSetCartaEnJuego observerSetCartaEnJuego;
    ObserverRecargarEscena observerRecargarEscena;

    private static VBox crearVBoxOponente(Jugador jugadorOponente) {
        Label vidaOponente = new Label(String.format("Vida %s: %d", jugadorOponente.getNombre(), jugadorOponente.getVida()));
        vidaOponente.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        HBox hBoxEfectosSecretosOponente = new HBox();

        añadirEfectosySecretosALayout(jugadorOponente, hBoxEfectosSecretosOponente.getChildren(), true);

        VBox vboxOponente = new VBox(vidaOponente, hBoxEfectosSecretosOponente);
        vboxOponente.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");
        vboxOponente.setAlignment(Pos.TOP_CENTER);

        return vboxOponente;
    }

    private static void añadirEfectosySecretosALayout(Jugador jugador, ObservableList<Node> children, Boolean jugadorEsOponente) {
        for (Efecto efecto : jugador.getEfectos()) {
            String efectoDuracion;
            if (efecto.getDuracion() < 0) efectoDuracion = "Permanente";
            else efectoDuracion = Integer.toString(efecto.getDuracion());

            Label efectoActual = new Label(String.format("%s(%s)", efecto.getNombre(), efectoDuracion));

            efectoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            efectoActual.setWrapText(true);
            efectoActual.setPadding(new Insets(10));

            Tooltip tooltip = new Tooltip(efecto.getDescripcion());
            tooltip.setShowDelay(Duration.seconds(0.3));
            efectoActual.setTooltip(tooltip);

            children.add(efectoActual);
        }
        for (Secreto secreto : jugador.getSecretos()) {
            Label secretoActual;
            Tooltip tooltip;

            if (jugadorEsOponente) {
                secretoActual = new Label("???");
                tooltip = new Tooltip("???");
            } else {
                secretoActual = new Label(secreto.getNombre());
                tooltip = new Tooltip(secreto.getDescripcion());
            }
            secretoActual.setFont(Font.font("verdana", FontPosture.REGULAR, 15));
            secretoActual.setWrapText(true);
            secretoActual.setPadding(new Insets(10));

            tooltip.setShowDelay(Duration.seconds(0.3));
            secretoActual.setTooltip(tooltip);

            children.add(secretoActual);
        }
    }

    private VBox crearEstadisticasJugadorActual(Jugador jugadorActual, Scene scene) {
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));

        Button botonInstrucciones = new Button("Instrucciones");
        botonInstrucciones.setOnAction(e -> observerRecargarEscena.crearEscenaInstrucciones(scene));

        Label jugadorLabel = new Label("Turno de " + jugadorActual.getNombre());
        jugadorLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        Label vidaLabel = new Label("Vida: " + jugadorActual.getVida());
        vidaLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        Label manaLabel = new Label(String.format("Mana: %d/%d", jugadorActual.getManaMaximo(), jugadorActual.getManaActual()));
        manaLabel.setFont(Font.font("verdana", FontPosture.REGULAR, 15));

        VBox estadisticasJugadorActual = new VBox(botonSalir, botonInstrucciones, jugadorLabel, vidaLabel, manaLabel);
        estadisticasJugadorActual.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");
        estadisticasJugadorActual.setAlignment(Pos.CENTER);

        return estadisticasJugadorActual;
    }

    public Scene crearEscenaTablero(Jugador jugadorActual, Jugador jugadorOponente) {
        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(borderPane, 1000, 700);

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

        borderPane.setBottom(crearCajaHcartas(jugadorActual, jugadorOponente));
        borderPane.setTop(crearVBoxOponente(jugadorOponente));
        borderPane.setLeft(crearEstadisticasJugadorActual(jugadorActual, scene));
        borderPane.setRight(crearVboxEfectosSecretos(jugadorActual));


        return scene;
    }

    private VBox crearVboxEfectosSecretos(Jugador jugadorActual) {
        VBox vboxEfectosSecretos = new VBox();
        vboxEfectosSecretos.setStyle("-fx-background-color: #ffffff; -fx-border-color: rgb(0,0,0);-fx-border-width: 3;");

        Button botonPasarTurno = new Button("Pasar turno");
        botonPasarTurno.setMinWidth(100);
        botonPasarTurno.maxWidthProperty().bind(vboxEfectosSecretos.widthProperty());
        botonPasarTurno.setOnAction(e -> observerPasarTurno.pasarTurno());
        vboxEfectosSecretos.getChildren().add(botonPasarTurno);

        añadirEfectosySecretosALayout(jugadorActual, vboxEfectosSecretos.getChildren(), false);

        return vboxEfectosSecretos;
    }

    private HBox crearCajaHcartas(Jugador jugadorActual, Jugador jugadorOponente) {
        HBox cajaHcartas = new HBox();
        cajaHcartas.maxHeight(300);


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

                            observerRecargarEscena.recargarEscenaTablero();

                            if (jugadorOponente.estaMuerto()) Juego.alertaFinJuego(jugadorOponente);

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Mana insuficiente");
                            alert.setHeaderText("No tenes suficiente mana para jugar esta carta");
                            alert.show();
                        }
                    }
            );

            cajaHcartas.getChildren().add(borderPCarta);
            cajaHcartas.setAlignment(Pos.BOTTOM_CENTER);
        }
        return cajaHcartas;
    }

    public void subscribe(ObserverPasarTurno observerPasarTurno, ObserverSetCartaEnJuego observerSetCartaEnJuego, ObserverRecargarEscena observerRecargarEscena) {
        this.observerPasarTurno = observerPasarTurno;
        this.observerSetCartaEnJuego = observerSetCartaEnJuego;
        this.observerRecargarEscena = observerRecargarEscena;
    }
}
