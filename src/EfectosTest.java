package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.efectos.*;

public class EfectosTest {
    @Test
    public void testearVeneno() {
        Veneno CartaDeVeneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 8, null, null, CartaDeVeneno, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        jugador1.manaActual = 10;
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador2.recorrerEfectos();
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testarVulnerable() {
        Vulnerable CartaDeVulnerable = new Vulnerable(3);
        Carta cartaDeVulnerable = new Carta("Carta de Vulnerable", "Aplica vulnerable", 5, null, null,
                CartaDeVulnerable, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVulnerable);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        jugador1.manaActual = 10;
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador2.recibirDaño(3);
        assertEquals(jugador2.vida, 4);

    }

    @Test
    public void testarAfilado() {
        Afilado CartaDeAfilado = new Afilado(3);
        Carta cartaDeAfilado = new Carta("Carta de Afilado", "Aplica afilado", 0, null, null, CartaDeAfilado, null,
                null);
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 0, CartaDeDañoNormal, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeAfilado);
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(1, jugador1, jugador2, tablero);
        jugador1.terminarTurno();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(6, jugador2.vida);
    }

    @Test
    public void testearDuracionDeEfectos() {
        Veneno veneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 0, null, null, veneno, null, null);
        Afilado afilado = new Afilado(2);
        Carta cartaDeAfilado = new Carta("Carta de Afilado", "Aplica afilado", 0, null, null, afilado, null, null);
        Vulnerable vulnerable = new Vulnerable(6);
        Carta cartaDeVulnerable = new Carta("Carta de Vulnerable", "Aplica vulnerable", 0, null, null, vulnerable,
                null, null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        mazo.agregarCarta(cartaDeAfilado);
        mazo.agregarCarta(cartaDeVulnerable);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.manaActual = 10;
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        for (int i = 0; i < 7; i++) {
            jugador1.terminarTurno();
            jugador2.terminarTurno();
        }
        assertEquals(true, jugador1.efectos.isEmpty());
        assertEquals(true, jugador2.efectos.isEmpty());

    }

    @Test
    public void testearBerserk() {
        Berserk berserk = new Berserk(3);
        Carta cartaDeBerserk = new Carta("Carta de Berserk", "Aplica berserk", 0, null, null, berserk, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeBerserk);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador1, tablero);
        assertEquals(11, jugador1.manaMaximo);
        assertEquals(10, jugador1.manaActual);
    }

    @Test
    public void testearCatalizador() {
        Catalizador catalizador = new Catalizador();
        Carta cartaDeCatalizador = new Carta("Carta de Catalizador", "Aplica catalizador", 0, null, null, catalizador,
                null, null);
        Berserk berserk = new Berserk(3);
        Carta cartaDeBerserk = new Carta("Carta de Berserk", "Aplica berserk", 0, null, null, berserk, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeBerserk);
        mazo.agregarCarta(cartaDeCatalizador);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador1, tablero);
        jugador1.recorrerEfectos();
        jugador1.mano.jugarCarta(0, jugador1, jugador1, tablero);
        assertEquals(11, jugador1.manaMaximo);
        for (int i = 0; i < 4; i++) {
            jugador1.terminarTurno();
            jugador2.terminarTurno();
        }
        assertEquals(11, jugador1.manaMaximo);

        for (int i = 0; i < 2; i++) {
            jugador1.terminarTurno();
            jugador2.terminarTurno();
        }
        assertEquals(10, jugador1.manaMaximo);

    }

    @Test
    public void testearInflacionDespuesDeUnTurno() {
        Inflacion inflacion = new Inflacion(3);
        Carta cartaDeInflacion = new Carta("Carta de Inflacion", "Aplica inflacion", 0, null, null, inflacion, null,
                null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 8, null, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaQueNoHaceNada);
        mazo.agregarCarta(cartaDeInflacion);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, new Jugador("Jugador 2", 10, 10, new Mano(), mazo));
        jugador1.manaActual = 10;
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador1, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador1, tablero);
        assertEquals(1, jugador1.manaActual);

    }

    @Test
    public void testearInflacionDespuesDeJugarLaCarta() {
        Inflacion inflacion = new Inflacion(3);
        Carta cartaDeInflacion = new Carta("Carta de Inflacion", "Aplica inflacion", 0, null, null, inflacion, null,
                null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 8, null, null, null, null, null);
        Mazo mazo1 = new Mazo();
        Mazo mazo2 = new Mazo();
        mazo1.agregarCarta(cartaDeInflacion);
        mazo2.agregarCarta(cartaQueNoHaceNada);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo2);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.manaActual = 10;
        jugador2.manaActual = 10;
        jugador1.robarCarta();
        jugador2.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador2.mano.jugarCarta(0, jugador2, jugador1, tablero);
        assertEquals(1, jugador2.manaActual);
    }

    @Test
    public void testearInflacionDespuesDeRobarCarta() {
        Inflacion inflacion = new Inflacion(3);
        Carta cartaDeInflacion = new Carta("Carta de Inflacion", "Aplica inflacion", 0, null, null, inflacion, null,
                null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 8, null, null, null, null, null);
        Mazo mazo1 = new Mazo();
        Mazo mazo2 = new Mazo();
        mazo1.agregarCarta(cartaDeInflacion);
        mazo2.agregarCarta(cartaQueNoHaceNada);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo2);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador2.robarCarta();
        jugador2.mano.jugarCarta(0, jugador2, jugador1, tablero);
        assertEquals(1, jugador2.manaActual);
    }

    @Test
    public void testSetearEfecto() {

        Veneno veneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 0, null, null, veneno, null, null);
        Afilado afilado = new Afilado(3);
        Carta cartaDeAfilado = new Carta("Carta de Afilado", "Aplica afilado", 0, null, null, afilado, null, null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        mazo.agregarCarta(cartaDeVeneno);
        mazo.agregarCarta(cartaDeAfilado);
        mazo.agregarCarta(cartaDeAfilado);

        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.manaActual = 10;
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        for (int i = 0; i < 4; i++) {
            jugador1.terminarTurno();
            jugador2.terminarTurno();
        }
        assertEquals(false, jugador1.efectos.isEmpty());
        assertEquals(false, jugador2.efectos.isEmpty());
        for (int i = 0; i < 4; i++) {
            jugador1.terminarTurno();
            jugador2.terminarTurno();
        }
        assertEquals(true, jugador1.efectos.isEmpty());
        assertEquals(true, jugador2.efectos.isEmpty());
    }

}
