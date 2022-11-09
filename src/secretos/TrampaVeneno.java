package src.secretos;

import src.*;
import src.efectos.Veneno;

public class TrampaVeneno implements Secreto {

    @Override
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta.daño != null && jugador == jugadorEnemigo) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);
        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        Veneno veneno = new Veneno(carta.getDaño().getCantidad());
        veneno.setearEfecto(jugadorAliado, jugadorEnemigo);

    }

    @Override
    public String getNombre() {
        return "TrampaVeneno";
    }
}