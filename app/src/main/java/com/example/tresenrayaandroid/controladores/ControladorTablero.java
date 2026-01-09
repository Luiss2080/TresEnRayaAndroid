package com.example.tresenrayaandroid.controladores;

import com.example.tresenrayaandroid.modelos.Juego;

/**
 * CONTROLADOR ESPECIALIZADO: GESTIÓN DEL TABLERO
 * 
 * Esta clase se encarga exclusivamente de la interacción física con la rejilla de juego.
 * Actúa como mediador entre los clics en los botones de la Vista y la lógica de
 * posicionamiento en el Modelo.
 */
public class ControladorTablero {

    // --- Atributos ---
    private final Juego modeloJuego; // Referencia al modelo para realizar cambios en el tablero

    /**
     * Constructor: Recibe la instancia del juego para poder manipular el tablero.
     * @param juego Instancia compartida del modelo de juego.
     */
    public ControladorTablero(Juego juego) {
        this.modeloJuego = juego;
    }

    // --- Métodos de Acción ---

    /**
     * Intenta colocar una ficha en una coordenada específica del tablero.
     * 
     * @param fila Fila seleccionada (0-2).
     * @param columna Columna seleccionada (0-2).
     * @return true si el movimiento fue válido (celda vacía y juego activo).
     */
    public boolean realizarMovimiento(int fila, int columna) {
        // Verificamos primero si la partida ya ha concluido
        if (modeloJuego.estaFinalizado()) {
            return false;
        }
        // Solicitamos al modelo que realice la acción lógica de colocar la ficha
        return modeloJuego.realizarMovimiento(fila, columna);
    }

    // --- Métodos de Consulta ---

    /**
     * Obtiene el símbolo del jugador que tiene el turno en este momento.
     * @return Texto "X" u "O" según corresponda.
     */
    public String obtenerSimboloActual() {
        return modeloJuego.obtenerJugadorActual().obtenerFicha().toString();
    }
}
