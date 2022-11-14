package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibe el doble de daño de cartas de daño
 */
public class Vulnerable implements Efecto {
    private int duracion;

    public Vulnerable(int duracion) {
        this.duracion = duracion;
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
    }

    @Override
    public void aplicarEfectoACarta(Carta carta) {
        return;
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        // no hace nada :)
    }

    @Override
    public String getNombre() {
        return "Vulnerable";
    }

    @Override
    public void modificarDuracion(int duracion) {
        this.duracion += duracion;
    }

    @Override
    public void setSeDebeAplicarElEfecto(boolean seDebeAplicarElEfecto) {
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
