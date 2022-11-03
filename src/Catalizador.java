package src;

public class Catalizador implements Efecto {
    private int duracion;

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarEfecto(this);
    }

    @Override
    public void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        // No hace nada
    }

    @Override
    public String getNombre() {
        return "Catalizador";
    }

}
