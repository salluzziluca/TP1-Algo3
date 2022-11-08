package src;

public interface Daño {
    public void aplicarDaño(Jugador jugadorEnemigo);

    public void modificarDaño(int cantidad);

    public void resetearValores();

    public int getCantidad();
}
