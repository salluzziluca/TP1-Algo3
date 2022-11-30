package model;

public class CuraNormal implements Cura {
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    private int cantidad;

    public CuraNormal(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public void aplicarCura(Jugador jugadorAliado) {
        jugadorAliado.aumentarVida(cantidad);
    }
}