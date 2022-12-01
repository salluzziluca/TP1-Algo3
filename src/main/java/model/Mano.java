package model;

import java.util.ArrayList;

public class Mano {
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private ArrayList<Carta> manoActual;

    public Mano() {
        this.manoActual = new ArrayList<>();
    }

    public int getCantMano() {
        return manoActual.size();
    }

    /*
     * Roba una carta del mazo y la agrega a la mano
     */
    public void agregarCarta(Mazo mazo, Jugador jugador) {
        Carta cartaRobada = mazo.darCarta();
        jugador.aplicarEfectosACarta(cartaRobada);
        this.manoActual.add(cartaRobada);
    }

    /*
     * Juega la carta en la posicion indicada por el jugador. La setea en el
     * tablero, resetea sus valores, la quita de la mano y la devuelve al mazo.
     * Luego, mezcla el mazo.
     */
    public void jugarCarta(int posicionCarta, Jugador jugadorAliado, Jugador jugadorEnemigo, ObserverSetCartaEnJuego observer) {
        Carta cartaJugada = this.manoActual.get(posicionCarta);
        this.manoActual.remove(posicionCarta);
        cartaJugada.alJugarse(jugadorAliado, jugadorEnemigo);
        observer.setCartaEnJuego(cartaJugada, jugadorAliado);
        cartaJugada.resetearValores();
        jugadorAliado.getMazo().agregarCarta(cartaJugada);
        jugadorAliado.getMazo().mezclar();
    }

    public void modificarAtaque(int cantidad) {
        for (Carta carta : this.manoActual) {
            carta.modificarAtaque(cantidad);
        }
    }

    public void modificarValorCartas(int sumaCosto) {
        for (Carta carta : this.manoActual) {
            carta.modificarValor(sumaCosto);
        }
    }

    public ArrayList<Carta> getCartas() {
        return this.manoActual;
    }
}
