package src;

public interface Daño {
    /*
     * Daña cantidad de vida igual a el int que se le pasa en el constructor
     */
    public void aplicarDaño(Jugador jugadorEnemigo);

    /*
     * Modifica la cantidad de daño que esta clase posee
     */
    public void modificarDaño(int daño);

    /*
     * resetea los valores de daño a los valores iniciales
     */
    public void resetearValores();

    /*
     * Devuelve la cantidad de daño que esta clase posee
     */
    public int getCantidad();
}
