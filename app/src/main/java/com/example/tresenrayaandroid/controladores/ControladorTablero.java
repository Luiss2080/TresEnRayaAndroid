package com.example.tresenrayaandroid.controladores;

import com.example.tresenrayaandroid.modelos.Juego;

/**
 * Controlador especializado en la gestión del tablero físico.
 */
public class ControladorTablero {
    private final Juego modeloJuego;

    public ControladorTablero(Juego juego) {
        this.modeloJuego = juego;
    }

    public boolean realizarMovimiento(int fila, int columna) {
        if (modeloJuego.estaFinalizado()) {
            return false;
        }
        return modeloJuego.realizarMovimiento(fila, columna);
    }

    public String obtenerSimboloActual() {
        return modeloJuego.obtenerJugadorActual().obtenerFicha().toString();
    }
}
