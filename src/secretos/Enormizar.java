package src.secretos;

import src.*;

public class Enormizar implements Secreto {
    private Jugador jugador;

    public Enormizar(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setearSecreto() {
        this.jugador.agregarSecreto(this);
    }

    public void alSerRevelado() {

    }
}
