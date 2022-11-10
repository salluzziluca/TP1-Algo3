package src;

public interface Efecto {
    public int duracion = 0;

    public void reducirDuracion(Jugador jugadorAliado);

    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    public void quitarEfecto(Jugador jugadorAliado);

    public void aplicarEfecto(Jugador jugador);

    public void aplicarEfectoACarta(Carta carta);

    public String getNombre();

    public void agregarDuracion(int duracion2);
}
