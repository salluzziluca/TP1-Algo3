package model;

/*
 * Roba cantidad de cartas igual al int que se le pasa en el constructor
 */
public class RobarNormal implements Robar {
    private final int cantidadRobar;

    public RobarNormal(int cantidadRobar) {
        this.cantidadRobar = cantidadRobar;

    }

    @Override
    public void robarCarta(Jugador jugadorAliado) {
        jugadorAliado.robarCarta(cantidadRobar);
    }

}
