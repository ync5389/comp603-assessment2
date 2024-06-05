/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.sql.*;

/**
 *
 * @author djh0308
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby://localhost:1527/TicTacToeDB;create=true";
    private static final String DB_USER = "pdc";
    private static final String DB_PASSWORD = "pdc";

    public static void main(String[] args) {
        initializeDatabase();

        readDataFromDatabase();

        writeDataToDatabase();
    }
 public static PlayerScoreManager.PlayerScore getPlayerScore(String playerName) {
        // Use try-with-resources to handle resources automatically
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Scores WHERE player_name = ?")) {

            // Set the parameter for the prepared statement
            statement.setString(1, playerName);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a result is returned
            if (resultSet.next()) {
                // Retrieve data from the result set
                int wins = resultSet.getInt("wins");
                int losses = resultSet.getInt("losses");
                int ties = resultSet.getInt("ties");

                // Create and return a PlayerScore object
                return new PlayerScoreManager.PlayerScore(playerName, wins, losses, ties);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if no data is found
        return null;
    }

    // Method to insert player scores into the database
    public static void insertPlayerScore(String playerName, int wins, int losses, int ties) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Scores (player_name, wins, losses, ties) VALUES (?, ?, ?, ?)")) {

            // Set parameters for the prepared statement
            statement.setString(1, playerName);
            statement.setInt(2, wins);
            statement.setInt(3, losses);
            statement.setInt(4, ties);

            // Execute the update
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Players (id INT PRIMARY KEY, name VARCHAR(100))");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Scores (player_id INT, wins INT, losses INT, ties INT)");

            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Players")) {

            while (resultSet.next()) {
                int playerId = resultSet.getInt("id");
                String playerName = resultSet.getString("name");
                System.out.println("Player ID: " + playerId + ", Name: " + playerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Players (id, name) VALUES (?, ?)")) {

            statement.setInt(1, 1);
            statement.setString(2, "Alice");
            statement.executeUpdate();

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
