package model;

public class Tablero implements ObserverSetCartaEnJuego {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private Carta cartaEnJuego;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.cartaEnJuego = null;
    }

    /*
     * Setea la carta en juego y avisa a los jugadores que se ha jugado una carta.
     */
    public void setCartaEnJuego(Carta carta, Jugador jugador) {
        this.cartaEnJuego = carta;
        notificar(jugador);
    }

    public void notificar(Jugador jugador) {
        jugador1.update(cartaEnJuego, jugador2, jugador);
        jugador2.update(cartaEnJuego, jugador1, jugador);
    }
}
