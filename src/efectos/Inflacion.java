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
        if (jugadorAliado.buscarEfecto("Catalizador")) {
            this.duracion *= 2;
            jugadorAliado.quitarEfecto("Catalizador");
        }
        if (jugadorEnemigo.buscarEfecto(this.getNombre())) {
            jugadorEnemigo.agregarDuracionAEfecto(getNombre(), duracion);

        } else {
            jugadorEnemigo.agregarEfecto(this);
            this.aplicarEfecto(jugadorEnemigo);
        }

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
        return "Inflacion";
    }

    @Override
    public void agregarDuracion(int duracion) {
        this.duracion += duracion;
    }

}
