package src;

import java.util.ArrayList;

public class Jugador {
    int vida;
    int manaMaximo;
    int manaActual;
    Mano mano;
    Mazo mazo;
    ArrayList<Efecto> efectos;
    ArrayList<Secreto> secretos;
    private boolean pasarTurno;

    public Jugador(String nombre, int vida, int mana, Mano mano, Mazo mazo) {
        this.vida = vida;
        this.manaMaximo = mana;
        this.manaActual = 0;
        this.mano = mano;
        this.mazo = mazo;
        this.efectos = new ArrayList<Efecto>();
        this.secretos = new ArrayList<Secreto>();
        this.pasarTurno = false;
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
    public void jugarTurno(Jugador jugadorEnemigo, Tablero tableroActual) {
        pasarTurno = false;
        manaActual = manaMaximo;
        this.recorrerEfectos();
        this.robarCarta();
        while (!pasarTurno) {
            int posicionCarta = this.pedirPosicionCarta();
            this.mano.jugarCarta(posicionCarta, this, jugadorEnemigo, tableroActual);
        }
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
        this.vida -= cantidad;

    }

    public void agregarEfecto(Efecto efecto) {
        this.efectos.add(efecto);
    }

    public void quitarEfecto(Efecto efecto) {
        this.efectos.remove(efecto);
    }

    public void quitarEfecto(String nombreEfecto) {
        this.efectos.remove(buscarIndexEfecto(nombreEfecto));
    }

    public void agregarDuracionAEfecto(String nombreEfecto, int duracion) {
        this.efectos.get(buscarIndexEfecto(nombreEfecto)).agregarDuracion(duracion);
    }

    /*
     * Recorre todos los efectos del jugador y utiliza su metodo AplicarEfecto para
     * que su efecto se aplique
     * Si el efecto es "catalizador", almacena la posicion del efecto a catalizar
     * (el siguiente en aparecer)
     * Cuando la posicion almacenada y la actual coinciden, aplica el efecto dos
     * veces.
     * Luego, si el catalizador fue usado, lo elimina de la lista de efectos
     */
    void recorrerEfectos() {
        for (Efecto Efecto : this.efectos) {
            Efecto.aplicarEfecto(this);
        }

    }

    /*
     * Reduce la duracion de todos los efectos del jugador
     */
    void terminarTurno() {
        for (int i = 0; i < this.efectos.size(); i++) {
            Efecto efecto = this.efectos.get(i);
            efecto.reducirDuracion(this);
        }
    }

    /*
     * Comprueba si el efecto pasado por parametro esta en la lista de efectos del
     * jugador
     */
    public boolean buscarEfecto(String nombreEfecto) {
        for (Efecto efecto : efectos) {
            if (efecto.getNombre().equals(nombreEfecto)) {
                return true;
            }
        }
        return false;
    }

    public int buscarIndexEfecto(String nombreEfecto) {
        for (int i = 0; i < this.efectos.size(); i++) {
            Efecto efecto = this.efectos.get(i);
            if (efecto.getNombre().equals(nombreEfecto)) {
                return i;
            }
        }
        return -1;
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

    public void update(Carta carta, Jugador jugadorEnemigo) {
        String nombreEfecto = new String();
        for (Secreto secreto : secretos) {
            secreto.comprobarSiSeCumple(carta, this, jugadorEnemigo);
        }
        // Si la carta es de efecto Afilado o Inflacion, entonces ejecutá ese efecto
        if (carta.getEfecto() != null) {
            nombreEfecto = carta.getEfecto().getNombre();
        }
        if ((nombreEfecto == "Afilado" && buscarEfecto("Afilado"))
                || nombreEfecto == "Inflacion"
                        && buscarEfecto("Inflacion")
                || nombreEfecto == "Berserk" && buscarEfecto("Berserk")) {
            carta.efecto.aplicarEfecto(this);
        }

    }

    public void agregarSecreto(Secreto secreto) {
        this.secretos.add(secreto);
    }

    public void quitarSecreto(Secreto secreto) {
        this.secretos.remove(secreto);
    }

    public int getManaActual() {
        return this.manaActual;
    }

}
