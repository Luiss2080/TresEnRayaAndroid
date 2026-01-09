package com.example.tresenrayaandroid.controladores;

import com.example.tresenrayaandroid.modelos.Ficha;
import com.example.tresenrayaandroid.modelos.Juego;

/**
 * Controlador especializado en la gestión de la partida global.
 */
public class ControladorPartida {
    private final Juego modeloJuego;
    private int puntuacionX = 0;
    private int puntuacionO = 0;

    public ControladorPartida(Juego juego) {
        this.modeloJuego = juego;
    }

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
}
