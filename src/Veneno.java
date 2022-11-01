package src;

public class Veneno implements Efecto {
    private int duracion;

    public Veneno(int duracion) {
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
        jugador.recibirDaño(duracion);
    }

}
