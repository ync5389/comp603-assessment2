/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vincentyu
 */
public class PlayerScoreManager {
    private Database database;

    public PlayerScoreManager() {
        database = new Database();
    }

    public PlayerScore getPlayerScore(String name) {
        String sql = "SELECT * FROM scores WHERE player_name = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new PlayerScore(
                        rs.getString("player_name"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("ties")
                );
            } else {
                return new PlayerScore(name, 0, 0, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new PlayerScore(name, 0, 0, 0);
        }
    }

    public void updatePlayerScore(String name, boolean isWin, boolean isTie) {
        PlayerScore score = getPlayerScore(name);
        if (isWin) {
            score.incrementWins();
        } else if (isTie) {
            score.incrementTies();
        } else {
            score.incrementLosses();
        }

        String sql = "UPDATE scores SET wins = ?, losses = ?, ties = ? WHERE player_name = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, score.getWins());
            pstmt.setInt(2, score.getLosses());
            pstmt.setInt(3, score.getTies());
            pstmt.setString(4, name);
            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            if (rowsUpdated == 0) {
                System.out.println("No existing record found for " + name + ". Inserting a new record.");
                String insertSql = "INSERT INTO scores (player_name, wins, losses, ties) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setString(1, name);
                    insertStmt.setInt(2, score.getWins());
                    insertStmt.setInt(3, score.getLosses());
                    insertStmt.setInt(4, score.getTies());
                    int rowsInserted = insertStmt.executeUpdate();
                    System.out.println("Rows inserted: " + rowsInserted);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearScores() {
        String sql = "DELETE FROM scores";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class PlayerScore {
        private String name;
        private int wins;
        private int losses;
        private int ties;

        public PlayerScore(String name, int wins, int losses, int ties) {
            this.name = name;
            this.wins = wins;
            this.losses = losses;
            this.ties = ties;
        }

        public String getName() {
            return name;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public int getTies() {
            return ties;
        }

        public void incrementWins() {
            wins++;
        }

        public void incrementLosses() {
            losses++;
        }

        public void incrementTies() {
            ties++;
        }
    }
}
