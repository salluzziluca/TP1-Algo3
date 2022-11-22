package model.secretos;

import model.Carta;
import model.Jugador;
import model.Secreto;

/*
 * Al instanciarse este secreto en el array de secretos de un jugador, la proxima vez que tu oponente juegue una carta que te infligiera un efecto se limpia el tipo de efecto que te intentan aplicar.
 */
public class Imparable implements Secreto {

    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta.getEfecto() != null && jugador == jugadorEnemigo) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);
        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        jugadorAliado.quitarEfecto(carta.getEfecto());

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getNombre() {
        return "Imparable";
    }

}
