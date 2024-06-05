/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author vincentyu
 */

public class TicTacToeGUI extends JFrame {
    private TicTacToeGame game;
    private JButton[] buttons;
    private JLabel statusLabel;
    private int moveCount;

    public TicTacToeGUI(String playerName) {
        // Initialize game and other components
        game = new TicTacToeGame(playerName);
        buttons = new JButton[9];
        statusLabel = new JLabel(game.getUserName() + "'s turn!");
        moveCount = 0;

        // Set up the layout
        setLayout(new BorderLayout());
        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i + 1));
            boardPanel.add(buttons[i]);
        }
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayPlayerScore();
    }

    private void displayPlayerScore() {
        PlayerScoreManager.PlayerScore score = game.getUserScore();
        JOptionPane.showMessageDialog(this,
                "Welcome " + score.getName() + "!\nWins: " + score.getWins() + "\nLosses: " + score.getLosses() + "\nTies: " + score.getTies(),
                "Player Score", JOptionPane.INFORMATION_MESSAGE);
    }

    private class ButtonClickListener implements ActionListener {
        private int position;

        public ButtonClickListener(int position) {
            this.position = position;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Check if it's the user's turn and make a move
            // Update the button text and game status
            if (game.isUserTurn()) {
                if (game.makeMove(position)) {
                    updateButton(position, "X");
                    moveCount++;
                    if (game.checkForWin()) {
                        gameOver(game.getUserName());
                    } else if (moveCount == 9) {
                        gameOver("It's a tie!");
                    } else {
                        statusLabel.setText("Computer's turn...");
                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int programMove = game.getProgramMove();
                                game.makeMove(programMove);
                                updateButton(programMove, "O");
                                moveCount++;
                                if (game.checkForWin()) {
                                    gameOver("Computer");
                                } else if (moveCount == 9) {
                                    gameOver("It's a tie!");
                                } else {
                                    statusLabel.setText(game.getUserName() + "'s turn!");
                                }
                                ((Timer) evt.getSource()).stop();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid move, try again!");
                }
            }
        }

        private void updateButton(int position, String text) {
            buttons[position - 1].setText(text);
            buttons[position - 1].setEnabled(false);
        }
    }

    private void gameOver(String winner) {
        statusLabel.setText(winner + " wins!");
        JOptionPane optionPane = new JOptionPane(winner + " wins!", JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
        optionPane.add(new JLabel("Do you want to play again?"));
        JDialog dialog = optionPane.createDialog(null, "Game Over");
        dialog.setVisible(true);
        int choice = (int) optionPane.getValue();
        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            dispose();
        }
    }

    private void resetGame() {
        // Reset game state
        game.resetGame();
        statusLabel.setText(game.getUserName() + "'s turn!");
        moveCount = 0;

        // Reset buttons
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }

    
}
