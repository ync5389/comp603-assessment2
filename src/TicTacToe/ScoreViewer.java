/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author djh0308
 */

public class ScoreViewer {
    private static final String DB_URL = "jdbc:derby://localhost:1527/TicTacToe";
    private static final String DB_USER = "pdc"; // Change this to your database username
    private static final String DB_PASSWORD = "pdc"; // Change this to your database password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // Execute the SQL query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PDC.SCORES FETCH FIRST 100 ROWS ONLY;");

            // Print the results
            while (resultSet.next()) {
                String playerName = resultSet.getString("player_name");
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                int ties = resultSet.getInt("ties");
                System.out.println("Player: " + playerName + ", Wins: " + wins + ", Losses: " + losses + ", Ties: " + ties);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  

}
