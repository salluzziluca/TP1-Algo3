package src;

public class RobarNormal implements Robar {
    private int cantidadRobar;

    public RobarNormal(int cantidadRobar) {
        this.cantidadRobar = cantidadRobar;

    }

    @Override
    public void robarCarta(Jugador jugadorAliado) {
        jugadorAliado.robarCarta(cantidadRobar);
    }

}
