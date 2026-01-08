package com.example.tresenrayaandroid;

public class Tablero {

    private Ficha[][] fichas;

    public Tablero() {
        fichas = new Ficha[3][3];
    }

    public boolean colocarFicha(int fila, int columna, Ficha ficha) {
        if (fichas[fila][columna] == null) {
            fichas[fila][columna] = ficha;
            return true;
        }
        return false;
    }

    public boolean hayGanador() {
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

    public void reiniciar() {
        fichas = new Ficha[3][3];
    }
}
