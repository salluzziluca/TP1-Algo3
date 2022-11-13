package src;

public class BuilderCartas {
    private Carta carta;

    public BuilderCartas(String nombre, String descripcion, int costo) {
        resetearCarta(nombre, descripcion, costo);
    }

    public BuilderCartas() {
        return;
    }

    public void resetearCarta(String nombre, String descripcion, int costo) {
        carta = new Carta(nombre, descripcion, costo, null, null, null, null, null);
    }

    public void setDaño(DañoNormal daño) {
        carta.daño = daño;
    }

    public void setCura(CuraNormal cura) {
        carta.cura = cura;
    }

    public void setEfecto(Efecto efecto) {
        carta.efecto = efecto;
    }

    public void setSecreto(Secreto secreto) {
        carta.secreto = secreto;
    }

    public void setRobar(RobarNormal robar) {
        carta.robar = robar;
    }

    public Carta crearCarta() {
        return carta;
    }
}
