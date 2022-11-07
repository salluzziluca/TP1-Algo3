package src.secretos;

import src.*;
import src.efectos.Inflacion;

public class Enormigus implements Secreto {

    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo) {
        if (carta != null) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);

        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        Inflacion inflacion = new Inflacion(carta.getCosto());
        inflacion.setearEfecto(jugadorAliado, jugadorEnemigo);

    }

}