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
    Stage escenarioPrincipal;
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;
    final int CANT_MANO_INICIAL = 3;

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


    /*
     * Recibe el string con el nombre y la elección de mazo hecha por el jugador.
     */
    public void recibirNombreYMazo(String nombre, String mazo) {
        Mazo mazoJugador = null;

        if(mazo.equals("Mazo Guerrero")){
            mazoJugador = builderMazos.crearMazoGuerrero();
        }
        else if(mazo.equals("Mazo Alquimista")){
            mazoJugador = builderMazos.crearMazoAlquimista();
        }
        Jugador jugadorActual = new Jugador(nombre, VIDA_INICIAL,MANA_INICIAL, new Mano(), mazoJugador);
        jugadores.add(jugadorActual);

        if (jugadores.size() == 2) {
            tablero = new Tablero(jugadores.get(0), jugadores.get(1));
            builderEscenaTablero.subscribe(this, tablero, this);
            for (int i = 0; i < CANT_MANO_INICIAL; i++) {
                jugadores.get(posicionJugadorActual).robarCarta();
                jugadores.get(posicionJugadorOponente).robarCarta();
            }
            Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(0), jugadores.get(1));
            escenarioPrincipal.setScene(escenaTablero);
            escenarioPrincipal.show();
        }else {
            Scene escenaInicio2 = builderEscenaInicio.crearEscena();
            escenarioPrincipal.setScene(escenaInicio2);
            escenarioPrincipal.show();

        }
    }
    public void empezarJuego( ){
        Scene escenaInicio1 = builderEscenaInicio.crearEscena();
        escenarioPrincipal.setScene(escenaInicio1);
        escenarioPrincipal.show();
    }

    public void pasarTurno() {
        //pasa turno y actualiza la interfaz


        jugadores.get(posicionJugadorActual).terminarTurno();
        jugadores.get(posicionJugadorActual).recargarMana();
        swapJugadores();
        jugadores.get(posicionJugadorActual).recorrerEfectos();

        for (Jugador jugador:jugadores) {
            if (!jugador.estaVivo()){
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setContentText(String.format("El jugador %s ha perdido",jugador.getNombre()));
                Optional<ButtonType> boton = alerta.showAndWait();
                if (boton.get() == ButtonType.OK) {
                    System.exit(0);
                } else {
                    System.exit(0);
                }
                alerta.show();
                System.exit(0);
            }
        }

        if(!jugadores.get(posicionJugadorActual).getMazo().estaVacio()){
            jugadores.get(posicionJugadorActual).robarCarta();
        }
       Scene escenaTablero =  builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

    }

    private void swapJugadores() {
        int aux = posicionJugadorActual;
        posicionJugadorActual = posicionJugadorOponente;
        posicionJugadorOponente = aux;
    }
    public void recargarEscena(){
        Scene escenaTablero =  builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();
    }
}
