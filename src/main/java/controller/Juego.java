package controller;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.*;
import view.BuilderEscenaInicio;
import view.BuilderEscenaTablero;

import java.util.ArrayList;
import java.util.Optional;

public class Juego implements ObserverRecibirNombreYMazo, ObserverPasarTurno, ObserverRecargarEscena {
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;
    final int CANT_MANO_INICIAL = 3;
    Stage escenarioPrincipal;
    int posicionJugadorActual;
    int posicionJugadorOponente;

    ArrayList<Jugador> jugadores = new ArrayList<>();
    BuilderEscenaInicio builderEscenaInicio;
    BuilderEscenaTablero builderEscenaTablero;
    BuilderMazos builderMazos;
    Tablero tablero;


    public Juego(Stage escenarioPrincipal, BuilderEscenaInicio builderEscenaInicio, BuilderEscenaTablero builderescenaTablero, BuilderMazos builderMazos) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.builderEscenaInicio = builderEscenaInicio;
        this.builderEscenaTablero = builderescenaTablero;
        this.builderMazos = builderMazos;
        posicionJugadorActual = 0;
        posicionJugadorOponente = 1;

    }

    public static void alertaFinJuego(Jugador jugador) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(String.format("El jugador %s ha perdido", jugador.getNombre()));
        Optional<ButtonType> boton = alerta.showAndWait();
        if (boton.get() == ButtonType.OK) {
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
        builderEscenaTablero.subscribe(this, tablero, this);

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
        escenarioPrincipal.setTitle("Juego cartas nombre pendiente MK1"); // TODO Nombre
        escenarioPrincipal.setScene(builderEscenaInicio.crearEscena());
        escenarioPrincipal.show();
    }

    public void pasarTurno() {
        //pasa turno y actualiza la interfaz

        jugadores.get(posicionJugadorActual).terminarTurno();
        jugadores.get(posicionJugadorActual).recargarMana();
        swapJugadores();
        jugadores.get(posicionJugadorActual).recorrerEfectos();

        for (Jugador jugador : jugadores) {
            if (!jugador.estaVivo()) {
                alertaFinJuego(jugador);
                System.exit(0);
            }
        }

        jugadores.get(posicionJugadorActual).robarCarta();

        Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

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
}
