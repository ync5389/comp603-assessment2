/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author vincentyu
 */
public class Database {
    private static final String DB_URL = "jdbc:derby:TicTacToeDB;create=true";
    private static final String USER = "pdc";  // replace with your database username
    private static final String PASSWORD = "pdc";  // replace with your database password

    public Database() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = conn.createStatement();

            // Create the scores table if it does not exist
            String sql = "CREATE TABLE scores (" +
                         "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                         "player_name VARCHAR(255) NOT NULL, " +
                         "wins INT NOT NULL, " +
                         "losses INT NOT NULL, " +
                         "ties INT NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table 'scores' already exists.");
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        new Database();
    }
}
