package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibe +1 de maná maximo.
 */
public class Berserk implements Efecto {
    public int duracion;
    private boolean seDebeAplicarElEfecto;
    private boolean seLeAplicaAlAliado;

    public Berserk(int duracion) {
        this.duracion = duracion;
        seLeAplicaAlAliado = true;
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
    public void aplicarEfectoACarta(Carta carta) {
        return;
    }

    @Override
    public void quitarEfecto(Jugador jugadorAliado) {
        jugadorAliado.quitarEfecto(this);

    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        return;
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

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return this.seLeAplicaAlAliado;
    }
}
