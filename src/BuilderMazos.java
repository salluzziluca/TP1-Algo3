package src;

import src.efectos.*;

/*TODO: 
Descripciones de las cartas dinamicas.
Ver como hacer réplica
Ver como hacer oportunista
Ver si berserk es permanente
Ver como hacer veneno destilado
Creo q falta hacer catalizador
Secretos
Testear el buider
*/

public class BuilderMazos {
        public Mazo crearMazoGuerrero() {
                Mazo mazoGuerrero = new Mazo();

                // Ataques
                mazoGuerrero.agregarCarta(new Carta("Golpe", "Daño 2", 1, new DañoNormal(2), null, null, null));

                mazoGuerrero.agregarCarta(
                                new Carta("Triturar", "Daño 3, Inflige 2 Vulnerable", 3, new DañoNormal(3), null,
                                                new Vulnerable(3),
                                                null));

                mazoGuerrero.agregarCarta(new Carta("Réplica", "3 veces Daño 1", 1, null, null, null, null)); // TODO

                // Bufos
                mazoGuerrero.agregarCarta(
                                new Carta("Afilar", " Mejora 2 Afilado", 2, null, null, new Afilado(2), null));

                mazoGuerrero.agregarCarta(
                                new Carta("Oportunista", " Roba cartas igual al daño que hagas este turno", 1, null,
                                                null, null, null)); // TODO

                mazoGuerrero.agregarCarta(
                                new Carta("Ira", "Mejora permanente Berserk; Gastar", 3, null, null, new Berserk(-1),
                                                null));

                // Secretos
                // TODO

                return mazoGuerrero;
        }

        public Mazo crearMazoAlquimista() {
                Mazo MazoAlquimista = new Mazo();

                // Ataques
                MazoAlquimista
                                .agregarCarta(new Carta("Dardo Tóxico", "Daño 1; Inflige 1 Veneno", 1,
                                                new DañoNormal(1), null,
                                                new Veneno(1), null));

                MazoAlquimista
                                .agregarCarta(new Carta("Frasco de Toxinas", "Inflige 3 Veneno", 2, null, null,
                                                new Veneno(3), null));

                MazoAlquimista.agregarCarta(new Carta("Combo", "Daño 1", 0, new DañoNormal(1), null, null, null));

                // Bufos
                MazoAlquimista
                                .agregarCarta(new Carta("Veneno destilado",
                                                "Cura igual a los efectos negativos que tenga el oponente; Roba 1 carta",
                                                1, null, null,
                                                null, null)); // TODO

                MazoAlquimista
                                .agregarCarta(new Carta("Pocion de agilidad",
                                                "Roba 2 cartas", 1, null, null,
                                                null, null)); // TODO

                MazoAlquimista
                                .agregarCarta(new Carta("Catalizador",
                                                "Mejora Catalizador", 2, null, null,
                                                new Catalizador(), null));

                // Secretos
                // TODO

                return MazoAlquimista;
        }

}
