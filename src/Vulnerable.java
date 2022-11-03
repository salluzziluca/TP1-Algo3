package src;

public class Vulnerable implements Efecto {

    private int duracion;

    public Vulnerable(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public void reducirDuracion() {
        this.duracion--;
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorEnemigo.agregarEfecto(this);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        // no hace nada :) 
    }
}
