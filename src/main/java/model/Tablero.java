package model;

public class Tablero {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private Carta cartaEnJuego;
    private int turno; // TODO Por ahora no lo usamos

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.cartaEnJuego = null;
        this.turno = 0;
    }

    public void jugar() {
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            jugador1.jugarTurno(jugador2, this);
            jugador2.jugarTurno(jugador1, this);
            turno++;
        }
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
