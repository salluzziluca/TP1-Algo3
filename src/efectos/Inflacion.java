package src.efectos;

import src.Efecto;
import src.Jugador;

public class Inflacion implements Efecto {

    private int duracion;

    public Inflacion(int duracion) {
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
        jugador.aumentarValorCartas();
    }

    @Override
    public String getNombre() {
        return "Inflación";
    }

}
