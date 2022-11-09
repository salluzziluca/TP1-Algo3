package src;

import src.efectos.*;
import src.secretos.*;

public class BuilderMazos {

    /*
     * 10 cartas:
     * 2x Réplica
     * 2x Triturar
     * 1x Golpe
     * 1x Afilar
     * 1x Ira
     * 1x Desviar
     * 1x Imparable
     * 1x Oportunista
     */
    public Mazo crearMazoGuerrero() {
        Mazo mazoGuerrero = new Mazo();

        // Ataques
        mazoGuerrero.agregarCarta(new Carta("Réplica", "Daño 1; Robar 1", 1, new DañoNormal(1), null, null,
                null, new RobarNormal(2)));
        mazoGuerrero.agregarCarta(new Carta("Réplica", "Daño 1; Robar 1", 1, new DañoNormal(1), null, null,
                null, new RobarNormal(2)));

        mazoGuerrero.agregarCarta(new Carta("Golpe", "Daño 4", 2, new DañoNormal(4), null, null, null, null));

        mazoGuerrero.agregarCarta(
                new Carta("Triturar", "Daño 3, Inflige 2 Vulnerable", 3, new DañoNormal(3), null,
                        new Vulnerable(3),
                        null, null));

        // Bufos
        mazoGuerrero.agregarCarta(
                new Carta("Afilar", " Mejora 2 Afilado", 2, null, null, new Afilado(2), null, null));

        mazoGuerrero.agregarCarta(
                new Carta("Ira", "Mejora permanente Berserk; Gastar", 3, null, null, new Berserk(-1),
                        null, null));

        // Secretos
        mazoGuerrero.agregarCarta(
                new Carta("Desviar",
                        "Secreto: La proxima vez que tu oponente juegue una carta de daño tu oponente recibirá el daño duplicado",
                        2,
                        null,
                        null, null, new Desviar(), null));

        mazoGuerrero.agregarCarta(
                new Carta("Imparable",
                        "Secreto: La proxima vez que tu oponente juegue una carta que te infligiera un efecto, se limpia el tipo de efecto que esta jugando (si te está afectando). Si no lo tenes, lo que te intentan meter no se pone",
                        1,
                        null,
                        null, null, new Imparable(), null));

        mazoGuerrero.agregarCarta(
                new Carta("Oportunista",
                        "Secreto: La proxima vez que juegues una carta de daño, robas 3", 1,
                        null,
                        null, null, new Oportunista(), null));

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
        Mazo MazoAlquimista = new Mazo();

        // Ataques
        MazoAlquimista.agregarCarta(new Carta("Dardo Tóxico", "Daño 1; Inflige 1 Veneno", 1,
                new DañoNormal(1), null,
                new Veneno(1), null, null));
        MazoAlquimista.agregarCarta(new Carta("Dardo Tóxico", "Daño 1; Inflige 1 Veneno", 1,
                new DañoNormal(1), null,
                new Veneno(1), null, null));

        MazoAlquimista.agregarCarta(new Carta("Frasco de Toxinas", "Inflige 3 Veneno", 2, null, null,
                new Veneno(3), null, null));

        MazoAlquimista.agregarCarta(new Carta("Combo", "Daño 1", 0, new DañoNormal(1), null, null, null, null));

        // Bufos

        MazoAlquimista.agregarCarta(new Carta("Pocion de agilidad",
                "Roba 2 cartas", 1, null, null,
                null, null, new RobarNormal(2)));

        MazoAlquimista.agregarCarta(new Carta("Catalizador",
                "Mejora Catalizador", 2, null, null,
                new Catalizador(), null, null));

        // Secretos
        MazoAlquimista.agregarCarta(
                new Carta("Enormigus",
                        "Secreto: La proxima vez que tu oponente juegue una carta Inflije Inflacion con duracion igual al coste de la carta jugada",
                        1,
                        null,
                        null, null, new Enormigus(), null));

        MazoAlquimista.agregarCarta(
                new Carta("Prevenir y curar",
                        "Secreto: La proxima vez que tu oponente juegue una carta de daño, en vez de dañarte te cura y robas 1 carta",
                        2,
                        null,
                        null, null, new PrevenirYCurar(), null));

        MazoAlquimista.agregarCarta(
                new Carta("Trampa Venenosa",
                        "Secreto: La proxima vez que tu oponente juegue una carta de daño, le aplicas Veneno igual al daño de la carta",
                        2,
                        null,
                        null, null, new TrampaVeneno(), null));

        return MazoAlquimista;
    }

}
