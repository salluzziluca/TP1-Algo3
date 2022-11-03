package src;

public class Veneno implements Efecto {
    private int duracion;

    public Veneno(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorEnemigo.agregarEfecto(this);
    }

    @Override
    public void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.recibirDaño(duracion);
    }

    @Override
    public String getNombre() {
        return "Veneno";
    }

}
