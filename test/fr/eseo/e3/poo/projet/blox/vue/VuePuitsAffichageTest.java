package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class VuePuitsAffichageTest {

    public VuePuitsAffichageTest() {
        testConstructeurPuits();
        testConstructeurPuitsTaille();
        testVuePieceDansVuePuits();
    }

    private void testConstructeurPuits() {
        JFrame frame = new JFrame("Puits");
        VuePuits vuePuits = new VuePuits(new Puits());
        frame.setSize(vuePuits.getPreferredSize());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(vuePuits);
        frame.setVisible(true);
    }

    private void testConstructeurPuitsTaille() {
        JFrame frame2 = new JFrame("Puits et taille");
        VuePuits vuePuits2 = new VuePuits(new Puits(), 30);

        frame2.setSize(vuePuits2.getPreferredSize());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);

        frame2.setLocationRelativeTo(null);

        frame2.add(vuePuits2);
        frame2.setVisible(true);

    }
    private void testVuePieceDansVuePuits() {
        Puits puits = new Puits(10,15, 10, 3);
        VuePuits vuePuits = new VuePuits(puits, 20);

        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        Piece piece1 = UsineDePiece.genererPiece();
        Piece piece2 = UsineDePiece.genererPiece();

        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);

        piece1.setPuits(puits);
        piece2.setPuits(puits);

        JFrame frame = new JFrame("Puits");
        int x = (puits.getLargeur() +  1) * vuePuits.getTaille();
        int y = (puits.getProfondeur() +  2) * vuePuits.getTaille();
        frame.setSize(x, y);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.add(vuePuits);
    }

    public static void main (String [] args) {
        SwingUtilities.invokeLater(new Runnable () {
            @Override
            public void run() {
                new VuePuitsAffichageTest ();
            }
        });
    }
}
