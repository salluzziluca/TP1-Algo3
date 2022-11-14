package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, el proximo efecto que inflija a su oponente o a el mismo se duplica.
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
    public void aplicarEfectoACarta(Carta carta) {
        return;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        // No hace nada
    }

    @Override
    public String getNombre() {
        return "Catalizador";
    }

    @Override
    public void modificarDuracion(int duracion) {
    }

    @Override
    public void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto) {

    }

    @Override
    public int getDuracion() {
        return 0;
    }

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return false;
    }
}
