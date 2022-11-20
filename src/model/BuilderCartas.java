package src.model;

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
        carta.setDaño(daño);
    }

    public void setCura(CuraNormal cura) {
        carta.setCura(cura);
    }

    public void setEfecto(Efecto efecto) {
        carta.setEfecto(efecto);
    }

    public void setSecreto(Secreto secreto) {
        carta.setSecreto(secreto);
    }

    public void setRobar(RobarNormal robar) {
        carta.setRobar(robar);
    }

    public Carta crearCarta() {
        return carta;
    }
}
