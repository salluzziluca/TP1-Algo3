package model;

public class Carta {
    private final String nombre;
    private final String descripcion;
    private final int costoOriginal;
    private final Cura cura;
    private int costo;
    private Daño daño;
    private Efecto efecto;
    private Secreto secreto;
    private Robar robar;

    public Carta(String nombre, String descripcion, int costo, Daño daño, Cura cura, Efecto efecto,
                 Secreto secreto) {
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

    public boolean puedeJugarse(int manaActual) {
        return manaActual >= this.costo;
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
                this.secreto.setearSecreto(jugadorAliado);
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

    public void modificarValor(int sumaCosto) {
        this.costo += sumaCosto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return this.costo;
    }

    public Daño getDaño() {
        return daño;
    }

    public void setDaño(Daño daño) {
        if (daño != null) {
            this.daño = daño;
        }
    }

    public Efecto getEfecto() {
        return efecto;
    }

    public void setEfecto(Efecto efecto) {
        if (efecto != null) {
            this.efecto = efecto;
        }
    }

    public void setSecreto(Secreto secreto) {
        if (secreto != null) {
            this.secreto = secreto;
        }
    }

    public void setRobar(Robar robar) {
        if (robar != null) {
            this.robar = robar;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }
}
