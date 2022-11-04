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
        jugador.aumentarManaMaximo(1);

    }

    @Override
    public String getNombre() {
        return "Berserk";
    }

}
