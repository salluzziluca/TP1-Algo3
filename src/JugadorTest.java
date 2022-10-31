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
}
