package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.*;
import view.BuilderEscenaInicio;
import view.BuilderEscenaInstrucciones;
import view.BuilderEscenaTablero;

import java.util.ArrayList;
import java.util.Optional;

public class Juego implements ObserverRecibirNombreYMazo, ObserverPasarTurno, ObserverRecargarEscena, ObserverFinJuego {
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;
    final int CANT_MANO_INICIAL = 3;
    final Stage escenarioPrincipal;
    final ArrayList<Jugador> jugadores = new ArrayList<>();
    final BuilderEscenaInicio builderEscenaInicio;
    final BuilderEscenaTablero builderEscenaTablero;
    final BuilderEscenaInstrucciones builderEscenaInstrucciones;
    final BuilderMazos builderMazos;
    int posicionJugadorActual;
    int posicionJugadorOponente;
    Tablero tablero;


    public Juego(Stage escenarioPrincipal, BuilderEscenaInicio builderEscenaInicio, BuilderEscenaTablero builderescenaTablero, BuilderMazos builderMazos, BuilderEscenaInstrucciones builderEscenaInstrucciones) {
        this.builderEscenaInstrucciones = builderEscenaInstrucciones;
        this.builderEscenaTablero = builderescenaTablero;
        this.builderEscenaInicio = builderEscenaInicio;
        this.escenarioPrincipal = escenarioPrincipal;
        this.builderMazos = builderMazos;
        posicionJugadorActual = 0;
        posicionJugadorOponente = 1;
        builderEscenaInicio.subscribe(this, this);
        builderEscenaInstrucciones.subscribe(this);

    }

    public void alertaFinJuego(Jugador jugador) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(String.format("El jugador %s ha perdido", jugador.getNombre()));
        Optional<ButtonType> boton = alerta.showAndWait();
        if (boton.isPresent() && boton.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            System.exit(0);
        }
        alerta.show();
    }

    /*
     * Recibe el string con el nombre y la elección de mazo hecha por el jugador.
     */
    public void recibirNombreYMazo(String nombre1, String mazo1, String nombre2, String mazo2) {

        crearJugador(nombre1, mazo1);
        crearJugador(nombre2, mazo2);

        tablero = new Tablero(jugadores.get(0), jugadores.get(1));
        builderEscenaTablero.subscribe(this, tablero, this, this);

        jugadores.get(posicionJugadorActual).robarCarta(CANT_MANO_INICIAL);
        jugadores.get(posicionJugadorOponente).robarCarta(CANT_MANO_INICIAL);


        Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(0), jugadores.get(1));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();
    }

    private void crearJugador(String nombre, String mazo) {
        Mazo mazoJugador = crearMazo(mazo);

        Jugador jugadorActual = new Jugador(nombre, VIDA_INICIAL, MANA_INICIAL, new Mano(), mazoJugador);
        jugadores.add(jugadorActual);
    }

    private Mazo crearMazo(String mazo) {
        return switch (mazo) {
            case "Mazo Guerrero" -> builderMazos.crearMazoGuerrero();
            case "Mazo Alquimista" -> builderMazos.crearMazoAlquimista();
            default -> null;
        };
    }

    public void empezarJuego() {
        escenarioPrincipal.setTitle("Spizz");
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.setScene(builderEscenaInicio.crearEscena());
        escenarioPrincipal.show();
    }

    public void pasarTurno() {
        //pasa turno y actualiza la interfaz

        jugadores.get(posicionJugadorActual).terminarTurno();
        jugadores.get(posicionJugadorActual).recargarMana();

        swapJugadores();
        jugadores.get(posicionJugadorActual).recorrerEfectos();

        jugadores.get(posicionJugadorActual).robarCarta();

        Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

        for (Jugador jugador : jugadores) {
            if (jugador.estaMuerto()) {
                alertaFinJuego(jugador);
            }
        }

    }

    private void swapJugadores() {
        int aux = posicionJugadorActual;
        posicionJugadorActual = posicionJugadorOponente;
        posicionJugadorOponente = aux;
    }

    public void recargarEscenaTablero() {
        Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();
    }

    public void crearEscenaInstrucciones(Scene escenaPrevia) {
        Scene escenaInstrucciones = builderEscenaInstrucciones.crearEscenaInstrucciones(escenaPrevia);
        escenarioPrincipal.setScene(escenaInstrucciones);
        escenarioPrincipal.show();
    }

    public void recargarEscenaAnterior(Scene escenaAnterior) {
        escenarioPrincipal.setScene(escenaAnterior);
        escenarioPrincipal.show();
    }
}
