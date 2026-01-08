package com.example.tresenrayaandroid;

public class Jugador {
    private Ficha ficha;

    public Jugador(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ficha obtenerFicha() {
        return ficha;
    }
}
