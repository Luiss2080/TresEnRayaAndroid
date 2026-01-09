package com.example.tresenrayaandroid;

/**
 * Controlador especializado en la gestión de la partida global.
 * Maneja puntuaciones, turnos y el estado final del juego.
 */
public class ControladorPartida {
    private final Juego modeloJuego;
    private int puntuacionX = 0;
    private int puntuacionO = 0;

    public ControladorPartida(Juego juego) {
        this.modeloJuego = juego;
    }

    /**
     * Verifica si hay un ganador y actualiza las puntuaciones.
     */
    public boolean verificarGanador() {
        boolean hayGanador = modeloJuego.hayGanador();
        if (hayGanador) {
            if (modeloJuego.obtenerJugadorActual().obtenerFicha() == Ficha.X) {
                puntuacionX++;
            } else {
                puntuacionO++;
            }
        }
        return hayGanador;
    }

    public boolean esEmpate() {
        return modeloJuego.estaLleno() && !modeloJuego.hayGanador();
    }

    public void cambiarTurno() {
        modeloJuego.cambiarTurno();
    }

    public void reiniciar() {
        modeloJuego.reiniciar();
    }

    public int obtenerPuntuacionX() {
        return puntuacionX;
    }

    public int obtenerPuntuacionO() {
        return puntuacionO;
    }

    public boolean partidaFinalizada() {
        return modeloJuego.estaFinalizado();
    }
}
