package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PieceDeplacementTest {

    public PieceDeplacementTest() {
        testConstructeurPuitsTaille();
    }

    private void testConstructeurPuitsTaille() {
        Puits puits = new Puits(10,15);
        VuePuits vuePuits = new VuePuits(puits, 20);

        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Piece piece1 = UsineDePiece.genererPiece();
        Piece piece2 = UsineDePiece.genererPiece();

        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        JFrame frame = new JFrame("Puits");
        int x = puits.getLargeur() * vuePuits.getTaille();
        int y = puits.getProfondeur() * vuePuits.getTaille();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.add(vuePuits);
        frame.pack();
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable () {
            @Override
            public void run() {
                new PieceDeplacementTest();
            }
        });
    }

}
