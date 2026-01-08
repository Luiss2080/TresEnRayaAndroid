package com.example.tresenrayaandroid;

public class Tablero {

    private Ficha[][] fichas;

    public Tablero() {
        fichas = new Ficha[3][3];
    }

    public Ficha obtenerFicha(int fila, int columna) {
        return fichas[fila][columna];
    }

    public Ficha[][] obtenerFichas() {
        return fichas;
    }

    public boolean colocarFicha(int fila, int columna, Ficha ficha) {
        if (fichas[fila][columna] == null) {
            fichas[fila][columna] = ficha;
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
