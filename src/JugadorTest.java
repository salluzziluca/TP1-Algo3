package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JugadorTest {
    @Test
    public void testRobarCarta() {
        Carta carta = new CartaDeAtaque("Carta de prueba", "Esta es una carta de prueba", 1, 1);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(carta);
        Jugador jugador = new Jugador("Jugador de prueba", 7, new Mano(), mazo);
        jugador.robarCarta();
        assertEquals(jugador.getCantidadCartasEnMano(), 1);
    }
}
