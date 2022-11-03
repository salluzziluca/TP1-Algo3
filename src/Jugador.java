package src;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    int vida;
    int manaMaximo;
    int manaActual;
    Mano mano;
    private Mazo mazo;
    ArrayList<Efecto> efectos;

    public Jugador(String nombre, int vida, int mana, Mano mano, Mazo mazo) {
        this.nombre = nombre;
        this.vida = vida;
        this.manaMaximo = mana;
        this.manaActual = 0;
        this.mano = mano;
        this.mazo = mazo;
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

    /*
     * Juega un turno completo del jugador, recorriendo los efectos para aplicarlos,
     * robando
     * y jugando cartas y finalmente reduciendo la duracion de todos sus efectos
     * mediante
     * terminarTurno
     */
    public void jugarTurno(Jugador jugadorEnemigo) {
        manaActual = manaMaximo;
        this.recorrerEfectos();
        this.robarCarta();
        int posicionCarta = this.pedirPosicionCarta();
        this.mano.jugarCarta(posicionCarta, manaActual, this, jugadorEnemigo);
        this.terminarTurno();
    }

    /*
     * Aplica el daño recibido, modifica ese valor dependiendo de los efectos que el
     * jugador
     * tenga en ese momento aplicados
     */
    public void recibirDaño(int cantidad) {

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

    public void quitarEfecto(Efecto efecto) {
        this.efectos.remove(efecto);
    }

    /*
     * Recorre todos los efectos del jugador y utiliza su metodo AplicarEfecto para
     * que su efecto se aplique
     */
    public void recorrerEfectos() {
        for (Efecto efecto : this.efectos) {
            efecto.aplicarEfecto(this);
        }
    }

    /*
     * Reduce la duracion de todos los efectos del jugador
     */
    public void terminarTurno() {
        for (int i = 0; i < this.efectos.size(); i++) {
            Efecto efecto = this.efectos.get(i);
            efecto.reducirDuracion(this);

        }

    }

    /*
     * Comprueba si el efecto pasado por parametro esta en la lista de efectos del
     * jugador
     */
    private boolean buscarEfecto(String nombreEfecto) {
        for (Efecto efecto : efectos) {
            if (efecto.getNombre().equals(nombreEfecto)) {
                return true;
            }
        }
        return false;
    }

    /*
     * aumenta el ataque de todas las cartas en la mano
     */
    public void modificarAtaque(int cantidad) {
        mano.modificarAtaque(cantidad);
    }

    public void aumentarManaMaximo(int i) {
        this.manaMaximo += i;
        this.manaActual += i;
    }

    public void aumentarValorCartas() {
        this.mano.aumentarValorCartas();
    }

    public void modificarMana(int i) {
        this.manaActual += i;
    }
}
