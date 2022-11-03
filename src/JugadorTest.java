package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JugadorTest {
    @Test
    public void testRobarCarta() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(0);
        Carta carta = new Carta("Carta de prueba", "Carta de prueba", 0, CartaDeDañoNormal, null, null, null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(carta);
        Jugador jugador = new Jugador("Jugador de prueba", 7, new Mano(), mazo);
        jugador.robarCarta();
        assertEquals(jugador.getCantidadCartasEnMano(), 1);
    }

    @Test
    public void testearDaño() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 0, CartaDeDañoNormal, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2);
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testearVeneno() {
        Veneno CartaDeVeneno = new Veneno(3);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Aplica veneno", 0, null, null, CartaDeVeneno, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVeneno);
        Jugador jugador1 = new Jugador("Jugador 1", 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2);
        jugador2.recorrerEfectos();
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testarVulnerable() {
        Vulnerable CartaDeVulnerable = new Vulnerable(3);
        Carta cartaDeVulnerable = new Carta("Carta de Vulnerable", "Aplica vulnerable", 0, null, null,
                CartaDeVulnerable, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeVulnerable);
        Jugador jugador1 = new Jugador("Jugador 1", 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2);
        jugador2.recorrerEfectos();
        jugador2.recibirDaño(3);
        assertEquals(jugador2.vida, 4);

    }
}
