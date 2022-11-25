package controller;

import model.*;
import view.BuilderEscenaInicio;
import view.BuilderEscenaTablero;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Juego implements ObserverRecibirNombreYMazo, ObserverPasarTurno {
    Stage escenarioPrincipal;
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;

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
            Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(0), jugadores.get(1));

            escenarioPrincipal.setScene(escenaTablero);
            escenarioPrincipal.show();
            /*builderEscenaTablero.setTablero(tablero);
            builderEscenaTablero.setJuego(this);
            builderEscenaTablero.setJugadores(jugadores.get(0), jugadores.get(1));
            tablero.jugar();*/
        }else {
            Scene escenaInicio2 = builderEscenaInicio.crearEscena();
            escenarioPrincipal.setScene(escenaInicio2);
            escenarioPrincipal.show();

        }
    }

    public void update() {

    }

    public void empezarJuego( ){
        Jugador jugador1 = new Jugador("Jugador 1", VIDA_INICIAL,MANA_INICIAL, new Mano(), builderMazos.crearMazoGuerrero());
        Jugador jugador2 = new Jugador("Jugador 2", VIDA_INICIAL,MANA_INICIAL, new Mano(), builderMazos.crearMazoAlquimista());
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugador1.robarCarta();
        Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugador1, jugador2);
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

        jugador1.robarCarta();
        escenaTablero = builderEscenaTablero.crearEscenaTablero(jugador1, jugador2);
        Scene escenaInicio1 = builderEscenaInicio.crearEscena();
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

    }

    public void pasarTurno() {
        //pasa turno y actualiza la interfaz
        jugadores.get(posicionJugadorActual).terminarTurno();
        swapJugadores();
        jugadores.get(posicionJugadorActual).robarCarta();
       Scene escenaTablero =  builderEscenaTablero.crearEscenaTablero(jugadores.get(posicionJugadorActual), jugadores.get(posicionJugadorOponente));
        escenarioPrincipal.setScene(escenaTablero);
        escenarioPrincipal.show();

    }

    private void swapJugadores() {
        int aux = posicionJugadorActual;
        posicionJugadorActual = posicionJugadorOponente;
        posicionJugadorOponente = aux;
    }
    public void inicializarMazos(){
        BuilderMazos builder = new BuilderMazos();
        Mazo mazoGuerrero = builder.crearMazoGuerrero();
        Mazo MazoAlquimista = builder.crearMazoAlquimista();

        Jugador jugador1 = new Jugador("Jugador 1", VIDA_INICIAL, MANA_INICIAL, new Mano(), mazoGuerrero);
        Jugador jugador2 = new Jugador("Jugador 2", VIDA_INICIAL, MANA_INICIAL, new Mano(), MazoAlquimista);
        Tablero tablero = new Tablero(jugador1, jugador2);

        tablero.jugar();// TODO por ahora no se puede jugar ni en la consola
    }


}
