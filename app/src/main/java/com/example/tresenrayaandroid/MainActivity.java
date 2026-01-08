package com.example.tresenrayaandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TicTacToeGame game;
    private Button[][] buttons = new Button[3][3];
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new TicTacToeGame();
        status = findViewById(R.id.status);

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
    }

    private void onCellClicked(int row, int col) {
        if (game.isGameOver() || !game.makeMove(row, col)) {
            return;
        }

        buttons[row][col].setText(game.getCurrentPlayer());

        if (game.checkForWin()) {
            status.setText("Player " + game.getCurrentPlayer() + " wins!");
            disableBoard();
        } else if (game.isBoardFull()) {
            status.setText("It's a draw!");
        } else {
            game.switchPlayer();
            status.setText("Player " + game.getCurrentPlayer() + "'s turn");
        }
    }

    private void resetGame() {
        game.resetGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        status.setText("Player X's turn");
    }

    private void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}
