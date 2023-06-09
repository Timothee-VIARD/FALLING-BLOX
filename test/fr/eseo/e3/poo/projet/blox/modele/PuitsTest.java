package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PuitsTest {
    @Test
    void testPuits() {
        Puits puits = new Puits();
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, puits.getLargeur());
        assertEquals(Puits.PROFONDEUR_PAR_DEFAUT, puits.getProfondeur(),
                "Constructeur avec valeurs par défaut");
    }

    @Test
    void testPuitsIntInt() {
        Puits puits = new Puits(5, 15);
        assertEquals(5, puits.getLargeur());
        assertEquals(15, puits.getProfondeur(), "Constructeur avec paramètres");
        try {
            Puits puits2 = new Puits(2, 5);
        } catch (IllegalArgumentException e) {
            assertEquals("La largeur doit être dans l'intervalle [5, 15]", e.getMessage(),
                    "Constructeur avec paramètres incorrects");
        }
        try {
            Puits puits3 = new Puits(5, 5);
        } catch (IllegalArgumentException e) {
            assertEquals("La profondeur doit être dans l'intervalle [15, 25]", e.getMessage(),
                    "Constructeur avec paramètres incorrects");
        }
    }

    @Test
    void testPuitIntIntIntInt() {
        Puits puits = new Puits(5, 15, 0, 0);
        assertEquals(5, puits.getLargeur());
        assertEquals(15, puits.getProfondeur(), "Constructeur avec paramètres");
        try {
            Puits puits2 = new Puits(2, 5, 0, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("La largeur doit être dans l'intervalle [5, 15]", e.getMessage(),
                    "Constructeur avec paramètres incorrects");
        }
        try {
            Puits puits3 = new Puits(5, 5, 0, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("La profondeur doit être dans l'intervalle [15, 25]", e.getMessage(),
                    "Constructeur avec paramètres incorrects");
        }
    }

    @Test
    void testGetPieceActuelle() {
        Puits puits = new Puits();
        assertNull(puits.getPieceActuelle(), "Get piece actuelle");
    }

    @Test
    void testGetPieceSuivante() {
        Puits puits = new Puits();
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante(), "Get piece suivante");
    }

    @Test
    void testGetProfondeur() {
        Puits puits = new Puits();
        assertEquals(15, puits.getProfondeur(), "Get profondeur");
    }

    @Test
    void testGetLargeur() {
        Puits puits = new Puits();
        puits.setLargeur(5);
        assertEquals(5, puits.getLargeur(), "Get largeur");
    }

    @Test
    void testSetPieceSuivante() {
        Puits puits = new Puits();
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante(), "Set piece suivante sans piece suivante");
        puits.setPieceSuivante(oPiece);
        assertEquals(oPiece, puits.getPieceSuivante());
        assertEquals(puits.getLargeur() / 2,
                puits.getPieceActuelle().getElements().get(0).getCoordonnees().getAbscisse());
        assertEquals(-4, puits.getPieceActuelle().getElements().get(0).getCoordonnees().getOrdonnee(),
                "Set piece suivante avec piece suivante");
    }

    @Test
    void testSetProfondeur() {
        Puits puits = new Puits();
        puits.setProfondeur(15);
        assertEquals(15, puits.getProfondeur(), "Set profondeur");
        try {
            Puits puits2 = new Puits();
            puits2.setProfondeur(5);
        } catch (IllegalArgumentException e) {
            assertEquals("La profondeur doit être dans l'intervalle [15, 25]", e.getMessage(),
                    "Set profondeur incorrect");
        }
    }

    @Test
    void testSetLargeur() {
        Puits puits = new Puits();
        puits.setLargeur(5);
        assertEquals(5, puits.getLargeur(), "Set largeur");
        try {
            Puits puits2 = new Puits();
            puits2.setLargeur(20);
        } catch (IllegalArgumentException e) {
            assertEquals("La largeur doit être dans l'intervalle [5, 15]", e.getMessage(),
                    "Set profondeur incorrect");
        }
    }

    @Test
    void testToString() {
        Puits puits = new Puits();
        assertEquals("Puits : Dimension 5 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : <aucune>\n",
                puits.toString());
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        assertEquals(
                "Puits : Dimension 5 x 15\nPiece Actuelle : <aucune>\nPiece Suivante : " + oPiece.toString(),
                puits.toString(), "ToString avec piece suivante");
    }

    @Test
    void testGraviteCollision() {
        Puits puits = new Puits(10, 15);
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        OPiece oPiece2 = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        puits.setPieceSuivante(oPiece2);
        Tas tas = new Tas(puits, 0, 0);

        puits.setTas(tas);
        for (int i = 0; i < 19; i++) {
            puits.gravite();
        }
        assertNotNull(tas.getElements()[puits.getProfondeur()-1], "Elements dans tas, non null");
        assertEquals(Couleur.BLEU, tas.getElements()[puits.getProfondeur()-1][puits.getLargeur()/2].getCouleur(),
                "Elements dans tas, couleur correcte");
    }

    @Test
    void testGravitePasCollision() {
        Puits puits = new Puits(10, 15);
        OPiece oPiece = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        OPiece oPiece2 = new OPiece(new Coordonnees(1, 2), Couleur.BLEU);
        puits.setPieceSuivante(oPiece);
        puits.setPieceSuivante(oPiece2);
        Tas tas = new Tas(puits, 0, 0);

        puits.setTas(tas);
        for (int i = 0; i < 15; i++) {
            puits.gravite();
        }
        assertEquals(11, oPiece.getElements().get(0).getCoordonnees().getOrdonnee(),
                "Pas de collision, piece actuelle");
    }
}