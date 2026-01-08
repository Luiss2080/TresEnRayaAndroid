package com.example.tresenrayaandroid;

public class Juego {

    private Tablero tablero;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Jugador jugadorActual;
    private Reglas reglas;
    private boolean finalizado;

    public Juego() {
        tablero = new Tablero();
        jugadorX = new Jugador(Ficha.X);
        jugadorO = new Jugador(Ficha.O);
        jugadorActual = jugadorX;
        reglas = new Reglas();
        finalizado = false;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public boolean realizarMovimiento(int fila, int columna) {
        if (reglas.esMovimientoValido(tablero, fila, columna)) {
            tablero.colocarFicha(fila, columna, jugadorActual.getFicha());
            if (reglas.es3EnRaya(tablero)) {
                finalizado = true;
            } else if (tablero.estaLleno()) {
                finalizado = true;
            }
            return true;
        }
        return false;
    }

    public boolean hayGanador() {
        return reglas.es3EnRaya(tablero);
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
