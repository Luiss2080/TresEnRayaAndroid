package com.example.tresenrayaandroid;

/**
 * Controlador especializado en la gestión del tablero físico.
 * Maneja la colocación de fichas y la validación de movimientos.
 */
public class ControladorTablero {
    private final Juego modeloJuego;

    public ControladorTablero(Juego juego) {
        this.modeloJuego = juego;
    }

    /**
     * Intenta realizar un movimiento en las coordenadas indicadas.
     * @return true si el movimiento es válido y se realizó.
     */
    public boolean realizarMovimiento(int fila, int columna) {
        // Solo permitimos el movimiento si la partida no ha terminado
        if (modeloJuego.estaFinalizado()) {
            return false;
        }
        return modeloJuego.realizarMovimiento(fila, columna);
    }

    /**
     * Obtiene el símbolo (X o O) del jugador que tiene el turno actual.
     */
    public String obtenerSimboloActual() {
        return modeloJuego.obtenerJugadorActual().obtenerFicha().toString();
    }
}
