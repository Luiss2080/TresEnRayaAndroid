package com.example.tresenrayaandroid.modelos;

/**
 * Clase que representa a un jugador del juego.
 */
public class Jugador {
    private final Ficha ficha;

    public Jugador(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha obtenerFicha() {
        return ficha;
    }
}
