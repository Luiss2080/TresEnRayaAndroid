package com.example.tresenrayaandroid.modelos;

/**
 * CLASE JUGADOR (MODELO)
 * 
 * Representa a un participante en el juego.
 * Cada jugador tiene asignada una ficha específica (X u O).
 */
public class Jugador {
    
    // --- Atributos ---
    private final Ficha ficha; // Ficha asignada al jugador

    /**
     * Constructor: Crea un nuevo jugador con su ficha correspondiente.
     * @param ficha Tipo de ficha (X u O).
     */
    public Jugador(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * Obtiene la ficha que pertenece a este jugador.
     * @return El objeto Ficha del jugador.
     */
    public Ficha obtenerFicha() {
        return ficha;
    }
}
