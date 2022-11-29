package model;

public interface Efecto {

    /*
     * Reduce la duracion y si llega a 0 se quita del array de efectos del jugador
     */
    void reducirDuracion(Jugador jugadorAliado);

    /*
     * Agrega el efecto al array del jugador correspondinte, si se tiene
     * catalizador, se duplica la duracion del efecto y si ya se tiene el efecto en
     * el array correspondiente, se suman las duraciones
     */
    default void setearEfectoDefault(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        Jugador jugadorQueRecibiraElEfecto = jugadorEnemigo;

        if (this.getSeLeAplicaAlAliado()) {
            jugadorQueRecibiraElEfecto = jugadorAliado;
        }
        if (jugadorQueRecibiraElEfecto.buscarEfecto(this.getNombre())) {
            jugadorQueRecibiraElEfecto.modificarDuracionAEfecto(getNombre(), getDuracion());
        } else {
            setSeDebeAplicarElEfecto(true);
            jugadorQueRecibiraElEfecto.agregarEfecto(this);
        }
    }

    void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    /*
     * Quita el efecto del array de efectos del jugador
     */
    default void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);
    }

    /*
     * Aplica el efecto en el jugador que se le pasa por parametro
     */
    void aplicarEfecto(Jugador jugador);

    /*
     * Aplica el efecto a la carta que se le pasa por parametro
     */
    void aplicarEfectoACarta(Carta carta);

    /*
     * Devuelve el nombre del efecto
     */
    String getNombre();

    String getDescripcion();

    /*
     * Modifica la duracion al efecto
     */
    void modificarDuracion(int duracionAñadida);

    void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto);

    int getDuracion();

    void setDuracion(int duracion);

    int getDuracionOriginal();

    boolean getSeLeAplicaAlAliado();
}
