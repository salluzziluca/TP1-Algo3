package src.efectos;

import src.Efecto;
import src.Jugador;

/*
 * El proximo efecto que inflijas a tu oponente o a vos mismo se duplica
 */
public class Catalizador implements Efecto {

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        return;
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
