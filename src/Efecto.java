package src;

public interface Efecto {
    public int duracion = 0;

    /*
     * Reduce la duracion y si llega a 0 se quita del array de efectos del jugador
     */
    public void reducirDuracion(Jugador jugadorAliado);

    /*
     * Agrega el efecto al array del jugador correspondinte, si se tiene
     * catalizador, se duplica la duracion del efecto y si ya se tiene el efecto en
     * el array correspondiente, se suman las duraciones
     */
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    /*
     * Quita el efecto del array de efectos del jugador
     */
    public void quitarEfecto(Jugador jugadorAliado);

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
}
