package src.efectos;

import src.Efecto;
import src.Jugador;
import src.Carta;

/*
* Al jugarse esta carta, las cartas de Daño del jugador que la juega tienen +1 de daño.
*/
public class Afilado implements Efecto {
    private int duracion;
    private boolean seDebeAplicarElEfecto;
    private boolean seLeAplicaAlAliado;

    public Afilado(int duracion) {
        this.duracion = duracion;
        seLeAplicaAlAliado = true;

    }

    /*
     * Disminuye al duracion del efecto, si es cero, lo quita del jugador y
     * adicionalmente resetea el ataque de las cartas del jugador para que no se
     * acumulen
     */
    @Override
    public void reducirDuracion(Jugador jugadorAliado) {
        this.duracion--;
        if (this.duracion == 0) {
            this.quitarEfecto(jugadorAliado);
            jugadorAliado.modificarAtaque(-1);
        }
    }

    @Override
    public void setearEfecto(Jugador jugadorAliado, Jugador jugadorEnemigo) {

        setearEfectoDefault(jugadorAliado, jugadorEnemigo);
        if (seDebeAplicarElEfecto) {
            jugadorAliado.modificarAtaque(1);
        }

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
    public void aplicarEfectoACarta(Carta carta) {
        carta.modificarAtaque(1);
        return;
    }

    @Override
    public String getNombre() {
        return "Afilado";
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
