/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package TicTacToe;

import java.util.List;
import java.util.Random;

/**
 *
 * @author vincentyu
 */
public class TicTacToeGame {
    private GameBoard board;
    private Player user;
    private Player computer;
    private boolean userTurn;
    private PlayerScoreManager scoreManager;

    public TicTacToeGame(String userName) {
        board = new GameBoard();
        user = new Player(userName);
        computer = new Player("Computer");
        userTurn = true; // User starts first
        scoreManager = new PlayerScoreManager();
    }

    public boolean isUserTurn() {
        return userTurn;
    }

    public GameBoard getBoard() {
        return board;
    }

    public boolean makeMove(int position) {
        if (board.isValidMove(position)) {
            Player currentPlayer = userTurn ? user : computer;
            currentPlayer.addMove(position);
            board.updateBoard(position, userTurn ? "X" : "O");
            userTurn = !userTurn;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        List<List<Integer>> winningCombinations = List.of(
            List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9),
            List.of(1, 4, 7), List.of(2, 5, 8), List.of(3, 6, 9),
            List.of(1, 5, 9), List.of(3, 5, 7)
        );

        for (List<Integer> combination : winningCombinations) {
            if (user.getMoves().containsAll(combination)) {
                updateScore(user.getName(), true, false);
                return true;
            } else if (computer.getMoves().containsAll(combination)) {
                updateScore(user.getName(), false, false);
                return true;
            }
        }
        return false;
    }

    private void updateScore(String playerName, boolean isWin, boolean isTie) {
        scoreManager.updatePlayerScore(playerName, isWin, isTie);
        ScoreManager.appendToFile("score.txt", isWin ? "WIN" : isTie ? "TIE" : "LOST");
    }

    public void recordTie() {
        updateScore(user.getName(), false, true);
    }

    public int getProgramMove() {
        Random random = new Random();
        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!board.isValidMove(move));
        return move;
    }

    public String getUserName() {
        return user.getName();
    }

    public PlayerScoreManager.PlayerScore getUserScore() {
        return scoreManager.getPlayerScore(user.getName());
    }

    public void resetGame() {
    // Reset game board
    board = new GameBoard();
    
    // Reset player moves
    user.getMoves().clear();
    computer.getMoves().clear();
    
    // Reset game state
    userTurn = true;
}


}
