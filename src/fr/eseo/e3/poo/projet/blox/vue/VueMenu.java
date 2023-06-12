package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VueMenu {
    private static JFrame frame;
    private static JPanel panel = new JPanel();
    private static JButton startButton;
    private static JButton exitButton;
    public VueMenu(String[] args) {
        frame = new JFrame("Menu Falling Blox");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        startButton = new JButton("Démarrer");
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                new FallingBloxVersion2(args);
            }
        });

        frame.add(startButton, BorderLayout.NORTH);
        startButton.setFocusable(false);
        exitButton = new JButton("Quitter");

        frame.add(exitButton, BorderLayout.SOUTH);
        exitButton.setFocusable(false);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new VueMenu(args);
    }
}
