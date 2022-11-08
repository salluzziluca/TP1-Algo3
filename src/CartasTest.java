package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartasTest {
    @Test
    public void testRobarCarta() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(0);
        Carta carta = new Carta("Carta de prueba", "Carta de prueba", 0, CartaDeDañoNormal, null, null, null, null);

        Mazo mazo = new Mazo();
        mazo.agregarCarta(carta);
        Jugador jugador = new Jugador("Jugador de prueba", 10, 10, new Mano(), mazo);
        jugador.robarCarta();
        assertEquals(jugador.getCantidadCartasEnMano(), 1);
    }

    @Test
    public void testearDaño() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 0, CartaDeDañoNormal, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(jugador2.vida, 7);

    }

    @Test
    public void testearMana() {
        DañoNormal CartaDeDañoNormal = new DañoNormal(3);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 100, CartaDeDañoNormal, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(10, jugador2.vida);

    }

    @Test
    public void testearCuraNormal() {
        CuraNormal CartaDeCuraNormal = new CuraNormal(3);
        Carta cartaDeCura = new Carta("Carta de Cura", "Hace cura", 0, null, CartaDeCuraNormal, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeCura);
        Jugador jugador1 = new Jugador("Jugador", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(13, jugador1.vida);
    }

    @Test
    public void testearRobar() {
        RobarNormal CartaDeRobar = new RobarNormal(3);
        Carta cartaDeRobar = new Carta("Carta de Robar", "Roba cartas", 0, null, null, null, null, CartaDeRobar);
        DañoNormal CartaDeDañoNormal = new DañoNormal(0);
        Carta cartaDeDaño = new Carta("Carta de Daño", "Hace daño", 0, CartaDeDañoNormal, null, null, null, null);
        Mazo mazo = new Mazo();
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeDaño);
        mazo.agregarCarta(cartaDeRobar);
        Jugador jugador1 = new Jugador("Jugador", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(3, jugador1.getCantidadCartasEnMano());
    }

}
