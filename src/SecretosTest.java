package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.secretos.*;

public class SecretosTest {
    @Test
    public void testearEnormizar() {
        Enormigus enormizar = new Enormigus();
        Carta cartaDeEnormizar = new Carta("Carta de Enormizar", "Enormiza pa", 3, null, null, null, enormizar);
        DañoNormal DañoNormal = new DañoNormal(0);
        Carta cartaDeDañoNormal = new Carta("Carta de prueba", "Carta de prueba", 5, DañoNormal, null, null, null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 5, null, null, null, null);
        Mazo mazo1 = new Mazo();
        mazo1.agregarCarta(cartaDeEnormizar);
        Mazo mazo2 = new Mazo();
        mazo2.agregarCarta(cartaDeDañoNormal);
        mazo2.agregarCarta(cartaQueNoHaceNada);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);
        jugador1.manaActual = 10;
        jugador2.manaActual = 30;
        Tablero tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.mano.jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.recorrerEfectos();
        jugador2.robarCarta();
        jugador2.mano.jugarCarta(0, jugador2, jugador1, tablero1);
        jugador2.mano.jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(19, jugador2.manaActual);
    }
}
