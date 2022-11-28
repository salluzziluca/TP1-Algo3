package model;

public interface Secreto {
    /*
     * Agrega el secreto al array de secretos del jugador correspondiente
     */
    void setearSecreto(Jugador jugadorAliado);

    default void quitarSecreto(Jugador jugador) {
        jugador.quitarSecreto(this);
    }

    /*
     * Comprueba si se cumple la condicion para revelar el secreto
     */
    void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador);

    /*
     * Revela el secreto y lo aplica
     */
    void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta);

    /*
     * Devuelve el nombre del secreto
     */
    String getNombre();

    String getDescripcion();
}
