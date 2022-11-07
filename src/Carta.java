package src;

public class Carta {
    public String nombre;
    public String descripcion;
    public int costo;
    public Daño daño;
    public Curación curación;
    public Efecto efecto;
    public Secreto secreto;

    public Carta(String nombre, String descripcion, int costo, Daño daño, Curación curación, Efecto efecto,
            Secreto secreto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.daño = daño;
        this.curación = curación;
        this.efecto = efecto;
        this.secreto = secreto;
    }

    private boolean puedeJugarse(int manaActual) {
        if (manaActual < this.costo) {
            return false;
        }
        return true;
    }

    /*
     * Ejecuta el metodo principal de la carta, el cual depende del tipo que esta
     * sea.
     */
    public void alJugarse(Jugador jugadorAliado, Jugador jugadorEnemigo) {
        int manaActual = jugadorAliado.getManaActual();
        if (puedeJugarse(manaActual)) {
            if (this.daño != null) {
                this.daño.aplicarDaño(jugadorEnemigo);
            }
            if (this.efecto != null) {
                this.efecto.setearEfecto(jugadorAliado, jugadorEnemigo);
            }
            if (this.secreto != null) {
                this.secreto.setearSecreto(jugadorAliado, jugadorEnemigo);
            }
            jugadorAliado.modificarMana(-this.costo);
        }
    }

    public void modificarAtaque(int cantidad) {
        if (this.daño != null) {
            this.daño.aumentarDaño(cantidad);
        }
    }

    public void aumentarValor() {
        this.costo++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return this.costo;
    }
}
