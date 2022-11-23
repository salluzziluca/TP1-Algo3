package controller;

import model.*;
import view.InterfazInicio;

public class Controller {
    final int VIDA_INICIAL = 15;
    final int MANA_INICIAL = 0;
    BuilderMazos builder = new BuilderMazos();

    /*
     * Recibe el string con el nombre y la elección de mazo hecha por el jugador.
     */
    public Jugador RecibirNombreYMazo(String nombre, String mazo) {
        Mazo mazoJugador = null;
        if(mazo.equals("Mazo Guerrero")){
            mazoJugador = builder.crearMazoGuerrero();
        }
        else if(mazo.equals("Mazo Alquimista")){
            mazoJugador = builder.crearMazoAlquimista();
        }

        return new Jugador(nombre, VIDA_INICIAL, MANA_INICIAL, new Mano(), mazoJugador);
    }

    public void InicializarJuego(){
        InterfazInicio interfazInicio= new InterfazInicio();
        interfazInicio.start();
        interfazInicio.start();
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
