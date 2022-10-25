package src;

import java.util.ArrayList;

public class Mano {
    private ArrayList<Carta> manoActual;
    private int cantCartasEnMano;

    public Mano() {
        this.manoActual = new ArrayList<Carta>();
        this.cantCartasEnMano = 0;
    }

    public int getCantidadCartasEnMano() {
        return cantCartasEnMano;
    }

    public void agregarCarta(Mazo mazo) {
        Carta cartaRobada = mazo.darCarta();
        this.manoActual.add(cartaRobada);
        this.cantCartasEnMano++;
    }
}
