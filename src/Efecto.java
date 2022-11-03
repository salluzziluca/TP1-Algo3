package src;

public interface Efecto {
    public int duracion = 0;

    public void reducirDuracion();

    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    public void aplicarEfecto(Jugador jugador);

    public String getNombre();
}
