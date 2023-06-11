package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.JPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.LPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.SPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.ZPiece;
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

        final ArrayList<Piece> pieces = amountOfPiece(1400);

        //compte le nombre de piece de chaque type
        int nbIPiece = 0;
        int nbOPiece = 0;
        int nbTPiece = 0;
        int nbLPiece = 0;
        int nbJPiece = 0;
        int nbZPiece = 0;
        int nbSPiece = 0;
        for (Object p : pieces) {
            if (p instanceof IPiece) {
                nbIPiece++;
            } else if (p instanceof OPiece) {
                nbOPiece++;
            } else if (p instanceof ZPiece) {
                nbZPiece++;
            } else if (p instanceof SPiece) {
                nbSPiece++;
            } else if (p instanceof TPiece) {
                nbTPiece++;
            } else if (p instanceof LPiece) {
                nbLPiece++;
            } else if (p instanceof JPiece) {
                nbJPiece++;
            }
        }

        //test si le nombre de piece de chaque type est bien reparti
        assertTrue(isInInterval(nbIPiece, 100, 300), "Nombre de IPiece incorrect");
        assertTrue(isInInterval(nbOPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isInInterval(nbTPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isInInterval(nbJPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isInInterval(nbLPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isInInterval(nbZPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isInInterval(nbSPiece, 100, 300), "Nombre de OPiece incorrect");
        assertTrue(isSuspicious(nbIPiece, 200), "Nombre de IPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbOPiece, 200), "Nombre de OPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbTPiece, 200), "Nombre de OPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbLPiece, 200), "Nombre de OPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbJPiece, 200), "Nombre de OPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbZPiece, 200), "Nombre de OPiece suspect, relancer le test");
        assertTrue(isSuspicious(nbSPiece, 200), "Nombre de OPiece suspect, relancer le test");
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
            case "LPiece":
            case "JPiece":
            case "TPiece":
            case "SPiece":
            case "ZPiece":
            case "OPiece":
                return true;
            default:
                return false;
        }
    }

    private boolean isDefaultColor(Piece piece) {
        boolean isDefaultColor;
        switch (piece.getClass().getSimpleName()) {
            case "IPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.ORANGE;
                break;
            case "ZPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.CYAN;
                break;
            case "SPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.VIOLET;
                break;
            case "LPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.VERT;
                break;
            case "JPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.JAUNE;
                break;
            case "TPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.BLEU;
                break;
            case "OPiece":
                isDefaultColor = piece.getElements().get(0).getCouleur() == Couleur.ROUGE;
                break;
            default:
                isDefaultColor = false;
        }
        return isDefaultColor;
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
        assertTrue(piece3 instanceof TPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece3), "Couleur non reconnue");
        Piece piece4 = UsineDePiece.genererPiece();
        assertTrue(piece4 instanceof LPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece4), "Couleur non reconnue");
        Piece piece5 = UsineDePiece.genererPiece();
        assertTrue(piece5 instanceof JPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece5), "Couleur non reconnue");
        Piece piece6 = UsineDePiece.genererPiece();
        assertTrue(piece6 instanceof ZPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece6), "Couleur non reconnue");
        Piece piece7 = UsineDePiece.genererPiece();
        assertTrue(piece7 instanceof SPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece7), "Couleur non reconnue");
        Piece piece8 = UsineDePiece.genererPiece();
        assertTrue(piece8 instanceof OPiece, "Retour de l'index");
        assertTrue(isDefaultColor(piece8), "Couleur non reconnue");
    }
}
