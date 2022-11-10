package src.secretos;

import src.*;

/*
 * Al instanciarse este secreto en el array de secretos de un jugador, La proxima vez que su oponente juegue una carta de daño, en vez de dañarlo lo cura y roba 1 carta
 */
public class PrevenirYCurar implements Secreto {
    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta.getDaño() != null && jugador == jugadorEnemigo) {
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

    @Override
    public String getNombre() {
        return "Prevenir y Curar";
    }
}
