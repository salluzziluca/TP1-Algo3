package model.secretos;

import model.Carta;
import model.Efecto;
import model.Jugador;
import model.Secreto;

public class Catalizador implements Secreto {
    @Override
    public void setearSecreto(Jugador jugadorAliado) {
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
        Jugador jugadorQueRecibeElEfecto = jugadorEnemigo;
        if (efectoActual.getSeLeAplicaAlAliado()) {
            jugadorQueRecibeElEfecto = jugadorAliado;
        }
        if (efectoActual.getDuracion() > 0) {
            Efecto efectoAplicado = jugadorQueRecibeElEfecto.getEfecto(efectoActual.getNombre());
            efectoAplicado.setDuracion(efectoAplicado.getDuracion() + efectoActual.getDuracionOriginal());
        }

        this.quitarSecreto(jugadorAliado);
    }

    @Override
    public String getDescripcion() {
        return "El proximo efecto que inflijas a tu oponente o a vos mismo se duplica.";
    }

    @Override
    public String getNombre() {
        return "Catalizador";
    }
}
