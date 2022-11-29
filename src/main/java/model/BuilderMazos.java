package model;

import model.efectos.Afilado;
import model.efectos.Berserk;
import model.efectos.Veneno;
import model.efectos.Vulnerable;
import model.secretos.*;

public class BuilderMazos {

    /*
     * 10 cartas:
     * 2x Réplica
     * 2x Golpe
     * 1x Triturar
     * 1x Afilar
     * 1x Ira
     * 1x Desviar
     * 1x Imparable
     * 1x Oportunista
     */
    public Mazo crearMazoGuerrero() {
        Mazo mazoGuerrero = new Mazo();
        BuilderCartas bCartas = new BuilderCartas();

        // Ataques
        bCartas.resetearCarta("Réplica", "Daño 1; Robar 1", 1);
        bCartas.setDaño(new DañoNormal(1));
        bCartas.setRobar(new RobarNormal(1));
        mazoGuerrero.agregarCarta(bCartas.crearCarta());
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Golpe", "Daño 3", 1);
        bCartas.setDaño(new DañoNormal(3));
        mazoGuerrero.agregarCarta(bCartas.crearCarta());
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Triturar", "Daño 3, Inflige 2 Vulnerable", 3);
        bCartas.setDaño(new DañoNormal(3));
        bCartas.setEfecto(new Vulnerable(2));
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        // Bufos
        bCartas.resetearCarta("Afilar", "Mejora 3 Afilado", 2);
        bCartas.setEfecto(new Afilado(3));
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Ira", "Mejora permanente Berserk", 3);
        bCartas.setEfecto(new Berserk(-1));
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        // Secretos
        bCartas.resetearCarta("Desviar",
                "Secreto: La proxima vez que tu oponente juegue una carta de daño tu oponente recibirá el daño duplicado",
                2);
        bCartas.setSecreto(new Desviar());
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Imparable",
                "Secreto: La proxima vez que tu oponente juegue una carta que te infligiera un efecto no te lo inflije y se remueve ese efecto si ya lo tenias",
                1);
        bCartas.setSecreto(new Imparable());
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Oportunista",
                "Secreto: La proxima vez que juegues una carta de daño, robas 3",
                1);
        bCartas.setSecreto(new Oportunista());
        mazoGuerrero.agregarCarta(bCartas.crearCarta());

        mazoGuerrero.mezclar();
        return mazoGuerrero;
    }

    /*
     * 10 cartas:
     * 2x Dardo toxico
     * 2x Frasco de Toxinas
     * 1x Combo
     * 1x Pocion de Agilidad
     * 1x Catalizador
     * 1x Enormigus
     * 1x Prevenir y curar
     * 1x Trampa Venenosa
     */
    public Mazo crearMazoAlquimista() {
        Mazo mazoAlquimista = new Mazo();
        BuilderCartas bCartas = new BuilderCartas();

        // Ataques
        bCartas.resetearCarta("Dardo Tóxico", "Daño 1; Inflige 1 Veneno", 1);
        bCartas.setDaño(new DañoNormal(1));
        bCartas.setEfecto(new Veneno(1));
        mazoAlquimista.agregarCarta(bCartas.crearCarta());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Frasco de Toxinas", "Inflige 3 Veneno", 2);
        bCartas.setEfecto(new Veneno(3));
        mazoAlquimista.agregarCarta(bCartas.crearCarta());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Combo", "Daño 1", 0);
        bCartas.setDaño(new DañoNormal(1));
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        // Bufos
        bCartas.resetearCarta("Pocion de agilidad", "Roba 2 cartas", 1);
        bCartas.setRobar(new RobarNormal(2));
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        // Secretos
        bCartas.resetearCarta("Catalizador", "Secreto: Tu proximo efecto se duplica en duracion", 0);
        bCartas.setSecreto(new Catalizador());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Enormigus",
                "Secreto: La proxima vez que tu oponente juegue una carta Inflije Inflacion con duracion igual al coste de la carta jugada",
                2);
        bCartas.setSecreto(new Enormigus());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Prevenir y curar",
                "Secreto: La proxima vez que tu oponente juegue una carta de daño, en vez de dañarte te cura y robas 1 carta",
                2);
        bCartas.setSecreto(new PrevenirYCurar());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Trampa Venenosa",
                "Secreto: La proxima vez que tu oponente juegue una carta de daño, le aplicas Veneno igual al daño de la carta",
                2);
        bCartas.setSecreto(new TrampaVeneno());
        mazoAlquimista.agregarCarta(bCartas.crearCarta());

        mazoAlquimista.mezclar();
        return mazoAlquimista;
    }

}
