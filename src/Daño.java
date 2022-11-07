package src;

public interface Daño {
    public void aplicarDaño(Jugador jugadorEnemigo);

    public void aumentarDaño(int cantidad);

    public void resetearValores();
}
