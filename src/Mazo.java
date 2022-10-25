package src;

import java.util.ArrayList;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<Carta>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public Carta darCarta() {
        int randomIndex = (int) (Math.random() * cartas.size());
        Carta carta = cartas.remove(randomIndex);
        return carta;

    }
}