package src.secretos;

import src.*;

public class PrevenirYCurar implements Secreto {
    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo) {
        if (carta.getDaño() != null) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);

        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        Daño daño = carta.getDaño();
        int cantidadDedaño = daño.getCantidad();
        jugadorAliado.aumentarVida(cantidadDedaño * 2);
        jugadorAliado.robarCarta();

    }
}
