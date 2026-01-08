
package com.example.tresenrayaandroid;

public class TicTacToeGame {

    private enum Player { X, O }

    private Player currentPlayer;
    private Player[][] board;
    private boolean gameOver;

    public TicTacToeGame() {
        board = new Player[3][3];
        currentPlayer = Player.X;
        gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String getCurrentPlayer() {
        return currentPlayer.toString();
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                gameOver = true;
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                gameOver = true;
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            gameOver = true;
            return true;
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            gameOver = true;
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    public void resetGame() {
        board = new Player[3][3];
        currentPlayer = Player.X;
        gameOver = false;
    }
}
