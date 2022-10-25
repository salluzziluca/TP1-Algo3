package src;

public class CartaDeAtaque implements Carta {
    private String nombre;
    private String descripcion;
    private int costo;
    private int ataque;

    public CartaDeAtaque(String nombre, String descripcion, int costo, int ataque) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.ataque = ataque;
    }

}