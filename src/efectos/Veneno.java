package src.efectos;

import src.Carta;
import src.Efecto;
import src.Jugador;

/*
 * Al instanciarse este efecto en el array de efectos de un jugador, este recibirá, al inicio del un daño equivalente a la duracion del efecto
 */
public class Veneno implements Efecto {
    private int duracion;
    private int duracionOriginal;

    public Veneno(int duracion) {
        this.duracion = duracion;
        this.duracionOriginal = duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
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
        return;
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

    @Override
    public boolean getSeLeAplicaAlAliado() {
        return false;
    }

}
