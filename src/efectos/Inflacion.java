package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, Aumenta 1 el costo de todas las cartas en mano
 */
public class Inflacion implements Efecto {
    private int duracion;
    private int duracionOriginal;
    private boolean seDebeAplicarElEfecto;

    public Inflacion(int duracion) {
        this.duracion = duracion;
        this.duracionOriginal = duracion;
    }

    public int getDuracionOriginal() {
        return duracionOriginal;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
        }

    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        setearEfectoDefault(jugadorAliado, jugadorEnemigo);
        if (seDebeAplicarElEfecto) {
            jugadorEnemigo.aumentarValorCartas();
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        return;
    }

    @Override
    public void aplicarEfectoACarta(Carta carta) {
        carta.aumentarValor();
    }

    @Override
    public String getNombre() {
        return "Inflacion";
    }

    @Override
    public void modificarDuracion(int duracion) {
        this.duracion += duracion;
    }

    @Override
    public void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto) {
        this.seDebeAplicarElEfecto = seDebeAplicarElEfecto;
    }

    @Override
    public int getDuracion() {
        return this.duracion;
    }

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return false;
    }
}
