package src;

public class Jugador {
    private String nombre;
    private Mano mano;
    private Mazo mazo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
        this.mazo = new Mazo();
    }

    public void robarCarta() {
        this.mano.agregarCarta(mazo);
    }
}
