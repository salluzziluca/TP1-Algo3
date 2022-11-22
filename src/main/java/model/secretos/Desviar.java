package model.secretos;

import model.Carta;
import model.Daño;
import model.Jugador;
import model.Secreto;

/*
 * Al instanciarse este secreto en el array de secreto de un jugador, la proxima vez que el jugador contrario juege una carta de daño, este recibirá el daño duplicado y se mitigará el daño hacia el jugador aliado.
 */
public class Desviar implements Secreto {
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

        jugadorEnemigo.recibirDaño(cantidadDedaño * 2);
        jugadorAliado.aumentarVida(cantidadDedaño); // para mitigar el daño que te hace la carta que te activa

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getNombre() {
        return "Desviar";
    }

}
