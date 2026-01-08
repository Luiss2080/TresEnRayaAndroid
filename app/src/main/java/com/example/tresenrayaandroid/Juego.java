package com.example.tresenrayaandroid;

public class Juego {

    private Tablero tablero;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Jugador jugadorActual;
    private boolean finalizado;

    public Juego() {
        tablero = new Tablero();
        jugadorX = new Jugador(Ficha.X);
        jugadorO = new Jugador(Ficha.O);
        jugadorActual = jugadorX;
        finalizado = false;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public boolean realizarMovimiento(int fila, int columna) {
        if (tablero.colocarFicha(fila, columna, jugadorActual.getFicha())) {
            if (tablero.hayGanador()) {
                finalizado = true;
            } else if (tablero.estaLleno()) {
                finalizado = true;
            }
            return true;
        }
        return false;
    }

    public boolean hayGanador() {
        return tablero.hayGanador();
    }

    public boolean estaLleno() {
        return tablero.estaLleno();
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugadorX) ? jugadorO : jugadorX;
    }

    public void reiniciar() {
        tablero.reiniciar();
        jugadorActual = jugadorX;
        finalizado = false;
    }
}
