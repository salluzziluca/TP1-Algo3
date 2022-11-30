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

        TableView<Pair<String,String>> tablaEfectos = crearTabla(crearParesEfectos(),"Efecto","Significado");
        TableView<Pair<String,String>> tablaEfectos2 = crearTabla(crearParesEfectos(),"Efecto","Significado");
        HBox hBoxTablas= new HBox(tablaEfectos,tablaEfectos2);
        hBoxTablas.setAlignment(Pos.CENTER);

        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> System.exit(0));

        Button botonVolver = new Button("Volver");
        botonVolver.setOnAction(e -> observerRecargarEscena.recargarEscenaAnterior(escenaPrevia));

        HBox hBoxBotones = new HBox(botonSalir, botonVolver);
        hBoxBotones.setAlignment(Pos.BOTTOM_CENTER);

        VBox vBoxPrincipal = new VBox();
        vBoxPrincipal.setAlignment(Pos.CENTER);
        vBoxPrincipal.setStyle("-fx-background-color: #3fe5d1;");
        vBoxPrincipal.getChildren().addAll(labelArriba, instrucciones,hBoxTablas, hBoxBotones);
        VBox.setVgrow(hBoxBotones, Priority.ALWAYS);
        return new Scene(vBoxPrincipal, 1000, 500);
    }

    private ObservableList<Pair<String,String>> crearParesEfectos(){
        ObservableList<Pair<String,String>> paresEfectos = FXCollections.observableArrayList();
        paresEfectos.add(new Pair<>("Vulnerable","Se duplica el daño recibido"));
        paresEfectos.add(new Pair<>("Afilado","Las cartas de Daño tienen +1 de daño"));
        paresEfectos.add(new Pair<>("Berserk","+1 de mana máximo"));
        paresEfectos.add(new Pair<>("Veneno","Al inicio del turno se recibe daño equivalente a la duracion del efecto"));
        paresEfectos.add(new Pair<>("Inflacion","Aumenta x el costo de todas las cartas en mano"));
        return paresEfectos;
    }

    private TableView<Pair<String,String>> crearTabla(ObservableList<Pair<String,String>> observableList,String tituloColumna1,String tituloColumna2){
        TableColumn<Pair<String,String>,String> columna1= new TableColumn<>(tituloColumna1);
        columna1.setCellValueFactory(new PropertyValueFactory<>("key"));



        TableColumn<Pair<String,String>,String> columna2= new TableColumn<>(tituloColumna2);
        columna2.setPrefWidth(200);
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


        TableView<Pair<String,String>> tabla = new TableView<>();
        tabla.setItems(observableList);
        tabla.getColumns().addAll(columna1,columna2);

        return tabla;
    }

    public void subscribe(ObserverRecargarEscena observerRecargarEscena) {
        this.observerRecargarEscena = observerRecargarEscena;
    }
}


