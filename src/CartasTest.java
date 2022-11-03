package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartasTest {
    @Test
    public void testRobarCarta() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(0);
        Carta carta = new Carta("Carta de prueba", "Carta de prueba", 0, CartaDeDañoNormal, null, null, null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(carta);
        Jugador jugador = new Jugador("Jugador de prueba", 10, 10, new Mano(), mazo);
        jugador.robarCarta();
        assertEquals(jugador.getCantidadCartasEnMano(), 1);
    }

    @Test
    public void testearDaño() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 0, CartaDeDañoNormal, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador2);
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testearMana() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 100, CartaDeDañoNormal, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, 10, jugador1, jugador2);
        assertEquals(10, jugador2.vida);

    }

}
