package controller;

import javafx.scene.Scene;

public interface ObserverRecargarEscena {
    void recargarEscenaTablero();

    void recargarEscenaAnterior(Scene escenaAnterior);

    void crearEscenaInstrucciones(Scene escenaPrevia);
}
