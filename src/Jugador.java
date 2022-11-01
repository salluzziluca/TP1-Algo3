package src;

public class Jugador {
    private String nombre;
    int vida;
    Mano mano;
    private Mazo mazo;

    public Jugador(String nombre, int vida, Mano mano, Mazo mazo) {
        this.nombre = nombre;
        this.mano = mano;
        this.mazo = mazo;
        this.vida = vida;
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

    private int pedirPosicionCarta() {
        System.out.println("Ingrese la posicion de la carta que desea jugar");
        int posicionCarta = Integer.parseInt(System.console().readLine());
        return posicionCarta;
    }

    public void jugarTurno(Jugador jugadorEnemigo) {
        this.robarCarta();
        int posicionCarta = this.pedirPosicionCarta();
        this.mano.jugarCarta(posicionCarta, jugadorEnemigo);
    }

    public void recibirDaño(int cantidad) {
        this.vida -= cantidad;
    }
}
