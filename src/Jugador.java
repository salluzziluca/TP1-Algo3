package src;

public class Jugador {
    private String nombre;
    private int vida;
    private Mano mano;
    private Mazo mazo;

    public Jugador(String nombre, int vida, Mano mano, Mazo mazo) {
        this.nombre = nombre;
        this.mano = mano;
        this.mazo = mazo;
    }

    public void robarCarta() {
        this.mano.agregarCarta(mazo);
    }

    public int getCantidadCartasEnMano() {
        return this.mano.getCantidadCartasEnMano();
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void jugarTurno(Jugador jugadorEnemigo) {
        this.robarCarta();
        this.mano.jugarCarta(jugadorEnemigo);
    }
}
