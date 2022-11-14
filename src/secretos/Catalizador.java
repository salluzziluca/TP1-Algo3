package src.secretos;

import src.Carta;
import src.Efecto;
import src.Jugador;
import src.Secreto;

public class Catalizador implements Secreto {

    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta.getEfecto() != null && jugador == jugadorAliado) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);
        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        Efecto efectoActual = carta.getEfecto();
        if (efectoActual.getDuracion() > 0) {
            efectoActual.setDuracion(efectoActual.getDuracion() * 2);
        }

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getNombre() {
        return "Catalizador";
    }

}
