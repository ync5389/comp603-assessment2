/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import javax.swing.*;

/**
 *
 * @author vincentyu
 */
public class PlayerNameInput {
    private String playerName;

    public PlayerNameInput() {
        playerName = JOptionPane.showInputDialog(null, "Enter your name:", "Player Name", JOptionPane.QUESTION_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player"; 
        }
    }

    public String getPlayerName() {
        return playerName;
    }
}
