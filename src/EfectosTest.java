package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.efectos.*;

public class EfectosTest {
    @Test
    public void testearVeneno() {
        Veneno CartaDeVeneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 8, null, null, CartaDeVeneno, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador2);
        jugador2.recorrerEfectos();
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testarVulnerable() {
        Vulnerable CartaDeVulnerable = new Vulnerable(3);
        Carta cartaDeVulnerable = new Carta("Carta de Vulnerable", "Aplica vulnerable", 5, null, null,
                CartaDeVulnerable, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVulnerable);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador2);
        jugador2.recorrerEfectos();
        jugador2.recibirDaño(3);
        assertEquals(jugador2.vida, 4);

    }

    @Test
    public void testarAfilado() {
        Afilado CartaDeAfilado = new Afilado(3);
        Carta cartaDeAfilado = new Carta("Carta de Afilado", "Aplica afilado", 5, null, null, CartaDeAfilado, null);
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 6, CartaDeDañoNormal, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeAfilado);
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(1, 10, jugador1, jugador2);
        jugador1.recorrerEfectos();
        jugador1.terminarTurno();
        jugador1.recorrerEfectos();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador2); // Asumiendo que no se gastan las cartas!!!!
        assertEquals(6, jugador2.vida);
    }

    @Test
    public void testearDuracionDeEfectos() {
        Veneno veneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 1, null, null, veneno, null);
        Afilado afilado = new Afilado(2);
        Carta cartaDeAfilado = new Carta("Carta de Afilado", "Aplica afilado", 3, null, null, afilado, null);
        Vulnerable vulnerable = new Vulnerable(6);
        Carta cartaDeVulnerable = new Carta("Carta de Vulnerable", "Aplica vulnerable", 0, null, null, vulnerable,
                null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        mazo.agregarCarta(cartaDeAfilado);
        mazo.agregarCarta(cartaDeVulnerable);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        for (int i = 0; i < 7; i++) {
            jugador1.terminarTurno();
        }
        assertEquals(true, jugador1.efectos.isEmpty());

    }

    @Test
    public void testearBerserk() {
        Berserk berserk = new Berserk(3);
        Carta cartaDeBerserk = new Carta("Carta de Berserk", "Aplica berserk", 0, null, null, berserk, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeBerserk);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        jugador1.recorrerEfectos();
        assertEquals(11, jugador1.manaMaximo);
        assertEquals(1, jugador1.manaActual);
    }

    @Test
    public void testearInflacion() {
        Inflacion inflacion = new Inflacion(3);
        Carta cartaDeInflacion = new Carta("Carta de Inflacion", "Aplica inflacion", 0, null, null, inflacion, null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 8, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaQueNoHaceNada);
        mazo.agregarCarta(cartaDeInflacion);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        jugador1.manaActual = 10;
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        jugador1.recorrerEfectos();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador1);
        assertEquals(1, jugador1.manaActual);

    }
}
