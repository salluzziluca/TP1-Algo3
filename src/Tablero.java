package src;

public class Tablero {
    public Jugador jugador1;
    public Jugador jugador2;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void jugar() {
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            jugador1.jugarTurno(jugador2);
            jugador2.jugarTurno(jugador1);
        }
    }
}
