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
    public void agregarCarta(Mazo mazo, Jugador jugador) {
        Carta cartaRobada = mazo.darCarta();
        jugador.aplicarEfectosACarta(cartaRobada);
        this.manoActual.add(cartaRobada);
        this.cantCartasEnMano++;
    }

    /*
     * Juega la carta en la posicion indicada por el jugador. La setea en el
     * tablero, resetea sus valores, la quita de la mano y la devuelve al mazo.
     * Luego, mezcla el mazo.
     */
    public void jugarCarta(int posicionCarta, Jugador jugadorAliado, Jugador jugadorEnemigo, Tablero tableroActual) {
        Carta cartaJugada = this.manoActual.get(posicionCarta);
        this.manoActual.remove(posicionCarta);
        cartaJugada.alJugarse(jugadorAliado, jugadorEnemigo);
        tableroActual.setCartaEnJuego(cartaJugada, jugadorAliado);
        cartaJugada.resetearValores();
        jugadorAliado.mazo.agregarCarta(cartaJugada);
        jugadorAliado.mazo.mezclar();
        this.cantCartasEnMano--;
    }

    public void modificarAtaque(int cantidad) {
        for (Carta carta : this.manoActual) {
            carta.modificarAtaque(cantidad);
        }
    }

    public void aumentarValorCartas() {
        for (Carta carta : this.manoActual) {
            carta.aumentarValor();
        }
    }
}
