package com.example.tresenrayaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Juego juego;
    private Button[][] buttons = new Button[3][3];
    private TextView status;
    private TextView playerXScoreTV;
    private TextView playerOScoreTV;
    private int playerXScore = 0;
    private int playerOScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        juego = new Juego();
        status = findViewById(R.id.status);
        playerXScoreTV = findViewById(R.id.player_x_score);
        playerOScoreTV = findViewById(R.id.player_o_score);


        GridLayout board = findViewById(R.id.board);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(v -> onCellClicked(row, col));
            }
        }

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(v -> resetGame());
        updateScore();
    }

    private void onCellClicked(int row, int col) {
        if (juego.isFinalizado() || !juego.realizarMovimiento(row, col)) {
            return;
        }

        buttons[row][col].setText(juego.getJugadorActual().getFicha().toString());

        if (juego.hayGanador()) {
            if (juego.getJugadorActual().getFicha() == Ficha.X) {
                playerXScore++;
                status.setText(R.string.player_x_wins);
            } else {
                playerOScore++;
                status.setText(R.string.player_o_wins);
            }
            updateScore();
            disableBoard();
        } else if (juego.estaLleno()) {
            status.setText(R.string.draw);
        } else {
            juego.cambiarTurno();
            if (juego.getJugadorActual().getFicha() == Ficha.X) {
                status.setText(R.string.player_x_turn);
            } else {
                status.setText(R.string.player_o_turn);
            }
        }
    }

    private void resetGame() {
        juego.reiniciar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        status.setText(R.string.player_x_turn);
    }

    private void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void updateScore() {
        playerXScoreTV.setText(getString(R.string.player_x_score, playerXScore));
        playerOScoreTV.setText(getString(R.string.player_o_score, playerOScore));
    }
}
