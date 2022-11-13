package src;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.secretos.*;
import src.efectos.*;

public class SecretosTest {
    @Test
    public void testearEnormizar() {
        Enormigus ENORMIGUS = new Enormigus();
        Carta cartaDeEnormizar = new Carta("Carta de Enormizar", "Enormiza pa", 3, null, null, null, ENORMIGUS,
                null);
        DañoNormal DañoNormal = new DañoNormal(0);
        Carta cartaDeDañoNormal = new Carta("Carta de prueba", "Carta de prueba", 5, DañoNormal, null, null,
                null,
                null);
        Carta cartaQueNoHaceNada = new Carta("Carta que no hace nada", "No hace nada", 5, null, null, null,
                null, null);
        Mazo mazo1 = new Mazo();
        mazo1.agregarCarta(cartaDeEnormizar);
        Mazo mazo2 = new Mazo();
        mazo2.agregarCarta(cartaDeDañoNormal);
        mazo2.agregarCarta(cartaQueNoHaceNada);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);
        Tablero tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(19, jugador2.getManaActual());
    }

    @Test
    public void testearDesviar() {
        Desviar DESVIAR = new Desviar();
        Carta cartaDeDesviar = new Carta("Carta de Desviar", "Desvía pa", 3, null, null, null, DESVIAR, null);
        DañoNormal DañoNormal = new DañoNormal(2);
        Carta cartaDeDañoNormal = new Carta("Carta de prueba", "Carta de prueba", 5, DañoNormal, null, null,
                null,
                null);
        Mazo mazo1 = new Mazo();
        mazo1.agregarCarta(cartaDeDesviar);
        Mazo mazo2 = new Mazo();
        mazo2.agregarCarta(cartaDeDañoNormal);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);
        Tablero tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(6, jugador2.getVida());
        assertEquals(10, jugador1.getVida());
    }

    @Test
    public void testearPrevenirYCurar() {
        PrevenirYCurar prevenirYCurar = new PrevenirYCurar();
        Carta cartaDePrevenirYCurar = new Carta("Carta de Prevenir y Curar", "Previene y cura pa", 3, null,
                null, null,
                prevenirYCurar, null);
        DañoNormal DañoNormal = new DañoNormal(2);
        Carta cartaDeDañoNormal = new Carta("Carta de prueba", "Carta de prueba", 5, DañoNormal, null, null,
                null,
                null);
        Mazo mazo1 = new Mazo();
        mazo1.agregarCarta(cartaDePrevenirYCurar);
        Mazo mazo2 = new Mazo();
        mazo2.agregarCarta(cartaDeDañoNormal);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);
        Tablero tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(10, jugador2.getVida());
        assertEquals(12, jugador1.getVida());
        assertEquals(1, jugador1.getCantidadCartasEnMano());
    }

    @Test
    public void testearImparable() {
        Imparable imparable = new Imparable();
        Carta cartaDeImparable = new Carta("Carta de Imparable", "Imparable pa", 3, null, null, null,
                imparable, null);
        Veneno veneno = new Veneno(2);
        Carta cartaDeVeneno = new Carta("Carta de Veneno", "Veneno pa", 3, null, null, veneno,
                null, null);
        Mazo mazo1 = new Mazo();
        mazo1.agregarCarta(cartaDeImparable);
        Mazo mazo2 = new Mazo();
        mazo2.agregarCarta(cartaDeVeneno);
        mazo2.agregarCarta(cartaDeVeneno);
        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);

        Tablero tablero1 = new Tablero(jugador1, jugador2);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(0, jugador1.getEfectos().size());

        jugador1 = null;
        jugador2 = null;
        jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);

        tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(0, jugador1.getEfectos().size());

    }
}
