package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, Aumenta 1 el costo de todas las cartas en mano
 */
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
            jugadorEnemigo.modificarDuracionAEfecto(getNombre(), duracion);

        } else {
            jugadorEnemigo.agregarEfecto(this);
            jugadorEnemigo.aumentarValorCartas();
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
    public void aplicarEfectoACarta(Carta carta) {
        carta.aumentarValor();
    }

    @Override
    public String getNombre() {
        return "Inflacion";
    }

    @Override
    public void modificarDuracion(int duracion) {
        this.duracion += duracion;
    }

}
