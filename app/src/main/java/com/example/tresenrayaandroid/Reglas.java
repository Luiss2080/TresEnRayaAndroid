package com.example.tresenrayaandroid;

public class Reglas {

    public boolean esMovimientoValido(Tablero tablero, int fila, int columna) {
        return tablero.obtenerFicha(fila, columna) == null;
    }

    public boolean es3EnRaya(Tablero tablero) {
        Ficha[][] fichas = tablero.obtenerFichas();

        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (fichas[i][0] != null && fichas[i][0] == fichas[i][1] && fichas[i][0] == fichas[i][2]) {
                return true;
            }
        }

        // Comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (fichas[0][i] != null && fichas[0][i] == fichas[1][i] && fichas[0][i] == fichas[2][i]) {
                return true;
            }
        }

        // Comprobar diagonales
        if (fichas[0][0] != null && fichas[0][0] == fichas[1][1] && fichas[0][0] == fichas[2][2]) {
            return true;
        }

        if (fichas[0][2] != null && fichas[0][2] == fichas[1][1] && fichas[0][2] == fichas[2][0]) {
            return true;
        }

        return false;
    }
}
