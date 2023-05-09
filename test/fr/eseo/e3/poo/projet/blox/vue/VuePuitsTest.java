package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VuePuitsTest {
    @Test
    void testConstructeur() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits);
        assertEquals(puits, vuePuits.getPuits(), "Test du constructeur avec un objet Puits en paramètre");
        assertEquals(puits.getLargeur() * VuePuits.TAILLE_PAR_DEFAUT, vuePuits.getPreferredSize().width);
        assertEquals(puits.getProfondeur() * VuePuits.TAILLE_PAR_DEFAUT, vuePuits.getPreferredSize().height, "Test des dimensions de vuePuits");
    }

    @Test
    void testConstructeur2() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, 10);
        assertEquals(puits, vuePuits.getPuits());
        assertEquals(10, vuePuits.getTaille(), "Test du constructeur avec un objet Puits et un entier en paramètre");
    }

    @Test
    void testGetterPuits() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits);
        assertEquals(puits, vuePuits.getPuits(), "Test du getter de l'attribut puits");
    }

    @Test
    void testSetterPuits() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits);
        Puits puits2 = new Puits();
        vuePuits.setPuits(puits2);
        assertEquals(puits2, vuePuits.getPuits(), "Test du setter de l'attribut puits");
    }

    @Test
    void testGetterTaille() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, 10);
        assertEquals(10, vuePuits.getTaille(), "Test du getter de l'attribut taille");
    }

    @Test
    void testSetterTaille() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, 10);
        vuePuits.setTaille(20);
        assertEquals(20, vuePuits.getTaille(), "Test du setter de l'attribut taille");
    }

    @Test
    void testGetterVuePiece() {
        Puits puits = new Puits();
        VuePuits vuePuits = new VuePuits(puits, 10);
        assertEquals(null, vuePuits.getVuePiece(), "Test du getter de l'attribut vuePiece");
    }
}
