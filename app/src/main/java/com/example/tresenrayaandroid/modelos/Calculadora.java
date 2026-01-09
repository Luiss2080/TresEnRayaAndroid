package com.example.tresenrayaandroid.modelos;

/**
 * Clase Calculadora: Realiza cálculos lógicos para determinar el ganador.
 */
public class Calculadora {

    public boolean calcularGanador(Ficha[][] fichas) {
        for (int i = 0; i < 3; i++) {
            // Filas
            if (fichas[i][0] != null && fichas[i][0] == fichas[i][1] && fichas[i][0] == fichas[i][2]) return true;
            // Columnas
            if (fichas[0][i] != null && fichas[0][i] == fichas[1][i] && fichas[0][i] == fichas[2][i]) return true;
        }
        // Diagonales
        if (fichas[0][0] != null && fichas[0][0] == fichas[1][1] && fichas[0][0] == fichas[2][2]) return true;
        return fichas[0][2] != null && fichas[0][2] == fichas[1][1] && fichas[0][2] == fichas[2][0];
    }
}
