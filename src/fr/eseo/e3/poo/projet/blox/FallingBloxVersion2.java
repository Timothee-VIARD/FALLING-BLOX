package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FallingBloxVersion2 {
    private static JFrame frame;
    public static void main(String[] args) {
        new FallingBloxVersion2(args);
    }
    public FallingBloxVersion2(String[] args) {
        Puits puits;

        switch (args.length) {
            case 1:
                puits = new Puits(7, 15, Integer.parseInt(args[0]),
                        (Integer.parseInt(args[0]) / 5) + 1);
                break;
            case 2:
                puits = new Puits(7, 15, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;
            default:
                puits = new Puits(7, 15);
                break;
        }

        VuePuits vuePuits = new VuePuits(puits, 20);
        PanneauInformation panneauInformation = new PanneauInformation(puits);

        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Piece piece1 = UsineDePiece.genererPiece();
        Piece piece2 = UsineDePiece.genererPiece();

        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        vuePuits.setGravite(new Gravite(vuePuits));

        int x = (puits.getLargeur() + 1) * vuePuits.getTaille() + panneauInformation.getPreferredSize().width;
        int y = (puits.getProfondeur() + 2) * vuePuits.getTaille();
        this.frame = new JFrame("Falling Blox");
        frame.setSize(x, y);
        frame.setResizable(false);
        frame.add(panneauInformation, BorderLayout.EAST);
        frame.add(vuePuits);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
