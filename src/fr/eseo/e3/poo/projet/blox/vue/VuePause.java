package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VuePause {
    private static JFrame frame;
    private static JButton playButton;
    private static JButton reStartButton;
    private static JButton exitButton;
    private VuePuits vuePuits;
    public VuePause(VuePuits vuePuits) {

        this.vuePuits = vuePuits;
        frame = new JFrame("Pause Falling Blox");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        playButton = new JButton("Reprendre");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playHandler();
            }
        });
        panel.add(playButton);
        playButton.setFocusable(false);

        reStartButton = new JButton("Redémarrer");
        reStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reStartHandler();
            }
        });
        panel.add(reStartButton);
        reStartButton.setFocusable(false);

        exitButton = new JButton("Quitter");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);
        exitButton.setFocusable(false);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private void playHandler() {
        getVuePuits().getPuits().getGravite().getTimer().start();
        this.getVuePuits().getGamePause().pauseHandler();
        frame.setVisible(false);
    }

    private void reStartHandler() {
        FallingBloxVersion2.getFrame().dispose();
        frame.setVisible(false);
        new FallingBloxVersion2(new String[0]);
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }
}
