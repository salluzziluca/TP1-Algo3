package src;

public class Jugador {
    private String nombre;
    private Mano mano;
    private Mazo mazo;

    public Jugador(String nombre, Mano mano, Mazo mazo) {
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
}
