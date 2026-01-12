package com.example.tresenrayaandroid.vistas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tresenrayaandroid.R;
import com.example.tresenrayaandroid.controladores.ControladorPartida;
import com.example.tresenrayaandroid.controladores.ControladorTablero;
import com.example.tresenrayaandroid.modelos.Juego;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

/**
 * Clase MainActivity que actúa como la Vista principal en el patrón MVC.
 */
public class MainActivity extends AppCompatActivity {

    private ControladorTablero controladorTablero;
    private ControladorPartida controladorPartida;

    private final MaterialButton[][] matrizBotones = new MaterialButton[3][3];
    private TextView textoEstado;
    private TextView textoPuntuacionX;
    private TextView textoPuntuacionO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Juego juegoModelo = new Juego();
        controladorTablero = new ControladorTablero(juegoModelo);
        controladorPartida = new ControladorPartida(juegoModelo);

        inicializarComponentes();
        configurarTablero();

        MaterialButton botonReiniciar = findViewById(R.id.reset_button);
        if (botonReiniciar != null) {
            botonReiniciar.setOnClickListener(v -> reiniciarJuego());
        }

        actualizarMarcadores();
        actualizarEstadoTurno();
    }

    private void inicializarComponentes() {
        textoEstado = findViewById(R.id.status);
        textoPuntuacionX = findViewById(R.id.player_x_score);
        textoPuntuacionO = findViewById(R.id.player_o_score);
    }

    private void configurarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String idBoton = String.format(Locale.US, "button%d%d", i, j);
                int idRecurso = getResources().getIdentifier(idBoton, "id", getPackageName());
                matrizBotones[i][j] = findViewById(idRecurso);
                
                final int fila = i;
                final int columna = j;
                if (matrizBotones[i][j] != null) {
                    matrizBotones[i][j].setOnClickListener(v -> manejarClickCelda(fila, columna));
                    matrizBotones[i][j].setText(""); // Limpiar texto de herramientas/diseño
                }
            }
        }
    }

    private void manejarClickCelda(int fila, int columna) {
        String fichaActual = controladorTablero.obtenerSimboloActual();

        if (controladorTablero.realizarMovimiento(fila, columna)) {
            matrizBotones[fila][columna].setText(fichaActual);

            if (controladorPartida.verificarGanador()) {
                String mensaje = fichaActual.equals("X") ? 
                        getString(R.string.player_x_wins) : getString(R.string.player_o_wins);
                if (textoEstado != null) {
                    textoEstado.setText(mensaje);
                }
                actualizarMarcadores();
                bloquearTablero();
            } else if (controladorPartida.esEmpate()) {
                if (textoEstado != null) {
                    textoEstado.setText(R.string.draw);
                }
                bloquearTablero();
            } else {
                controladorPartida.cambiarTurno();
                actualizarEstadoTurno();
            }
        }
    }

    private void actualizarEstadoTurno() {
        if (textoEstado != null) {
            String turno = controladorTablero.obtenerSimboloActual();
            textoEstado.setText(turno.equals("X") ? R.string.player_x_turn : R.string.player_o_turn);
        }
    }

    private void actualizarMarcadores() {
        if (textoPuntuacionX != null) {
            textoPuntuacionX.setText(getString(R.string.player_x_score, controladorPartida.obtenerPuntuacionX()));
        }
        if (textoPuntuacionO != null) {
            textoPuntuacionO.setText(getString(R.string.player_o_score, controladorPartida.obtenerPuntuacionO()));
        }
    }

    private void bloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBotones[i][j] != null) {
                    matrizBotones[i][j].setEnabled(false);
                }
            }
        }
    }

    private void reiniciarJuego() {
        controladorPartida.reiniciar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizBotones[i][j] != null) {
                    matrizBotones[i][j].setText("");
                    matrizBotones[i][j].setEnabled(true);
                }
            }
        }
        actualizarEstadoTurno();
    }
}
