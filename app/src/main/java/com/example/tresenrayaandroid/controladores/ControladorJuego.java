package com.example.tresenrayaandroid.controladores;

import com.example.tresenrayaandroid.modelos.Ficha;
import com.example.tresenrayaandroid.modelos.Juego;

/**
 * CONTROLADOR PRINCIPAL DEL JUEGO
 * 
 * Esta clase actúa como el puente (intermediario) entre la interfaz de usuario (Vista)
 * y la lógica interna del juego (Modelo). Sigue el patrón de diseño MVC.
 * 
 * Responsabilidades:
 * - Procesar las acciones del usuario.
 * - Consultar y actualizar el estado del modelo.
 * - Gestionar las puntuaciones de la sesión actual.
 */
public class ControladorJuego {

    // --- Atributos (Variables de Clase) ---
    private final Juego juego;         // Referencia al modelo principal
    private int puntuacionX = 0;       // Contador de victorias para el Jugador X
    private int puntuacionO = 0;       // Contador de victorias para el Jugador O

    /**
     * Constructor: Inicializa el controlador y crea una nueva instancia del modelo.
     */
    public ControladorJuego() {
        this.juego = new Juego();
    }

    // --- Métodos de Acción (Interacción con la Vista) ---

    /**
     * Se llama cuando el usuario intenta realizar un movimiento en el tablero.
     * 
     * @param fila Fila de la matriz (0-2).
     * @param columna Columna de la matriz (0-2).
     * @return true si el movimiento fue permitido y procesado con éxito.
     */
    public boolean procesarMovimiento(int fila, int columna) {
        // Si el juego ya terminó, no permitimos más movimientos
        if (juego.estaFinalizado()) {
            return false;
        }
        // Delegamos la lógica de colocar la ficha al modelo
        return juego.realizarMovimiento(fila, columna);
    }

    /**
     * Reinicia la lógica de la partida actual para empezar de nuevo.
     */
    public void reiniciarPartida() {
        juego.reiniciar();
    }

    /**
     * Indica al modelo que debe pasar el turno al siguiente jugador.
     */
    public void siguienteTurno() {
        juego.cambiarTurno();
    }

    // --- Métodos de Consulta (Estado del Juego) ---

    /**
     * Obtiene el símbolo (X o O) del jugador que tiene el turno actual.
     * @return Representación en texto de la ficha actual.
     */
    public String obtenerFichaActual() {
        return juego.obtenerJugadorActual().obtenerFicha().toString();
    }

    /**
     * Verifica si el último movimiento resultó en una victoria.
     * Si hay ganador, actualiza automáticamente el contador de puntuación.
     * 
     * @return true si el jugador actual ha ganado la partida.
     */
    public boolean hayGanador() {
        boolean ganador = juego.hayGanador();
        if (ganador) {
            // Actualizamos la puntuación según quién sea el jugador actual
            if (juego.obtenerJugadorActual().obtenerFicha() == Ficha.X) {
                puntuacionX++;
            } else {
                puntuacionO++;
            }
        }
        return ganador;
    }

    /**
     * Comprueba si la partida ha terminado en empate (tablero lleno sin ganador).
     * @return true si es un empate.
     */
    public boolean esEmpate() {
        return juego.estaLleno() && !juego.hayGanador();
    }

    /**
     * Informa si el juego ha llegado a su fin (ya sea por victoria o empate).
     */
    public boolean estaFinalizado() {
        return juego.estaFinalizado();
    }

    // --- Métodos de Acceso a Datos (Getters) ---

    public int obtenerPuntuacionX() {
        return puntuacionX;
    }

    public int obtenerPuntuacionO() {
        return puntuacionO;
    }
}
