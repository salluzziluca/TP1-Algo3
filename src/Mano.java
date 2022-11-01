package src;

import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> manoActual;
    private int cantCartasEnMano;

    public Mano() {
        this.manoActual = new ArrayList<Carta>();
        this.cantCartasEnMano = 0;
    }

    public int getCantidadCartasEnMano() {
        return cantCartasEnMano;
    }

    /*
     * Roba una carta del mazo y la agrega a la mano
     */
    public void agregarCarta(Mazo mazo) {
        Carta cartaRobada = mazo.darCarta();
        this.manoActual.add(cartaRobada);
        this.cantCartasEnMano++;
    }

    /*
     * Juega la carta en la posicion indicada por el jugador.
     * Luego, la quita de la mano.
     */
    public void jugarCarta(int posicionCarta, Jugador jugadorEnemigo) {
        Carta cartaJugada = this.manoActual.get(posicionCarta);
        cartaJugada.alJugarse(jugadorEnemigo);
        this.manoActual.remove(posicionCarta);
        this.cantCartasEnMano--;
    }
}
