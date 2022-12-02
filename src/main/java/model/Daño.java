package model;

public interface Daño {
    /*
     * Daña cantidad de vida igual al int que se le pasa en el constructor
     */
    void aplicarDaño(Jugador jugadorEnemigo);

    /*
     * Modifica la cantidad de daño que esta clase posee
     */
    void modificarDaño(int daño);

    /*
     * resetea los valores de daño a los valores iniciales
     */
    void resetearValores();

    /*
     * Devuelve la cantidad de daño que esta clase posee
     */
    int getCantidad();
}
