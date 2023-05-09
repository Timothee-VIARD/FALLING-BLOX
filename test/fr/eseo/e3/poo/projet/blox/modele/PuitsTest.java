package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuitsTest {
    @Test
    public void testPuits() {
        Puits puits = new Puits();
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, puits.getLargeur());
        assertEquals(Puits.PROFONDEUR_PAR_DEFAUT, puits.getProfondeur(), "Constructeur avec valeurs par défaut");
    }

    @Test
    public void testPuitsIntInt() {
        Puits puits = new Puits(5, 15);
        assertEquals(5, puits.getLargeur());
        assertEquals(15, puits.getProfondeur(), "Constructeur avec paramètres");
        try {
            Puits puits2 = new Puits(2, 5);
        }
        catch (IllegalArgumentException e) {
            assertEquals("La largeur doit être dans l'intervalle [5, 15]", e.getMessage(), "Constructeur avec paramètres incorrects");
        }
        try {
            Puits puits3 = new Puits(5, 5);
        }
        catch (IllegalArgumentException e) {
            assertEquals("La profondeur doit être dans l'intervalle [15, 25]", e.getMessage(), "Constructeur avec paramètres incorrects");
        }
    }

    @Test
    public void testGetPieceActuelle() {
        Puits puits = new Puits();
        assertEquals(null, puits.getPieceActuelle(), "Get piece actuelle");
    }

    @Test
    public void testGetPieceSuivante() {
        Puits puits = new Puits();
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante(), "Get piece suivante");
    }

    @Test
    public void testGetProfondeur() {
        Puits puits = new Puits();
        assertEquals(15, puits.getProfondeur(), "Get profondeur");
    }

    @Test
    public void testGetLargeur() {
        Puits puits = new Puits();
        puits.setLargeur(5);
        assertEquals(5, puits.getLargeur(), "Get largeur");
    }

    @Test
    public void testSetPieceSuivante() {
        Puits puits = new Puits();
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante(), "Set piece suivante sans piece suivante");
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante());
        assertEquals(puits.getLargeur()/2, puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse());
        assertEquals(-4, puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee(), "Set piece suivante avec piece suivante");
    }

    @Test
    public void testSetProfondeur() {
        Puits puits = new Puits();
        puits.setProfondeur(15);
        assertEquals(15, puits.getProfondeur(), "Set profondeur");
        try {
            Puits puits2 = new Puits();
            puits2.setProfondeur(5);
        }
        catch (IllegalArgumentException e) {
            assertEquals("La profondeur doit être dans l'intervalle [15, 25]", e.getMessage(), "Set profondeur incorrect");
        }
    }

    @Test
    public void testSetLargeur() {
        Puits puits = new Puits();
        puits.setLargeur(5);
        assertEquals(5, puits.getLargeur(), "Set largeur");
        try {
            Puits puits2 = new Puits();
            puits2.setLargeur(20);
        }
        catch (IllegalArgumentException e) {
            assertEquals("La largeur doit être dans l'intervalle [5, 15]", e.getMessage(), "Set profondeur incorrect");
        }
    }

    @Test
    public void testToString() {
        Puits puits = new Puits();
        assertEquals("Puits : Dimension 5 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : <aucune>\n", puits.toString());
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals("Puits : Dimension 5 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : " + oPiece.toString(), puits.toString());
    }

}