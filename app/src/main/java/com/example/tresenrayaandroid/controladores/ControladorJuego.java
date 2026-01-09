package com.example.tresenrayaandroid.controladores;

/**
 * Clase Controlador que gestiona la comunicación entre la Vista (MainActivity)
 * y el Modelo (Juego). Sigue el patrón de diseño MVC.
 */
public class ControladorJuego {

    private final Juego juego;
    private int puntuacionX = 0;
    private int puntuacionO = 0;

    public ControladorJuego() {
        // Inicializamos el modelo
        this.juego = new Juego();
    }

    /**
     * Procesa el intento de un jugador de colocar una ficha.
     * @param fila Fila seleccionada.
     * @param columna Columna seleccionada.
     * @return true si el movimiento fue válido, false de lo contrario.
     */
    public boolean procesarMovimiento(int fila, int columna) {
        if (juego.estaFinalizado()) return false;
        return juego.realizarMovimiento(fila, columna);
    }

    public String obtenerFichaActual() {
        return juego.obtenerJugadorActual().obtenerFicha().toString();
    }

    public boolean hayGanador() {
        boolean ganador = juego.hayGanador();
        if (ganador) {
            if (juego.obtenerJugadorActual().obtenerFicha() == Ficha.X) {
                puntuacionX++;
            } else {
                puntuacionO++;
            }
        }
        return ganador;
    }

    public boolean esEmpate() {
        return juego.estaLleno() && !juego.hayGanador();
    }

    public void siguienteTurno() {
        juego.cambiarTurno();
    }

    public void reiniciarPartida() {
        juego.reiniciar();
    }

    public int obtenerPuntuacionX() {
        return puntuacionX;
    }

    public int obtenerPuntuacionO() {
        return puntuacionO;
    }
    
    public boolean estaFinalizado() {
        return juego.estaFinalizado();
    }
}
