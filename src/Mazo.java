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
        int ultimaPosicion = cartas.size() - 1;
        Carta carta = cartas.remove(ultimaPosicion);
        return carta;

    }

    public void barajar() {
        ArrayList<Carta> cartasBarajadas = new ArrayList<Carta>();
        int cantidadCartas = cartas.size();
        for (int i = 0; i < cantidadCartas; i++) {
            int posicionAleatoria = (int) (Math.random() * cartas.size());
            Carta carta = cartas.remove(posicionAleatoria);
            cartasBarajadas.add(carta);
        }
        cartas = cartasBarajadas;
    }
}