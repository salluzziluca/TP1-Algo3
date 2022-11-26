package model.efectos;

import model.Carta;
import model.Efecto;
import model.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibe el doble de daño de cartas de daño
 */
public class Vulnerable implements Efecto {
    private final int duracionOriginal;
    private int duracion;

    public Vulnerable(int duracion) {
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
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        setearEfectoDefault(jugadorAliado, jugadorEnemigo);
    }

    @Override
    public void aplicarEfectoACarta(Carta carta) {
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

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return false;
    }

}
