package view;

import controller.ObserverRecargarEscena;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class BuilderEscenaInstrucciones {

    ObserverRecargarEscena observerRecargarEscena;

    public Scene crearEscenaInstrucciones(Scene escenaPrevia) {

        Label labelArriba = new Label("Instrucciones");
        labelArriba.setFont(Font.font("verdana", FontWeight.BOLD, 18));

        Label instrucciones = new Label("El objetivo del juego es reducir la vida de tu oponente a 0. Para eso utilizaras cartas que, pricipalmente, hagan daño a tu oponente, apliquen un efecto a un jugador o pongan un secreto que se activará cuando se den las condiciones.");
        instrucciones.setFont(Font.font("verdana", FontPosture.REGULAR, 12));
        instrucciones.setWrapText(true);
        instrucciones.setPadding(new Insets(5));

        TableView<Pair<String, String>> tablaTutorial = crearTabla(crearParesTutorial(), "Notacion", "Significado", 250);
        TableView<Pair<String, String>> tablaEfectos = crearTabla(crearParesEfectos(), "Efecto", "Significado", 250);

        HBox hBoxTablas = new HBox(tablaTutorial, tablaEfectos);
        hBoxTablas.setAlignment(Pos.CENTER);

        Label tituloMazos = new Label("Mazos disponibles");
        tituloMazos.setFont(Font.font("verdana", FontWeight.BOLD, 14));
        TableView<Pair<String, String>> tablaMazoGuerrero = crearTabla(crearCartasGuerrero(), "Carta", "Descripcion", 335);
        TableView<Pair<String, String>> tablaMazoAlquimista = crearTabla(crearCartasAlqimista(), "Carta", "Descripcion", 335);

        HBox hBoxTablasMazos = new HBox(tablaMazoGuerrero, tablaMazoAlquimista);
        hBoxTablasMazos.setAlignment(Pos.CENTER);

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));

        Button botonVolver = new Button("Volver");
        botonVolver.setOnAction(e -> observerRecargarEscena.recargarEscenaAnterior(escenaPrevia));

        HBox hBoxBotones = new HBox(botonSalir, botonVolver);
        hBoxBotones.setAlignment(Pos.BOTTOM_CENTER);

        VBox vBoxPrincipal = new VBox();
        vBoxPrincipal.setAlignment(Pos.CENTER);
        vBoxPrincipal.setStyle("-fx-background-color: #3fe5d1;");
        vBoxPrincipal.getChildren().addAll(labelArriba, instrucciones, hBoxTablas, tituloMazos, hBoxTablasMazos, hBoxBotones);
        VBox.setVgrow(hBoxBotones, Priority.ALWAYS);
        return new Scene(vBoxPrincipal, 1000, 750);
    }

    private ObservableList<Pair<String, String>> crearParesEfectos() {
        ObservableList<Pair<String, String>> paresEfectos = FXCollections.observableArrayList();
        paresEfectos.add(new Pair<>("Vulnerable", "Se duplica el daño recibido"));
        paresEfectos.add(new Pair<>("Afilado", "Las cartas de Daño tienen +1 de daño"));
        paresEfectos.add(new Pair<>("Berserk", "+1 de mana máximo"));
        paresEfectos.add(new Pair<>("Veneno", "Al inicio del turno se recibe daño equivalente a la duracion del efecto"));
        paresEfectos.add(new Pair<>("Inflacion", "Aumenta 1 el costo de todas las cartas en la mano"));
        return paresEfectos;
    }

    private ObservableList<Pair<String, String>> crearParesTutorial() {
        ObservableList<Pair<String, String>> paresEfectos = FXCollections.observableArrayList();
        paresEfectos.add(new Pair<>("nombre_carta(x)", "La carta cuesta x mana"));
        paresEfectos.add(new Pair<>("Daño x", "Al jugarse el enemigo pierde x de vida"));
        paresEfectos.add(new Pair<>("Cura x", "Al jugarse ganas x de vida"));
        paresEfectos.add(new Pair<>("Inflije x efecto", "Aplica un efecto al jugador que usa la carta por x turnos"));
        paresEfectos.add(new Pair<>("Mejora x efecto", "Aplica un efecto al jugador que usa la carta por x turnos"));
        paresEfectos.add(new Pair<>("Secreto", "Cuando se cumple la condicion se activa el efecto de la carta"));
        return paresEfectos;
    }

    private ObservableList<Pair<String, String>> crearCartasGuerrero() {
        ObservableList<Pair<String, String>> paresEfectos = FXCollections.observableArrayList();
        paresEfectos.add(new Pair<>("2x Réplica (1)", "Daño 1; Robar 1"));
        paresEfectos.add(new Pair<>("2x Golpe (1)", "Daño 3"));
        paresEfectos.add(new Pair<>("Triturar (2)", "Daño 2; Inflige 2 Vulnerable"));
        paresEfectos.add(new Pair<>("Afilar (2)", "Mejora 3 Afilado"));
        paresEfectos.add(new Pair<>("Ira (3)", "Mejora permanente Berserk"));
        paresEfectos.add(new Pair<>("Imparable (1)", "Secreto: La proxima vez que tu oponente juegue una carta que te infligiera un efecto no te lo inflije y se remueve ese efecto si ya lo tenias"));
        paresEfectos.add(new Pair<>("Oportunista (1)", "Secreto: La proxima vez que juegues una carta de daño, robas 3"));
        paresEfectos.add(new Pair<>("Desviar (2)", "Secreto: La proxima vez que tu oponente juegue una carta de daño tu oponente recibirá el daño duplicado."));
        return paresEfectos;
    }

    private ObservableList<Pair<String, String>> crearCartasAlqimista() {
        ObservableList<Pair<String, String>> paresEfectos = FXCollections.observableArrayList();
        paresEfectos.add(new Pair<>("Combo (0)", "Daño 1"));
        paresEfectos.add(new Pair<>("x2 Dardo Tóxico (1)", "Daño 1; Inflige 1 Veneno"));
        paresEfectos.add(new Pair<>("x2 Frasco de Toxinas (2)", "Inflige 2 Veneno"));
        paresEfectos.add(new Pair<>("Poción de Agilidad (1)", "Roba 2 cartas"));
        paresEfectos.add(new Pair<>("Catalizador (0)", "Secreto: Tu proximo efecto se duplica en duracion"));
        paresEfectos.add(new Pair<>("Enormigus (2)", "Secreto: La proxima vez que tu oponente juegue una carta Inflije Inflacion con duracion igual al coste de la carta jugada"));
        paresEfectos.add(new Pair<>("Prevenir y curar (2)", "Secreto: La proxima vez que tu oponente juegue una carta de daño, en vez de dañarte te cura y robas 1 carta"));
        paresEfectos.add(new Pair<>("Trampa Venenosa (2)", "Secreto: La proxima vez que tu oponente juegue una carta de Daño, le aplicas Veneno igual al daño de la carta"));
        return paresEfectos;
    }


    private TableView<Pair<String, String>> crearTabla(ObservableList<Pair<String, String>> observableList, String tituloColumna1, String tituloColumna2, int maxHeight) {
        TableColumn<Pair<String, String>, String> columna1 = new TableColumn<>(tituloColumna1);
        columna1.setCellValueFactory(new PropertyValueFactory<>("key"));


        TableColumn<Pair<String, String>, String> columna2 = new TableColumn<>(tituloColumna2);
        columna2.setPrefWidth(320);
        columna2.setCellValueFactory(new PropertyValueFactory<>("value"));
        columna2.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    Text text = new Text(item);
                    text.setStyle("-fx-text-alignment:justify;");
                    text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                    setGraphic(text);
                }
            }
        });


        TableView<Pair<String, String>> tabla = new TableView<>();
        tabla.setMaxHeight(maxHeight);
        tabla.setItems(observableList);
        tabla.getColumns().addAll(columna1, columna2);

        return tabla;
    }

    public void subscribe(ObserverRecargarEscena observerRecargarEscena) {
        this.observerRecargarEscena = observerRecargarEscena;
    }
}


