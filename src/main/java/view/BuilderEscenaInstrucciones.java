package view;

import controller.ObserverRecargarEscena;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class BuilderEscenaInstrucciones {

    ObserverRecargarEscena observerRecargarEscena;

    private static Label getLabel(String texto) {
        Label label = new Label(texto);
        label.setFont(Font.font("verdana", FontPosture.REGULAR, 12));
        label.setWrapText(true);
        label.setPadding(new Insets(5));
        return label;
    }

    public Scene crearEscenaInstrucciones(Scene escenaPrevia) {

        Label labelArriba = getLabel("Instrucciones");
        labelArriba.setFont(Font.font("verdana", FontWeight.BOLD, 18));

        Label instrucciones = getLabel("El objetivo del juego es reducir la vida de tu oponente a 0. Para eso utilizaras cartas que, pricipalmente, hagan daño a tu oponente, apliquen un efecto a un jugador o pongan un secreto que se activará cuando se den las condiciones.");

        Label efectos = getLabel("Efecto\tSignificado\n" +
                "Vulnerable\tSe duplica el daño recibido\n" +
                "Afilado\tLas cartas de Daño tienen +1 de daño\n" +
                "Berserk\t+1 de mana máximo\n" +
                "Veneno\tAl inicio del turno se recibe daño equivalente a la duracion del efecto\n" +
                "Inflacion\tAumenta x el costo de todas las cartas en mano");

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));

        Button botonVolver = new Button("Volver");
        botonVolver.setOnAction(e -> observerRecargarEscena.recargarEscenaAnterior(escenaPrevia));

        HBox hBoxBotones = new HBox(botonSalir, botonVolver);
        hBoxBotones.setAlignment(Pos.BOTTOM_CENTER);

        VBox vBoxPrincipal = new VBox();
        vBoxPrincipal.setAlignment(Pos.CENTER);
        vBoxPrincipal.setStyle("-fx-background-color: #3fe5d1;");
        vBoxPrincipal.getChildren().addAll(labelArriba, instrucciones, hBoxBotones);
        VBox.setVgrow(hBoxBotones, Priority.ALWAYS);
        return new Scene(vBoxPrincipal, 500, 300);
    }

    public void subscribe(ObserverRecargarEscena observerRecargarEscena) {
        this.observerRecargarEscena = observerRecargarEscena;
    }
}


