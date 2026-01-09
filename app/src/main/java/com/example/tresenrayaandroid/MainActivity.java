package com.example.tresenrayaandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase MainActivity que actúa como la Vista principal en el patrón MVC.
 * Coordina los controladores especializados para ofrecer la experiencia de juego.
 */
public class MainActivity extends AppCompatActivity {

    // Controladores especializados
    private ControladorTablero controladorTablero;
    private ControladorPartida controladorPartida;

    // Elementos de la interfaz
    private final Button[][] matrizBotones = new Button[3][3];
    private TextView textoEstado;
    private TextView textoPuntuacionX;
    private TextView textoPuntuacionO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el modelo único
        Juego juegoModelo = new Juego();

        // Inicializar los controladores inyectando el mismo modelo
        controladorTablero = new ControladorTablero(juegoModelo);
        controladorPartida = new ControladorPartida(juegoModelo);

        // Vincular la interfaz
        inicializarComponentes();
        configurarTablero();

        // Configurar botón de reinicio
        Button botonReiniciar = findViewById(R.id.reset_button);
        botonReiniciar.setOnClickListener(v -> reiniciarJuego());

        // Mostrar estado inicial
        actualizarMarcadores();
    }

    private void inicializarComponentes() {
        textoEstado = findViewById(R.id.status);
        textoPuntuacionX = findViewById(R.id.player_x_score);
        textoPuntuacionO = findViewById(R.id.player_o_score);
    }

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

    private void manejarClickCelda(int fila, int columna) {
        // Obtenemos el símbolo antes del movimiento para pintarlo si es válido
        String fichaActual = controladorTablero.obtenerSimboloActual();

        // Delegamos el movimiento al controlador del tablero
        if (controladorTablero.realizarMovimiento(fila, columna)) {
            
            // Si fue válido, actualizamos la vista
            matrizBotones[fila][columna].setText(fichaActual);

            // Consultamos al controlador de la partida sobre el resultado
            if (controladorPartida.verificarGanador()) {
                String mensaje = fichaActual.equals("X") ? 
                        getString(R.string.player_x_wins) : getString(R.string.player_o_wins);
                textoEstado.setText(mensaje);
                actualizarMarcadores();
                bloquearTablero();
            } else if (controladorPartida.esEmpate()) {
                textoEstado.setText(R.string.draw);
            } else {
                // Si la partida sigue, cambiamos el turno
                controladorPartida.cambiarTurno();
                actualizarEstadoTurno();
            }
        }
    }

    private void actualizarEstadoTurno() {
        String turno = controladorTablero.obtenerSimboloActual();
        textoEstado.setText(turno.equals("X") ? R.string.player_x_turn : R.string.player_o_turn);
    }

    private void actualizarMarcadores() {
        textoPuntuacionX.setText(getString(R.string.player_x_score, controladorPartida.obtenerPuntuacionX()));
        textoPuntuacionO.setText(getString(R.string.player_o_score, controladorPartida.obtenerPuntuacionO()));
    }

    private void bloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizBotones[i][j].setEnabled(false);
            }
        }
    }

    private void reiniciarJuego() {
        controladorPartida.reiniciar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizBotones[i][j].setText("");
                matrizBotones[i][j].setEnabled(true);
            }
        }
        actualizarEstadoTurno();
    }
}
