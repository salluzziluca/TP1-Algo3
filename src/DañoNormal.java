package src;

public class DañoNormal implements Daño {
    private int cantidad;

    public DañoNormal(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aplicarDaño(Jugador jugadorEnemigo) {
        System.out.println(" Aplicando " + cantidad + " puntos de daño");
        jugadorEnemigo.recibirDaño(cantidad);
    }

    public void aumentarDaño(int cantidad) {
        this.cantidad += cantidad;
    }
}
