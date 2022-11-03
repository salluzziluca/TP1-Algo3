package src;

import java.util.ArrayList;
import java.lang.*;

public class Jugador {
    private String nombre;
    int vida;
    Mano mano;
    private Mazo mazo;
    private ArrayList<Efecto> efectos;

    public Jugador(String nombre, int vida, Mano mano, Mazo mazo) {
        this.nombre = nombre;
        this.mano = mano;
        this.mazo = mazo;
        this.vida = vida;
        this.efectos = new ArrayList<Efecto>();
    }

    public void robarCarta() {
        this.mano.agregarCarta(mazo);
    }

    public int getCantidadCartasEnMano() {
        return this.mano.getCantidadCartasEnMano();
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    private int pedirPosicionCarta() {
        System.out.println("Ingrese la posicion de la carta que desea jugar");
        int posicionCarta = Integer.parseInt(System.console().readLine());
        return posicionCarta;
    }

    public void jugarTurno(Jugador jugadorEnemigo) {
        this.recorrerEfectos();
        this.robarCarta();
        int posicionCarta = this.pedirPosicionCarta();
        this.mano.jugarCarta(posicionCarta, this, jugadorEnemigo);
    }

    public void recibirDaño(int cantidad) {
        // see if the arraylist has any veneno

        if (buscarEfecto("Vulnerable")) {
            cantidad *= 2;
        }
        if (buscarEfecto("Afilado")) {
            cantidad += 1;
        }
        this.vida -= cantidad;

    }

    public void agregarEfecto(Efecto efecto) {
        this.efectos.add(efecto);
    }

    public void recorrerEfectos() {
        for (Efecto efecto : this.efectos) {
            efecto.aplicarEfecto(this);
        }
    }

    private boolean buscarEfecto(String nombreEfecto) {
        for (Efecto efecto : efectos) {
            if (efecto.getNombre().equals(nombreEfecto)) {
                return true;
            }
        }
        return false;
    }

    public void aumentarAtaque(int cantidad) {
        mano.aumentarAtaque(cantidad);
    }
}
