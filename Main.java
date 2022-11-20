import src.model.BuilderMazos;
import src.model.Jugador;
import src.model.Mano;
import src.model.Mazo;
import src.model.Tablero;

public class Main {
    public static void main(String[] args) {
        BuilderMazos builder = new BuilderMazos();
        Mazo mazoGuerrero = builder.crearMazoGuerrero();
        Mazo MazoAlquimista = builder.crearMazoAlquimista();

        Jugador jugador1 = new Jugador("Jugador 1", 15, 3, new Mano(), mazoGuerrero);
        Jugador jugador2 = new Jugador("Jugador 2", 15, 3, new Mano(), MazoAlquimista);
        Tablero tablero = new Tablero(jugador1, jugador2);

        tablero.jugar();// TODO por ahora no se puede jugar ni en la consola
    }
}
