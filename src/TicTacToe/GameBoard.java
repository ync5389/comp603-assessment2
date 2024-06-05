/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincentyu
 */
public class GameBoard {
    private String[][] board;
    private List<Integer> record;

    public GameBoard() {
        board = new String[][]{
            {" ", "|", " ", "|", " "},
            {"-", "+", "-", "+", "-"},
            {" ", "|", " ", "|", " "},
            {"-", "+", "-", "+", "-"},
            {" ", "|", " ", "|", " "}
        };
        record = new ArrayList<>();
    }

    public String[][] getBoard() {
        return board;
    }

    public boolean isValidMove(int position) {
        return position >= 1 && position <= 9 && !record.contains(position);
    }

    public void updateBoard(int position, String symbol) {
        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
        }
        record.add(position);
    }
}

