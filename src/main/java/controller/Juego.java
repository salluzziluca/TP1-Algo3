package controller;

import model.*;
import view.BuilderEscenaInicio;
import view.BuilderEscenaTablero;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Juego {
    Stage escenarioPrincipal;
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;

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

    public void jugarse( ){
        Scene escenaInicio1 = builderEscenaInicio.crearEscena();
        escenarioPrincipal.setScene(escenaInicio1);
        escenarioPrincipal.show();
        if (jugadores.size() == 2) {
            builderEscenaTablero = new BuilderEscenaTablero();
            Scene escenaTablero = builderEscenaTablero.crearEscenaTablero(jugadores.get(0), jugadores.get(1));
            escenarioPrincipal.setScene(escenaTablero);
            escenarioPrincipal.show();
        }

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
