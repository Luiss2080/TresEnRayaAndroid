package com.example.tresenrayaandroid.modelos;

/**
 * CLASE CALCULADORA (MODELO)
 * 
 * Esta clase se encarga exclusivamente de realizar los cálculos matemáticos y lógicos
 * del tablero para determinar estados complejos.
 */
public class Calculadora {

    /**
     * Calcula si existe una combinación ganadora en el tablero actual.
     * 
     * @param fichas Matriz de fichas del tablero.
     * @return true si se encuentra un tres en raya.
     */
    public boolean calcularGanador(Ficha[][] fichas) {
        // Comprobar filas y columnas en un solo ciclo
        for (int i = 0; i < 3; i++) {
            // Comprobación de filas
            if (fichas[i][0] != null && fichas[i][0] == fichas[i][1] && fichas[i][0] == fichas[i][2]) {
                return true;
            }
            // Comprobación de columnas
            if (fichas[0][i] != null && fichas[0][i] == fichas[1][i] && fichas[0][i] == fichas[2][i]) {
                return true;
            }
        }
        
        // Comprobación de diagonales
        if (fichas[0][0] != null && fichas[0][0] == fichas[1][1] && fichas[0][0] == fichas[2][2]) {
            return true;
        }
        
        return fichas[0][2] != null && fichas[0][2] == fichas[1][1] && fichas[0][2] == fichas[2][0];
    }
}
