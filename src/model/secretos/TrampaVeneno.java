package src.model.secretos;

import src.model.efectos.Veneno;
import src.model.Carta;
import src.model.Jugador;
import src.model.Secreto;

/*
 * Al instanciarse este secreto en el array de secretos de un jugador, La proxima vez que su oponente juegue una carta de Daño, le aplica Veneno igual al daño de la carta.
 */
public class TrampaVeneno implements Secreto {

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
        Veneno veneno = new Veneno(carta.getDaño().getCantidad());
        veneno.setearEfecto(jugadorAliado, jugadorEnemigo);

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getNombre() {
        return "TrampaVeneno";
    }
}