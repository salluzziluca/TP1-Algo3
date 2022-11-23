package controller;

import model.*;
import view.InterfazInicio;

public class Controller {
    final int MANA_INICIAL = 3;
    final int VIDA_INICIAL = 15;
    Jugador jugador1;
    Jugador jugador2;
    BuilderMazos builder = new BuilderMazos();



    /*
     * Recibe el string con el nombre y la elección de mazo hecha por el jugador.
     */
    public void RecibirNombreYMazo(String nombre, String mazo) {
        Mazo mazoJugador = null;

        if(mazo.equals("Mazo Guerrero")){
            mazoJugador = builder.crearMazoGuerrero();
        }
        else if(mazo.equals("Mazo Alquimista")){
            mazoJugador = builder.crearMazoAlquimista();
        }
        Jugador jugadorActual = new Jugador(nombre, VIDA_INICIAL,MANA_INICIAL, new Mano(), mazoJugador);

        if(jugador1 == null) jugador1 = jugadorActual;
        else if(jugador2 == null) jugador2 = jugadorActual;
        else {
            //arrancar el juego

        }
    }

    public void InicializarJuego(){
        InterfazInicio interfazInicio= new InterfazInicio(this);
        interfazInicio.start();
        interfazInicio.start();

    }

    public void jugarse(){
        //crear una escena
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
