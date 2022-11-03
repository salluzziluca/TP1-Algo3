package src;

public class Afilado implements Efecto {
    private int duracion;

    public Afilado(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public void reducirDuracion() {
        this.duracion--;
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        jugadorAliado.agregarEfecto(this);
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.aumentarAtaque(1);
    }

    @Override
    public String getNombre() {
        return "Afilado";
    }

}
