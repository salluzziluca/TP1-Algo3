package view;

import controller.ObserverRecibirNombreYMazo;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class BuilderEscenaInicio {

    ArrayList<ObserverRecibirNombreYMazo> ObserversRecibirNombreYMazo = new ArrayList<>();

    public Scene crearEscena() {
        VBox vcaja = new VBox();
        TextField textField = new TextField("Juan Doe");
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
            if (textField.getText().isEmpty()) {
                label.setText("POR FAVOR Ingrese un nombre");
            } else {
                String nombreJugador = textField.getText();
                Object mazoElegido = choiceBox.getSelectionModel().getSelectedItem();
                for (ObserverRecibirNombreYMazo observer : ObserversRecibirNombreYMazo) {
                    observer.recibirNombreYMazo(nombreJugador, mazoElegido.toString());
                }
            }

        });

        textField.setMaxWidth(200);
        mazoGuerrero.setText("Mazo Guerrero");
        mazoAlquimista.setText("Mazo Alquimista");
        menuDeMazos.getItems().addAll(mazoGuerrero, mazoAlquimista);
        menuBar.getMenus().add(menuDeMazos);

        choiceBox.getItems().add("Mazo Guerrero");
        choiceBox.getItems().add("Mazo Alquimista");
        choiceBox.setValue("Mazo Alquimista");

        hbox.getChildren().addAll(botonSalir, botonContinuar);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        vcaja.setAlignment(Pos.CENTER);
        vcaja.setStyle("-fx-background-color: cyan;");
        vcaja.getChildren().addAll(label, textField, choiceBox, hbox);
        VBox.setVgrow(hbox, Priority.ALWAYS);
        return new Scene(vcaja, 500, 300);
    }

    public void subscribe(ObserverRecibirNombreYMazo observer) {
        ObserversRecibirNombreYMazo.add(observer);
    }

}
