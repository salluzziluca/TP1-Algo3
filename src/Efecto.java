package src;

public interface Efecto {
    public int duracion = 0;

    public void reducirDuracion(Jugador jugadorAliado);

    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo);// TODO si ya esta el efecto tiene que
                                                                            // aumentar la duracion del que ya esta!!

    public void quitarEfecto(Jugador jugadorAliado);

    public void aplicarEfecto(Jugador jugador);

    public String getNombre();

    public void agregarDuracion(int duracion2);
}
