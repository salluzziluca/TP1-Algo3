package src;

public class Carta {
    public String nombre;
    public String descripcion;
    public int costo;
    private int costoOriginal;
    public Daño daño;
    public Cura cura;
    public Efecto efecto;
    public Secreto secreto;
    public Robar robar;

    public Carta(String nombre, String descripcion, int costo, Daño daño, Cura cura, Efecto efecto,
            Secreto secreto, Robar robar) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.costoOriginal = costo;
        this.daño = daño;
        this.cura = cura;
        this.efecto = efecto;
        this.secreto = secreto;
        this.robar = robar;
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
            if (this.cura != null) {
                this.cura.aplicarCura(jugadorAliado);
            }
            if (this.robar != null) {
                this.robar.robarCarta(jugadorAliado);
            }
            jugadorAliado.modificarMana(-this.costo);
        }
    }

    public void modificarAtaque(int cantidad) {
        if (this.daño != null) {
            this.daño.modificarDaño(cantidad);
        }
    }

    public void resetearValores() {
        this.costo = this.costoOriginal;
        if (this.daño != null) {
            this.daño.resetearValores();
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

    public Efecto getEfecto() {
        return efecto;
    }

    public Daño getDaño() {
        return daño;
    }

}
