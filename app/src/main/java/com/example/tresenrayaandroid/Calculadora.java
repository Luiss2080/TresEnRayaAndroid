package com.example.tresenrayaandroid;

/**
 * Clase Calculadora encargada de realizar los cálculos lógicos y matemáticos
 * del juego, cumpliendo con la responsabilidad de procesamiento de datos.
 */
public class Calculadora {

    /**
     * Realiza el cálculo matemático para determinar si hay tres fichas iguales en línea.
     * @param fichas Matriz de fichas del tablero.
     * @return true si se cumple la condición de victoria.
     */
    public boolean calcularTresEnRaya(Ficha[][] fichas) {
        // Comprobación de filas
        for (int i = 0; i < 3; i++) {
            if (fichas[i][0] != null && fichas[i][0] == fichas[i][1] && fichas[i][0] == fichas[i][2]) {
                return true;
            }
        }

        // Comprobación de columnas
        for (int i = 0; i < 3; i++) {
            if (fichas[0][i] != null && fichas[0][i] == fichas[1][i] && fichas[0][i] == fichas[2][i]) {
                return true;
            }
        }

        // Comprobación de diagonales
        if (fichas[0][0] != null && fichas[0][0] == fichas[1][1] && fichas[0][0] == fichas[2][2]) {
            return true;
        }

        if (fichas[0][2] != null && fichas[0][2] == fichas[1][1] && fichas[0][2] == fichas[2][0]) {
            return true;
        }

        return false;
    }

    /**
     * Calcula el porcentaje de victorias de un jugador basado en sus resultados.
     */
    public String calcularPorcentajeVictorias(int victorias, int totalPartidas) {
        if (totalPartidas == 0) return "0%";
        double porcentaje = ((double) victorias / totalPartidas) * 100;
        return String.format("%.1f%%", porcentaje);
    }
}
