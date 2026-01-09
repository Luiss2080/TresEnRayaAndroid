package com.example.tresenrayaandroid.modelos;

/**
 * Clase Juego: Director del modelo que coordina las piezas lógicas.
 */
public class Juego {

    private final Tablero tablero;
    private final Jugador jugadorX;
    private final Jugador jugadorO;
    private Jugador jugadorActual;
    private final Reglas reglas;
    private boolean finalizado;

    public Juego() {
        tablero = new Tablero();
        jugadorX = new Jugador(Ficha.X);
        jugadorO = new Jugador(Ficha.O);
        jugadorActual = jugadorX;
        reglas = new Reglas();
        finalizado = false;
    }

    public boolean estaFinalizado() {
        return finalizado;
    }

    public Jugador obtenerJugadorActual() {
        return jugadorActual;
    }

    public boolean realizarMovimiento(int fila, int columna) {
        if (reglas.esMovimientoValido(tablero, fila, columna)) {
            tablero.colocarFicha(fila, columna, jugadorActual.obtenerFicha());
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
