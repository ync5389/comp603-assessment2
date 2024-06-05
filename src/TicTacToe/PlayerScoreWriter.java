/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author djh0308
 */
public class PlayerScoreWriter {
    public static void writePlayerScore(String playerName, int wins, int losses, int ties) {
        try (FileWriter writer = new FileWriter("player_score.txt", true)) {
            writer.write(playerName + ": Wins - " + wins + ", Losses - " + losses + ", Ties - " + ties + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}