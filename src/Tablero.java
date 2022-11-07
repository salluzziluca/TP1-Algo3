package src;

public class Tablero {
    private Jugador jugador1;
    private Jugador jugador2;
    private Carta cartaEnJuego;
    private int turno;

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

    public void setCartaEnJuego(Carta carta) {
        this.cartaEnJuego = carta;
        notificar();
    }

    public void notificar() {
        jugador1.update(cartaEnJuego, jugador2);
        jugador2.update(cartaEnJuego, jugador1);

    }
}
