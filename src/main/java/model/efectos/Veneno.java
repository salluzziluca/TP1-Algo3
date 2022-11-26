package model.efectos;

import model.Carta;
import model.Efecto;
import model.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibirá, al inicio del un daño equivalente a la duracion del efecto
 */
public class Veneno implements Efecto {
    private final int duracionOriginal;
    private int duracion;

    public Veneno(int duracion) {
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
        jugador.recibirDaño(duracion);
    }

    @Override
    public String getNombre() {
        return "Veneno";
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
