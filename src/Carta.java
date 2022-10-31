package src;

public class Carta {
    public String nombre;
    public String descripcion;
    public int costo;
    public Daño daño;
    public Curación curación;
    public Efecto efecto;
    public Secreto secreto;

    public Carta(String nombre, String descripcion, int costo, Daño daño, Curación curación, Efecto efecto,
            Secreto secreto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.daño = daño;
        this.curación = curación;
        this.efecto = efecto;
        this.secreto = secreto;
    }

    public void alJugarse() {
        System.out.println("Jugando carta");
    }
}
