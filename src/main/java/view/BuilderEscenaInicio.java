package view;

import controller.ObserverRecargarEscena;
import controller.ObserverRecibirNombreYMazo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BuilderEscenaInicio {

    private ObserverRecibirNombreYMazo observerRecibirNombreYMazo;

    private ObserverRecargarEscena observerRecargarEscena;

    public Scene crearEscena() {

        Label labelArriba = new Label("Jugadores ingresen su nombre y elijan un mazo");

        TextField ingresarNombreJugador1 = new TextField("Jugador 1");
        ingresarNombreJugador1.setMaxWidth(200);

        ChoiceBox<Object> choiceBox1 = new ChoiceBox<>();
        choiceBox1.getItems().add("Mazo Guerrero");
        choiceBox1.getItems().add("Mazo Alquimista");
        choiceBox1.setValue("Mazo Guerrero");

        TextField ingresarNombreJugador2 = new TextField("Jugador 2");
        ingresarNombreJugador2.setMaxWidth(200);

        ChoiceBox<Object> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().add("Mazo Guerrero");
        choiceBox2.getItems().add("Mazo Alquimista");
        choiceBox2.setValue("Mazo Alquimista");


        VBox vBoxPrincipal = new VBox();


        Scene scene = new Scene(vBoxPrincipal, 500, 300);

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));

        Button botonInstrucciones = new Button("Instrucciones");
        botonInstrucciones.setOnAction(e -> observerRecargarEscena.crearEscenaInstrucciones(scene));

        Button botonContinuar = new Button("Continuar");
        botonContinuar.setOnAction(e -> {
            if (ingresarNombreJugador1.getText().isEmpty() || ingresarNombreJugador2.getText().isEmpty()) {
                labelArriba.setText("Por favor ingrese un nombre para ambos jugadores");
            } else if (ingresarNombreJugador1.getText().equals(ingresarNombreJugador2.getText())) {
                labelArriba.setText("El nombre de los jugadores tiene que ser distinto");
            } else {
                String nombreJugador1 = ingresarNombreJugador1.getText();
                String mazoElegido1 = choiceBox1.getSelectionModel().getSelectedItem().toString();
                String nombreJugador2 = ingresarNombreJugador2.getText();
                String mazoElegido2 = choiceBox2.getSelectionModel().getSelectedItem().toString();
                observerRecibirNombreYMazo.recibirNombreYMazo(nombreJugador1, mazoElegido1, nombreJugador2, mazoElegido2);
            }
        });

        HBox hBoxBotones = new HBox(botonSalir, botonInstrucciones, botonContinuar);
        hBoxBotones.setAlignment(Pos.BOTTOM_CENTER);

        VBox.setVgrow(hBoxBotones, Priority.ALWAYS);
        vBoxPrincipal.setAlignment(Pos.CENTER);
        vBoxPrincipal.setStyle("-fx-background-color: #3fe5d1;");
        vBoxPrincipal.getChildren().addAll(labelArriba, ingresarNombreJugador1, choiceBox1, ingresarNombreJugador2, choiceBox2, hBoxBotones);


        return scene;
    }

    public void subscribe(ObserverRecibirNombreYMazo observer, ObserverRecargarEscena observerRecargarEscena) {
        this.observerRecibirNombreYMazo = observer;
        this.observerRecargarEscena = observerRecargarEscena;
    }

}
