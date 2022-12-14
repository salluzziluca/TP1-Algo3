package modelTest;

import model.*;
import model.efectos.Veneno;
import model.secretos.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertTrue(jugador1.getSecretos().isEmpty());
    }

    @Test
    public void testearCatalizador() {
        BuilderCartas bCartas = new BuilderCartas();
        Mazo mazo = new Mazo();

        bCartas.resetearCarta("Frasco de Toxinas", "Inflige 3 Veneno", 0);
        bCartas.setEfecto(new Veneno(3));
        mazo.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Catalizador", "Secreto: Tu proximo efecto se duplica en duracion", 0);
        bCartas.setSecreto(new Catalizador());
        mazo.agregarCarta(bCartas.crearCarta());

        bCartas.resetearCarta("Frasco de Toxinas", "Inflige 3 Veneno", 0);
        bCartas.setEfecto(new Veneno(3));
        mazo.agregarCarta(bCartas.crearCarta());

        Jugador jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo);
        Jugador jugador2 = new Jugador("Jugador 2", 10, 10, new Mano(), mazo);
        Tablero tablero = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero);
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero);
        assertEquals(9, jugador2.getEfectos().get(0).getDuracion());
        assertTrue(jugador1.getSecretos().isEmpty());
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
        assertTrue(jugador1.getSecretos().isEmpty());
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
        assertTrue(jugador1.getSecretos().isEmpty());
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

        jugador1 = new Jugador("Jugador 1", 10, 10, new Mano(), mazo1);
        jugador2 = new Jugador("Jugador 2", 10, 30, new Mano(), mazo2);

        tablero1 = new Tablero(jugador1, jugador2);
        jugador1.robarCarta();
        jugador1.getMano().jugarCarta(0, jugador1, jugador2, tablero1);
        jugador2.robarCarta();
        jugador2.getMano().jugarCarta(0, jugador2, jugador1, tablero1);
        assertEquals(0, jugador1.getEfectos().size());
        assertTrue(jugador1.getSecretos().isEmpty());
    }
}
