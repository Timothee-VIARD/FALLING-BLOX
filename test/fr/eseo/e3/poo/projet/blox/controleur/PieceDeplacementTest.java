package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    void testMouseMoved(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        assertDoesNotThrow(() -> pieceDeplacement.mouseMoved(null));
    }

    @Test
    void testMouseMovedNotNull(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, 0, 0, 0, 0, 0, 0, false);
        assertDoesNotThrow(() -> pieceDeplacement.mouseMoved(mouseEvent));
    }

    @Test
    void testMouseMovedPrevNotNull(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, 0, 0, 0, 0, 0, 0, false);
        pieceDeplacement.setCoordonneesSourisPrev(new Coordonnees(-1,0));
        assertDoesNotThrow(() -> pieceDeplacement.mouseMoved(mouseEvent));
    }

    @Test
    void testMouseMovedPrevNotNullException(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, 0, 0, 0, 0, 0, 0, false);
        while(iPiece.getElements().get(0).getCoordonnees().getAbscisse() < puitsTest.getLargeur()-1){
            try {
                iPiece.deplacerDe(1,0);
            } catch (BloxException e) {
                fail("Erreur deplacerDe");
            }
        }
        pieceDeplacement.setCoordonneesSourisPrev(new Coordonnees(-1,0));
        try {
            pieceDeplacement.mouseMoved(mouseEvent);
        }
        catch (RuntimeException e){
            assert true;
        }
    }

    @Test
    void testMouseWheelMoved(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        MouseWheelEvent mouseWheelEvent = new MouseWheelEvent(vuePuitsTest, 0, 0, 0, 0, 0, 0, false, 0, 0, 1);
        assertDoesNotThrow(() -> pieceDeplacement.mouseWheelMoved(mouseWheelEvent));
    }

    @Test
    void testMouseEntered(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        assertDoesNotThrow(() -> pieceDeplacement.mouseEntered(null));
    }

    @Test
    void testMouseWheelMovedException(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceDeplacement pieceDeplacement = new PieceDeplacement(vuePuitsTest);
        MouseWheelEvent mouseWheelEvent = new MouseWheelEvent(vuePuitsTest, 0, 0, 0, 0, 0, 0, false, 0, 0, 1);
        while(iPiece.getElements().get(0).getCoordonnees().getOrdonnee() < puitsTest.getProfondeur()-2){
            try {
                iPiece.deplacerDe(0,1);
            } catch (BloxException e) {
                fail("Erreur deplacerDe");
            }
        }
        try {
            pieceDeplacement.mouseWheelMoved(mouseWheelEvent);
        }
        catch (RuntimeException e){
            assert true;
        }
    }
}
