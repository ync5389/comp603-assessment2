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
public class Player {
    private String name;
    private List<Integer> moves;

    public Player(String name) {
        this.name = name;
        moves = new ArrayList<>();
    }

    public void addMove(int position) {
        moves.add(position);
    }

    public List<Integer> getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }
}

