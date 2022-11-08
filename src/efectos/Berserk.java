package src.efectos;

import src.Efecto;
import src.Jugador;

public class Berserk implements Efecto {
    public int duracion;

    public Berserk(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
            jugadorAliado.aumentarManaMaximo(-1);
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        if (jugadorAliado.buscarEfecto("Catalizador")) {
            this.duracion *= 2;
            jugadorAliado.quitarEfecto("Catalizador");
        }
        if (jugadorAliado.buscarEfecto(this.getNombre())) {
            jugadorAliado.agregarDuracionAEfecto(getNombre(), duracion);
        } else {

            jugadorAliado.agregarEfecto(this);
            jugadorAliado.aumentarManaMaximo(1);
        }
    }

    @Override
    public void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);

    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        return;
    }

    @Override
    public String getNombre() {
        return "Berserk";
    }

    @Override
    public void agregarDuracion(int duracion) {
        this.duracion += duracion;
    }

}
