package com.example.tresenrayaandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Juego juego;
    private final Button[][] botones = new Button[3][3];
    private TextView estado;
    private TextView puntuacionJugadorXTV;
    private TextView puntuacionJugadorOTV;
    private int puntuacionJugadorX = 0;
    private int puntuacionJugadorO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        juego = new Juego();
        estado = findViewById(R.id.status);
        puntuacionJugadorXTV = findViewById(R.id.player_x_score);
        puntuacionJugadorOTV = findViewById(R.id.player_o_score);

        GridLayout tablero = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String idBoton = "button" + i + j;
                int idRecurso = getResources().getIdentifier(idBoton, "id", getPackageName());
                botones[i][j] = findViewById(idRecurso);
                final int fila = i;
                final int columna = j;
                botones[i][j].setOnClickListener(v -> alPulsarCelda(fila, columna));
            }
        }

        Button botonReiniciar = findViewById(R.id.reset_button);
        botonReiniciar.setOnClickListener(v -> reiniciarPartida());
        actualizarPuntuacion();
    }

    private void alPulsarCelda(int fila, int columna) {
        if (juego.estaFinalizado() || !juego.realizarMovimiento(fila, columna)) {
            return;
        }

        botones[fila][columna].setText(juego.obtenerJugadorActual().obtenerFicha().toString());

        if (juego.hayGanador()) {
            if (juego.obtenerJugadorActual().obtenerFicha() == Ficha.X) {
                puntuacionJugadorX++;
                estado.setText(R.string.player_x_wins);
            } else {
                puntuacionJugadorO++;
                estado.setText(R.string.player_o_wins);
            }
            actualizarPuntuacion();
            desactivarTablero();
        } else if (juego.estaLleno()) {
            estado.setText(R.string.draw);
        } else {
            juego.cambiarTurno();
            if (juego.obtenerJugadorActual().obtenerFicha() == Ficha.X) {
                estado.setText(R.string.player_x_turn);
            } else {
                estado.setText(R.string.player_o_turn);
            }
        }
    }

    private void reiniciarPartida() {
        juego.reiniciar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("");
                botones[i][j].setEnabled(true);
            }
        }
        estado.setText(R.string.player_x_turn);
    }

    private void desactivarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }

    private void actualizarPuntuacion() {
        puntuacionJugadorXTV.setText(getString(R.string.player_x_score, puntuacionJugadorX));
        puntuacionJugadorOTV.setText(getString(R.string.player_o_score, puntuacionJugadorO));
    }
}
