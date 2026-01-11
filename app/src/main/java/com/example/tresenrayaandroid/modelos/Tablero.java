package com.example.tresenrayaandroid.modelos;

/**
 * CLASE TABLERO (MODELO)
 * 
 * Representa la matriz de 3x3 donde se desarrolla el juego.
 * Gestiona la ocupación de las celdas y el estado físico de la rejilla.
 */
public class Tablero {

    // --- Atributos ---
    private Ficha[][] fichas; // Matriz bidimensional para almacenar las fichas

    /**
     * Constructor: Inicializa un tablero vacío de 3x3.
     */
    public Tablero() {
        fichas = new Ficha[3][3];
    }

    /**
     * Obtiene la ficha situada en una posición específica.
     * @param fila Fila (0-2).
     * @param columna Columna (0-2).
     * @return La ficha en esa posición o null si está vacía.
     */
    public Ficha obtenerFicha(int fila, int columna) {
        return fichas[fila][columna];
    }

    /**
     * Devuelve la matriz completa de fichas.
     * @return Array bidimensional de fichas.
     */
    public Ficha[][] obtenerFichas() {
        return fichas;
    }

    /**
     * Intenta colocar una ficha en el tablero.
     * @param fila Fila donde colocar.
     * @param columna Columna donde colocar.
     * @param ficha Tipo de ficha a colocar.
     * @return true si la celda estaba vacía y se pudo colocar.
     */
    public boolean colocarFicha(int fila, int columna, Ficha ficha) {
        if (fichas[fila][columna] == null) {
            fichas[fila][columna] = ficha;
            return true;
        }
        return false;
    }

    /**
     * Verifica si todas las celdas del tablero están ocupadas.
     * @return true si el tablero está lleno.
     */
    public boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (fichas[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Limpia el tablero eliminando todas las fichas.
     */
    public void reiniciar() {
        fichas = new Ficha[3][3];
    }
}
