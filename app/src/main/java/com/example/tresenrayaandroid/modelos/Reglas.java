package com.example.tresenrayaandroid.modelos;

/**
 * Clase Reglas que define las normas del juego.
 * Utiliza la Calculadora para validar estados complejos del tablero.
 */
public class Reglas {

    private final Calculadora calculadora;

    public Reglas() {
        this.calculadora = new Calculadora();
    }

    /**
     * Valida si un movimiento es permitido según las reglas.
     */
    public boolean esMovimientoValido(Tablero tablero, int fila, int columna) {
        return tablero.obtenerFicha(fila, columna) == null;
    }

    /**
     * Determina si se ha formado un tres en raya.
     */
    public boolean es3EnRaya(Tablero tablero) {
        return calculadora.calcularGanador(tablero.obtenerFichas());
    }
}
