package model;

/*
 * Aplica daño al jugador enemigo igual a la cantidad que se le asigna en el constructor
 */
public class DañoNormal implements Daño {
    private int cantidad;
    private final int cantidadOriginal;

    public DañoNormal(int cantidad) {
        this.cantidad = cantidad;
        this.cantidadOriginal = cantidad;
    }

    public void aplicarDaño(Jugador jugadorEnemigo) {
        jugadorEnemigo.recibirDaño(cantidad);
    }

    public void modificarDaño(int cantidad) {
        this.cantidad += cantidad;
    }

    public void resetearValores() {
        this.cantidad = this.cantidadOriginal;
    }

    public int getCantidad() {
        return this.cantidad;
    }
}
