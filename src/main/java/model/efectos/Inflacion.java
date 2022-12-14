package model.efectos;

import model.Carta;
import model.Efecto;
import model.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, Aumenta 1 el costo de todas las cartas en mano
 */
public class Inflacion implements Efecto {
    private final int duracionOriginal;
    private int duracion;
    private boolean seDebeAplicarElEfecto;

    public Inflacion(int duracion) {
        this.duracion = duracion;
        this.duracionOriginal = duracion;
    }

    public int getDuracionOriginal() {
        return duracionOriginal;
    }

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
            jugadorAliado.modificarValorCartas(-1);
        }

    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        setearEfectoDefault(jugadorAliado, jugadorEnemigo);
        if (seDebeAplicarElEfecto) {
            jugadorEnemigo.modificarValorCartas(1);
        }
    }

    @Override
    public String getDescripcion() {
        return "Aumenta en 1 el costo de todas las cartas en la mano";
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
    }

    @Override
    public void aplicarEfectoACarta(Carta carta) {
        carta.modificarValor(1);
    }

    @Override
    public String getNombre() {
        return "Inflacion";
    }

    @Override
    public void modificarDuracion(int duracionAñadida) {
        this.duracion += duracionAñadida;
    }

    @Override
    public void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto) {
        this.seDebeAplicarElEfecto = seDebeAplicarElEfecto;
    }

    @Override
    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return false;
    }
}
