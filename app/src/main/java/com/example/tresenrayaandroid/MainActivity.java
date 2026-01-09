package com.example.tresenrayaandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase MainActivity que actúa como la Vista en el patrón MVC.
 * Se encarga únicamente de la interacción con el usuario y de actualizar la UI.
 */
public class MainActivity extends AppCompatActivity {

    // El Controlador que maneja la lógica
    private ControladorJuego controlador;

    // Elementos de la interfaz
    private final Button[][] matrizBotones = new Button[3][3];
    private TextView textoEstado;
    private TextView textoPuntuacionX;
    private TextView textoPuntuacionO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el controlador
        controlador = new ControladorJuego();

        // Vincular elementos de la vista
        inicializarComponentes();
        
        // Configurar los listeners de los botones del tablero
        configurarTablero();

        // Configurar el botón de reiniciar
        Button botonReiniciar = findViewById(R.id.reset_button);
        botonReiniciar.setOnClickListener(v -> reiniciarJuego());

        // Mostrar puntuación inicial
        actualizarPuntuacionesVista();
    }

    /**
     * Vincula los objetos Java con los IDs del XML.
     */
    private void inicializarComponentes() {
        textoEstado = findViewById(R.id.status);
        textoPuntuacionX = findViewById(R.id.player_x_score);
        textoPuntuacionO = findViewById(R.id.player_o_score);
    }

    /**
     * Busca los botones en el GridLayout y les asigna su evento click.
     */
    private void configurarTablero() {
        GridLayout gridTablero = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String idBoton = "button" + i + j;
                int idRecurso = getResources().getIdentifier(idBoton, "id", getPackageName());
                matrizBotones[i][j] = findViewById(idRecurso);
                
                final int fila = i;
                final int columna = j;
                matrizBotones[i][j].setOnClickListener(v -> manejarClickCelda(fila, columna));
            }
        }
    }

    /**
     * Método que se ejecuta cuando el usuario pulsa una celda.
     * Delega la lógica al controlador.
     */
    private void manejarClickCelda(int fila, int columna) {
        // Le pedimos al controlador que procese el movimiento
        if (controlador.procesarMovimiento(fila, columna)) {
            
            // Si fue válido, pintamos la ficha en la vista
            matrizBotones[fila][columna].setText(controlador.obtenerFichaActual());

            // Comprobamos resultados a través del controlador
            if (controlador.hayGanador()) {
                String mensaje = controlador.obtenerFichaActual().equals("X") ? 
                        getString(R.string.player_x_wins) : getString(R.string.player_o_wins);
                textoEstado.setText(mensaje);
                actualizarPuntuacionesVista();
                bloquearTablero();
            } else if (controlador.esEmpate()) {
                textoEstado.setText(R.string.draw);
            } else {
                // Si el juego sigue, cambiamos de turno
                controlador.siguienteTurno();
                actualizarEstadoTurno();
            }
        }
    }

    /**
     * Actualiza el texto de quién tiene el turno.
     */
    private void actualizarEstadoTurno() {
        String turno = controlador.obtenerFichaActual();
        if (turno.equals("X")) {
            textoEstado.setText(R.string.player_x_turn);
        } else {
            textoEstado.setText(R.string.player_o_turn);
        }
    }

    /**
     * Pide al controlador las puntuaciones y las muestra.
     */
    private void actualizarPuntuacionesVista() {
        textoPuntuacionX.setText(getString(R.string.player_x_score, controlador.obtenerPuntuacionX()));
        textoPuntuacionO.setText(getString(R.string.player_o_score, controlador.obtenerPuntuacionO()));
    }

    /**
     * Desactiva los botones cuando termina la partida.
     */
    private void bloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizBotones[i][j].setEnabled(false);
            }
        }
    }

    /**
     * Limpia la vista y reinicia el modelo a través del controlador.
     */
    private void reiniciarJuego() {
        controlador.reiniciarPartida();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizBotones[i][j].setText("");
                matrizBotones[i][j].setEnabled(true);
            }
        }
        actualizarEstadoTurno();
    }
}
