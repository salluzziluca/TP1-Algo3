package model;

public class Carta {
    private final String nombre;
    @SuppressWarnings("FieldCanBeLocal")
    private final String descripcion;
    private int costo;
    private final int costoOriginal;
    private Daño daño;
    private Cura cura;
    private Efecto efecto;
    private Secreto secreto;
    private Robar robar;

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

    public Daño getDaño() {
        return daño;
    }

    public void setDaño(Daño daño) {
        if (daño != null) {
            this.daño = daño;
        }
    }

    public Cura getCura() {
        return cura;
    }

    public void setCura(Cura cura) {
        if (cura != null) {
            this.cura = cura;
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

    public Secreto getSecreto() {
        return secreto;
    }

    public void setSecreto(Secreto secreto) {
        if (secreto != null) {
            this.secreto = secreto;
        }
    }

    public Robar getRobar() {
        return robar;
    }

    public void setRobar(Robar robar) {
        if (robar != null) {
            this.robar = robar;
        }
    }
}