package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasTest {

    @Test
    void testConstructeurPuits() {
        Puits puit = new Puits();
        Tas tas = new Tas(puit);
        Assertions.assertEquals(0, getNbElements(tas),
                "La construction du tas vide a échoué");
    }

    @Test
    void testConstructeurPuitsElements() {
        Puits puit = new Puits();
        Tas tas = new Tas(puit, 12);
        Assertions.assertEquals(12, getNbElements(tas),
                "La construction du tas avec nbElements a échoué");
    }

    @Test
    void testConstructeurPuitsElementsLignes() {
        Puits puit = new Puits(10, 20);
        Tas tas1 = new Tas(puit, 20, 4);
        Assertions.assertEquals(20, getNbElements(tas1),
                "La construction du tas a échoué");

        try {
            Tas tas2 = new Tas(puit, 50, 3);
        }
        catch (IllegalArgumentException e) {
            Assertions.assertEquals("Le nombre d'éléments est trop grand", e.getMessage(),
                    "Le nombre d'éléments est trop grand et n'a pas été détecté");
        }
    }

    private int getNbElements(Tas tas) {
        Element[][] elements = tas.getElements();
        int nbElements = 0;

        for (Element[] element : elements) {
            for (Element value : element) {
                if (value != null) nbElements++;
            }
        }
        return nbElements;
    }

    @Test
    void getPuits() {
        Puits puit = new Puits();
        Tas tas = new Tas(puit);
        Assertions.assertEquals(puit, tas.getPuits(), "Le getter de Puits a échoué");
    }

    @Test
    void getElements() {
        Puits puit = new Puits();
        Tas tas = new Tas(puit, 10);
        Assertions.assertEquals(10, getNbElements(tas), "Le getter de Elements a échoué");
    }

    @Test
    void ajouterElements() {
        Puits puit = new Puits(10, 15);
        Tas tas = new Tas(puit, 0, 0);
        IPiece iPiece = new IPiece(new Coordonnees(0, 13), Couleur.ROUGE);
        tas.ajouterElements(iPiece);
        Assertions.assertEquals(iPiece.getElements().size(), getNbElements(tas),
                "L'ajout des éléments a échoué");
    }
}