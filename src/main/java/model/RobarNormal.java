package model;

/*
 * Roba cantidad de cartas igual a el int que se le pasa en el constructor
 */
public class RobarNormal implements Robar {
    @SuppressWarnings("CanBeFinal")
    private int cantidadRobar;

    public RobarNormal(int cantidadRobar) {
        this.cantidadRobar = cantidadRobar;

    }

    @Override
    public void robarCarta(Jugador jugadorAliado) {
        jugadorAliado.robarCarta(cantidadRobar);
    }

}
