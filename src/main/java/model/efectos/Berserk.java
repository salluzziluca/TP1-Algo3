package model.efectos;

import model.Carta;
import model.Efecto;
import model.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibe +1 de maná maximo.
 */
public class Berserk implements Efecto {
    private final int duracionOriginal;
    private final boolean seLeAplicaAlAliado;
    public int duracion;
    private boolean seDebeAplicarElEfecto;

    public Berserk(int duracion) {
        this.duracion = duracion;
        this.duracionOriginal = duracion;
        seLeAplicaAlAliado = true;
    }

    public int getDuracionOriginal() {
        return duracionOriginal;
    }

    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
            jugadorAliado.aumentarManaMaximo(-1);
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        setearEfectoDefault(jugadorAliado, jugadorEnemigo);
        if (seDebeAplicarElEfecto) {
            jugadorAliado.aumentarManaMaximo(1);
        }

    }

    @Override
    public String getDescripcion() {
        return "+1 de mana máximo";
    }

    @Override
    public void aplicarEfectoACarta(Carta carta) {
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
    }

    @Override
    public String getNombre() {
        return "Berserk";
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

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return this.seLeAplicaAlAliado;
    }
}
