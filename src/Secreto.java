package src;

public interface Secreto {
    public void setearSecreto(Jugador jugadorAliado, Jugador jugadorEnemigo);

    public void comprobarSiSeCumple(Carta carta, Jugador jugadorAliado, Jugador jugadorEnemigo, Jugador jugador);

    public void alSerRevelado(Jugador jugadorAliado, Jugador jugadorEnemigo, Carta carta);

    public String getNombre();
}
