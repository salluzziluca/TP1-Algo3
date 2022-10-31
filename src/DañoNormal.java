package src;

public class DañoNormal implements Daño {
    private int cantidad;

    public DañoNormal(int cantidad) {
        this.cantidad = cantidad;
    }

    public void aplicarDaño() {
        System.out.println(" Aplicando " + cantidad + " puntos de daño");
    }
}
