package model;

import java.util.ArrayList;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class Jugador {
    private final String nombre;
    private int vida;
    private int manaMaximo;
    private int manaActual;
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private Mano mano;
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private Mazo mazo;
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private ArrayList<Efecto> efectos;
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private ArrayList<Secreto> secretos;

    public Jugador(String nombre, int vida, int mana, Mano mano, Mazo mazo) {
        this.nombre = nombre;
        this.vida = vida;
        this.manaMaximo = mana;
        this.manaActual = manaMaximo;
        this.mano = mano;
        this.mazo = mazo;
        this.efectos = new ArrayList<>();
        this.secretos = new ArrayList<>();
    }

    public int getVida() {
        return vida;
    }

    public Mano getMano() {
        return mano;
    }

    public ArrayList<Efecto> getEfectos() {
        return efectos;
    }

    public ArrayList<Secreto> getSecretos() {
        return secretos;
    }

    public int getManaMaximo() {
        return manaMaximo;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public int getCantidadCartasEnMano() {
        return mano.getCantMano();
    }

    public void robarCarta() {
        if (mazo.getCartas().size() > 0) {
            this.mano.agregarCarta(mazo, this);
        }
    }

    public void robarCarta(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            this.robarCarta();
        }
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }

    /*
     * Aplica el daño recibido, modifica ese valor dependiendo de los efectos que
     * el jugador tenga en ese momento aplicados.
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

    public void modificarDuracionAEfecto(String nombreEfecto, int duracion) {
        this.efectos.get(buscarIndexEfecto(nombreEfecto)).modificarDuracion(duracion);
    }

    public void recorrerEfectos() {
        for (Efecto Efecto : this.efectos) {
            Efecto.aplicarEfecto(this);
        }
    }

    /*
     * Aplica los efectos a la carta pasada por parametro
     */
    public void aplicarEfectosACarta(Carta cartaRobada) {
        for (Efecto efecto : this.efectos) {
            efecto.aplicarEfectoACarta(cartaRobada);
        }
    }

    /*
     * Comprueba si el efecto pasado por parametro está en la lista de efectos del
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

    /*
     * Si el efecto pasado por parametro está en la lista de efectos del
     * jugador lo retorna
     */
    public Efecto getEfecto(String nombreEfecto) {
        for (Efecto efecto : efectos) {
            if (efecto.getNombre().equals(nombreEfecto)) {
                return efecto;
            }
        }
        return null;
    }

    /*
     * Busca el index del efecto pasado por parametro en la lista de efectos del
     * jugador
     */
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
    }

    public void aumentarValorCartas() {
        this.mano.aumentarValorCartas();
    }

    public void modificarMana(int i) {
        this.manaActual += i;
    }

    public void agregarSecreto(Secreto secreto) {
        this.secretos.add(secreto);
    }

    public void quitarSecreto(Secreto secreto) {
        this.secretos.remove(secreto);
    }

    public void aumentarVida(int cantidad) {
        this.vida += cantidad;
    }

    public int getManaActual() {
        return this.manaActual;
    }

    /*
     * Le pasa la carta en juego a todos los secretos y estos se encargan de ver si
     * cumple las condiciones para que sean activados
     */
    public void update(Carta carta, Jugador jugadorEnemigo, Jugador jugador) {

        for (int i = 0; i < this.secretos.size(); i++) {
            Secreto secreto = this.secretos.get(i);
            secreto.comprobarSiSeCumple(carta, this, jugadorEnemigo, jugador);
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

    public String getNombre() {
        return this.nombre;
    }

    public void recargarMana() {
        this.manaActual = this.manaMaximo;
    }
}