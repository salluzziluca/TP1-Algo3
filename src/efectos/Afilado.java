package src.efectos;

import src.Efecto;
import src.Jugador;

public class Afilado implements Efecto {
    private int duracion;

    public Afilado(int duracion) {
        this.duracion = duracion;
    }

    /*
     * Disminuye al duracion del efecto, si es cero, lo quita del jugador y
     * adicionalmente resetea el ataque de las cartas del jugador para que no se
     * acumulen
     */
    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
        }
        jugadorAliado.modificarAtaque(-1);
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        if (jugadorAliado.buscarEfecto("Catalizador")) {
            this.duracion *= 2;
            jugadorAliado.quitarEfecto("Catalizador");
        }
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
