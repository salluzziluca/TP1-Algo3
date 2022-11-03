package src;

public class Afilado implements Efecto {
    private int duracionActual;
    private int duracion;

    public Afilado(int duracion) {
        this.duracion = duracion;
        this.duracionActual = duracion;
    }

    /*
     * Disminuye al duracion del efecto, si es cero, lo quita del jugador y
     * adicionalmente resetea el ataque de las cartas del jugador para que no se
     * acumulen
     */
    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracionActual--;
        if (this.duracionActual == 0) {
            this.quitarEfecto(jugadorAliado);
        }
        jugadorAliado.modificarAtaque(-1);
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
        jugador.modificarAtaque(1);
    }

    @Override
    public String getNombre() {
        return "Afilado";
    }

}
