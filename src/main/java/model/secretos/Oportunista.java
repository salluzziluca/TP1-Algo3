package model.secretos;

import model.Carta;
import model.Jugador;
import model.Secreto;

/*
 * La proxima vez que juegues una carta de daño, robas 3 cartas.
 */
public class Oportunista implements Secreto {
    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta != null && jugador == jugadorAliado) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);
        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        jugadorAliado.robarCarta(3);

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getNombre() {
        return "Oportunista";
    }

    @Override
    public String getDescripcion() {
        return "La proxima vez que juegues una carta de daño, robas 3.";
    }

}
