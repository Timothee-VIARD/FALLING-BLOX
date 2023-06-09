package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IPieceTest {
    @Test
    public void testConstructeur() {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        assertEquals(new Coordonnees(1, 2), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(1, 3), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(1, 1), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(1, 0), iPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(3).getCouleur(),
                "Test constructeur avec bonne couleur et bonne position");
    }

    @Test
    public void testToString() {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        assertEquals("IPiece :\n\t(1, 2) - BLEU\n\t(1, 3) - BLEU\n\t(1, 1) - BLEU\n\t(1, 0) - BLEU\n",
                iPiece.toString(), "Test toString");
    }

    @Test
    public void testSetElements() {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        iPiece.setElements(new Coordonnees(2, 3), Couleur.ROUGE);
        assertEquals(new Coordonnees(2, 3), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(2, 4), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 2), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), iPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.ROUGE, iPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.ROUGE, iPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.ROUGE, iPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.ROUGE, iPiece.getElements().get(3).getCouleur(),
                "Test setElements avec bonne couleur et bonne position");
    }

    @Test
    public void testGetElements() {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        List<Element> elements = new ArrayList<>();
        elements.add(new Element(new Coordonnees(1, 2), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(1, 3), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(1, 1), Couleur.BLEU));
        elements.add(new Element(new Coordonnees(1, 0), Couleur.BLEU));
        assertEquals(elements, iPiece.getElements(),
                "Test getElements avec bonne couleur et bonne position");

    }

    @Test
    public void testSetPosition() {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        iPiece.setPosition(2, 3);
        assertEquals(new Coordonnees(2, 3), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(2, 4), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 2), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), iPiece.getElements().get(3).getCoordonnees());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(0).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(1).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(2).getCouleur());
        assertEquals(Couleur.BLEU, iPiece.getElements().get(3).getCouleur(),
                "Test setPosition avec bonne couleur et bonne position");
    }

    @Test
    public void testDeplacerDe() throws BloxException {
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        iPiece.setPosition(2, 3);

        iPiece.deplacerDe(1, 1);
        assertEquals(new Coordonnees(3, 4), iPiece.getElements().get(0).getCoordonnees());

        try {
            iPiece.deplacerDe(-1, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée y doit être positive");
        }

        try {
            iPiece.deplacerDe(2, -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée |x| doit être inférieure ou égale à 1");
        }

        try {
            iPiece.deplacerDe(-2, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée |x| doit être inférieure ou égale à 1");
        }

        try {
            iPiece.deplacerDe(0, 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Le déplacement ne peut se faire que vers le bas, la droite et la gauche",
                    e.getMessage(), "La coordonnée y doit être inférieure ou égale à 1");
        }
    }

    @Test
    public void testDeplacerException() {
        Puits puits = new Puits(10, 15);
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        iPiece.setPuits(puits);
        iPiece.setPosition(0, 0);
        try {
            iPiece.deplacerDe(-1, 0);
        } catch (BloxException e) {
            assertEquals("Le déplacement ne peut se faire en dehors du puits",
                    e.getMessage(), "Le déplacement ne peut se faire en dehors du puits");
        }
        iPiece.setPosition(puits.getLargeur() - 1, 0);
        try {
            iPiece.deplacerDe(1, 0);
        } catch (BloxException e) {
            assertEquals("Le déplacement ne peut se faire en dehors du puits",
                    e.getMessage(), "Le déplacement ne peut se faire en dehors du puits");
        }
        iPiece.setPosition(0, puits.getProfondeur());
        try {
            iPiece.deplacerDe(0, 1);
        } catch (BloxException e) {
            assertEquals("Collision avec le fond du puits",
                    e.getMessage(), "Collision avec le fond du puits");
        }
        Puits puits1 = new Puits(10, 15, 29, 3);
        iPiece.setPuits(puits1);
        iPiece.setPosition(0, 12);
        try {
            iPiece.deplacerDe(0, 1);
        } catch (BloxException e) {
            assertEquals("Collision avec un élément du tas",
                    e.getMessage(), "Collision avec un élément du tas");
        }
    }

    @Test
    public void testTourner() throws BloxException {
        IPiece iPiece = new IPiece(new Coordonnees(2, 2), Couleur.BLEU);

        iPiece.tourner(true);
        assertEquals(new Coordonnees(2, 2), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(1, 2), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(4, 2), iPiece.getElements().get(3).getCoordonnees());

        iPiece.tourner(false);
        assertEquals(new Coordonnees(2, 2), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(2, 3), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(2, 1), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(2, 0), iPiece.getElements().get(3).getCoordonnees());

        iPiece.tourner(false);
        assertEquals(new Coordonnees(2, 2), iPiece.getElements().get(0).getCoordonnees());
        assertEquals(new Coordonnees(3, 2), iPiece.getElements().get(1).getCoordonnees());
        assertEquals(new Coordonnees(1, 2), iPiece.getElements().get(2).getCoordonnees());
        assertEquals(new Coordonnees(0, 2), iPiece.getElements().get(3).getCoordonnees());
    }

    @Test
    public void testTournerException() {
        Puits puits = new Puits(10, 15);
        IPiece iPiece = new IPiece(new Coordonnees(1, 2), Couleur.BLEU);
        iPiece.setPuits(puits);
        iPiece.setPosition(0, 0);
        try {
            iPiece.tourner(true);
        } catch (BloxException e) {
            assertEquals("La rotation ne peut se faire en dehors du puits",
                    e.getMessage(), "La rotation ne peut se faire en dehors du puits");
        }
        iPiece.setPosition(puits.getLargeur(), 0);
        try {
            iPiece.tourner(true);
        } catch (BloxException e) {
            assertEquals("La rotation ne peut se faire en dehors du puits",
                    e.getMessage(), "La rotation ne peut se faire en dehors du puits");
        }
        iPiece.setPosition(0, puits.getProfondeur());
        try {
            iPiece.tourner(true);
        } catch (BloxException e) {
            assertEquals("La rotation ne peut se faire contre le fond du puits",
                    e.getMessage(), "La rotation ne peut se faire contre le fond du puits");
        }
        Puits puits1 = new Puits(10, 15, 29, 3);
        iPiece.setPuits(puits1);
        iPiece.setPosition(0, 12);
        try {
            iPiece.tourner(true);
        } catch (BloxException e) {
            assertEquals("Collision avec un élément du tas",
                    e.getMessage(), "Collision avec un élément du tas");
        }
    }
}
