package model.secretos;

import model.Carta;
import model.Jugador;
import model.Secreto;
import model.efectos.Inflacion;

/*
 * Al instanciarse este secreto en el array de secretos de un jugador, la proxima vez que su oponente juegue una carta Inflije Inflacion con duracion igual al coste de la carta jugada.
 */
public class Enormigus implements Secreto {

    @Override
    public void setearSecreto(Jugador jugadorAliado) {
        jugadorAliado.agregarSecreto(this);
    }

    @Override
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador) {
        if (carta != null && jugador == jugadorEnemigo) {
            alSerRevelado(jugadorAliado, jugadorEnemigo, carta);

        }
    }

    @Override
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta) {
        int costoCarta = carta.getCosto();
        if (costoCarta <= 0) {
            return;
        }
        Inflacion inflacion = new Inflacion(costoCarta);
        inflacion.setearEfecto(jugadorAliado, jugadorEnemigo);

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getDescripcion() {
        return "La proxima vez que tu oponente juegue una carta Inflije Inflacion con duracion igual al coste de la carta jugada.";
    }

    @Override
    public String getNombre() {
        return "Enormigus";
    }
}