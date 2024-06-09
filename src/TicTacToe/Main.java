//run main program


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import javax.swing.SwingUtilities;

/**
 *
 * @author vincentyu
 */
public class Main {

    public boolean getVisibility(){
        return true;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenu menu = new StartMenu();
            menu.setVisible(true);
        });
    }
}
