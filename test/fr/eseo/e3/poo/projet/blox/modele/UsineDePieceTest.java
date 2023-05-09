package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsineDePieceTest {
    @Test
    void testSetMode() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
        assertEquals(UsineDePiece.ALEATOIRE_COMPLET, UsineDePiece.getMode());
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        assertEquals(UsineDePiece.ALEATOIRE_PIECE, UsineDePiece.getMode());
        UsineDePiece.setMode(UsineDePiece.CYCLIC);
        assertEquals(UsineDePiece.CYCLIC, UsineDePiece.getMode(), "Mode non reconnu");
    }

    @Test
    void testGenererPieceAC() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);

        final ArrayList<Piece> pieces = amountOfPiece(1000);

        //compte le nombre de piece de chaque type
        int nbIPiece = 0;
        int nbOPiece = 0;
        for (Object p : pieces) {
            if (p instanceof IPiece) {
                nbIPiece++;
            } else if (p instanceof OPiece) {
                nbOPiece++;
            }
        }

        //test si le nombre de piece de chaque type est bien reparti
        assertTrue(isInInterval(nbIPiece, 400, 600), "Nombre de IPiece incorrect");
        assertTrue(isInInterval(nbOPiece, 400, 600), "Nombre de OPiece incorrect");
        assertTrue(isSuspicious(nbIPiece, 500), "Nombre de IPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbOPiece, 500), "Nombre de OPiece suspect, relancer le test");

    }

    private ArrayList<Piece> amountOfPiece(int nbPiece) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < nbPiece; i++) {
            pieces.add(UsineDePiece.genererPiece());
        }
        return pieces;
    }

    private boolean isInInterval(int value, int min, int max) {
        return value >= min && value <= max;
    }

    private boolean isSuspicious(int value, int nbTheorique) {
        return value != nbTheorique;
    }

    @Test
    void testGenererPieceAP() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
        Piece piece = UsineDePiece.genererPiece();
        assertTrue(isAnXPiece(piece), "Piece non reconnue");
        assertTrue(isDefaultColor(piece), "Couleur non reconnue");
    }

    private boolean isAnXPiece(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "IPiece":
            case "OPiece":
                return true;
            default:
                return false;
        }
    }

    private boolean isDefaultColor(Piece piece) {
        switch (piece.getClass().getSimpleName()) {
            case "IPiece":
                return piece.getElements().get(0).getCouleur() == Couleur.ORANGE;
            case "OPiece":
                return piece.getElements().get(0).getCouleur() == Couleur.ROUGE;
            default:
                return false;
        }
    }

    @Test
    void testGenererPieceC() {
        UsineDePiece.setMode(UsineDePiece.CYCLIC);
        Piece piece = UsineDePiece.genererPiece();
        //test si la piece est bien une instance de OPiece
        assertTrue(piece instanceof OPiece, "OPiece non reconnue");
        //test si la couleur de la piece est bien une instance de Couleur
        assertTrue(isDefaultColor(piece), "Couleur non reconnue");
        Piece piece2 = UsineDePiece.genererPiece();
        assertTrue(piece2 instanceof IPiece, "IPiece non reconnue");
        assertTrue(isDefaultColor(piece2), "Couleur non reconnue");
        Piece piece3 = UsineDePiece.genererPiece();
        assertTrue(piece3 instanceof OPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece3), "Couleur non reconnue");
    }
}
