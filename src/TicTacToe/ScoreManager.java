/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToe;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author vincentyu
 */
public class ScoreManager {
    public static void appendToFile(String filePath, String textToAppend) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(textToAppend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readDataFromFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void updateTextInFile(String filePath, String oldText, String newText) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            List<String> updatedLines = lines.stream()
                    .map(line -> line.replace(oldText, newText))
                    .collect(Collectors.toList());

            Files.write(Paths.get(filePath), updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
