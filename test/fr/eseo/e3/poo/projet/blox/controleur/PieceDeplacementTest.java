package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testGetter(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        VuePuits vuePuits = pieceDeplacement.getVuePuits();
        Puits puits = pieceDeplacement.getPuits();
        assertEquals(puitsTest, puits, "Probleme du getter du puits");
        assertEquals(vuePuitsTest, vuePuits, "Probleme du getter de la vuePuits");
    }

    @Test
    void testSetter(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        Puits puits = new Puits(10,15);
        VuePuits vuePuits = new VuePuits(puits);
        pieceDeplacement.setPuits(puits);
        pieceDeplacement.setVuePuits(vuePuits);
        assertEquals(puits, pieceDeplacement.getPuits(), "Probleme du setter du puits");
        assertEquals(vuePuits, pieceDeplacement.getVuePuits(), "Probleme du setter de la vuePuits");
    }
}
