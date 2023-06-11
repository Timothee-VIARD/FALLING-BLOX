package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class FallingBloxVersion1 {

    private FallingBloxVersion1() {
    }
    public static void main(String[] args) {
        Puits puits;

        switch (args.length) {
            case 1:
                puits = new Puits(5, 15, Integer.parseInt(args[0]),
                        (Integer.parseInt(args[0]) / 5) + 1);
                break;
            case 2:
                puits = new Puits(5, 15, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                break;
            default:
                puits = new Puits(5, 15);
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

        JFrame frame = new JFrame("Falling Blox");
        int x = (puits.getLargeur() + 1) * vuePuits.getTaille() + panneauInformation.getPreferredSize().width;
        int y = (puits.getProfondeur() + 2) * vuePuits.getTaille();
        frame.setSize(x, y);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panneauInformation, BorderLayout.EAST);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.add(vuePuits);
    }
}
