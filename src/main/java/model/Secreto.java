package model;

public interface Secreto {
    /*
     * Agrega el secreto al array de secretos del jugador correspondiente
     */
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    default public void quitarSecreto(Jugador jugador) {
        jugador.quitarSecreto(this);
    }

    /*
     * Comprueba si se cumple la condicion para revelar el secreto
     */
    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador);

    /*
     * Revela el secreto y lo aplica
     */
    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta);

    /*
     * Devuelve el nombre del secreto
     */
    public String getNombre();
}
