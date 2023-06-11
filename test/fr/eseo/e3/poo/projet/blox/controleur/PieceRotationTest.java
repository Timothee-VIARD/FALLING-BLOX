package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.*;
import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.junit.jupiter.api.Test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PieceRotationTest {

    public PieceRotationTest() {
        testAffichagePieceRotation();
    }

    private void testAffichagePieceRotation() {
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
                new PieceRotationTest();
            }
        });
    }

    @Test
    void testPieceRotation() {
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        Puits puits = pieceRotation.getPuits();
        VuePuits vuePuits = pieceRotation.getVuePuits();
        assertEquals(puitsTest, puits, "Probleme du getter du puits");
        assertEquals(vuePuitsTest, vuePuits, "Probleme du getter de la vue du puits");
    }

    @Test
    void testSetterPieceRotation(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        Puits puits = new Puits(10,15);
        VuePuits vuePuits = new VuePuits(puits);
        pieceRotation.setPuits(puits);
        pieceRotation.setVuePuits(vuePuits);
        assertEquals(puits, pieceRotation.getPuits(), "Probleme du setter du puits");
        assertEquals(vuePuits, pieceRotation.getVuePuits(), "Probleme du setter de la vue du puits");
    }

    @Test
    void testMouseClickedLeft(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, MouseEvent.BUTTON1);
        pieceRotation.mouseClicked(mouseEvent);
    }

    @Test
    void testMouseClickedLeftException(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, MouseEvent.BUTTON1);
        while(puitsTest.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse() != 0){
            try {
                puitsTest.getPieceActuelle().deplacerDe(-1,0);
            } catch (BloxException e) {
                fail("Probleme de deplacement de la piece");
            }
        }
        try {
            pieceRotation.mouseClicked(mouseEvent);
        }
        catch (RuntimeException e){
            assert true;
        }
    }

    @Test
    void testMouseClickedRight(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, MouseEvent.BUTTON3);
        pieceRotation.mouseClicked(mouseEvent);
    }

    @Test
    void testMouseClickedRightException(){
        Puits puitsTest = new Puits(10,15);
        VuePuits vuePuitsTest = new VuePuits(puitsTest);
        IPiece iPiece = new IPiece(new Coordonnees(0,0), Couleur.ROUGE);
        puitsTest.setPieceSuivante(iPiece);
        puitsTest.setPieceSuivante(UsineDePiece.genererPiece());
        PieceRotation pieceRotation = new PieceRotation(vuePuitsTest);
        MouseEvent mouseEvent = new MouseEvent(vuePuitsTest, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false, MouseEvent.BUTTON3);
        while(puitsTest.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse() != 0){
            try {
                puitsTest.getPieceActuelle().deplacerDe(-1,0);
            } catch (BloxException e) {
                fail("Probleme de deplacement de la piece");
            }
        }
        try {
            pieceRotation.mouseClicked(mouseEvent);
        }
        catch (RuntimeException e){
            assert true;
        }
    }

}
