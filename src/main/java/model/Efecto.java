package model;

public interface Efecto {

    /*
     * Reduce la duracion y si llega a 0 se quita del array de efectos del jugador
     */
    public void reducirDuracion(Jugador jugadorAliado);

    public void setDuracion(int duracion);

    /*
     * Agrega el efecto al array del jugador correspondinte, si se tiene
     * catalizador, se duplica la duracion del efecto y si ya se tiene el efecto en
     * el array correspondiente, se suman las duraciones
     */
    default public void setearEfectoDefault(Jugador jugadorAliado, Jugador jugadorEnemigo) {
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

    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    /*
     * Quita el efecto del array de efectos del jugador
     */
    default public void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);
    }

    /*
     * Aplica el efecto en el jugador que se le pasa por parametro
     */
    public void aplicarEfecto(Jugador jugador);

    /*
     * Aplica el efecto a la carta que se le pasa por parametro
     */
    public void aplicarEfectoACarta(Carta carta);

    /*
     * Devuelve el nombre del efecto
     */
    public String getNombre();

    /*
     * Modifica la duracion al efecto
     */
    public void modificarDuracion(int duracion);

    public void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto);

    public int getDuracion();

    public int getDuracionOriginal();

    public boolean getSeLeAplicaAlAliado();
}
