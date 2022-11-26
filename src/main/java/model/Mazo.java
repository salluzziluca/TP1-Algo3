package model;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    /*
     * Devuelve la primera carta del mazo y la elimina del mismo
     */
    public Carta darCarta() {
        int ultimaPosicion = cartas.size() - 1;
        return cartas.remove(ultimaPosicion);

    }

    /*
     * Mezcla las cartas del mazo aleatoreamente
     */
    public void mezclar() {
        ArrayList<Carta> cartasBarajadas = new ArrayList<>();
        int cantidadCartas = cartas.size();
        for (int i = 0; i < cantidadCartas; i++) {
            int posicionAleatoria = (int) (Math.random() * cartas.size());
            Carta carta = cartas.remove(posicionAleatoria);
            cartasBarajadas.add(carta);
        }
        cartas = cartasBarajadas;
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }
}