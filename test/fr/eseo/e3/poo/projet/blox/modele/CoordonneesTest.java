package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordonneesTest {
    @Test
    public void constructeurTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        assertEquals(1, coordonnees.getAbscisse());
        assertEquals(2, coordonnees.getOrdonnee(), "Test du constructeur");
    }

    @Test
    public void getterTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        assertEquals(1, coordonnees.getAbscisse());
        assertEquals(2, coordonnees.getOrdonnee(), "Test des getters");
    }

    @Test
    public void setterTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        coordonnees.setAbscisse(3);
        coordonnees.setOrdonnee(4);
        assertEquals(3, coordonnees.getAbscisse());
        assertEquals(4, coordonnees.getOrdonnee(), "Test des setters");
    }

    @Test
    public void toStringTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        assertEquals("(1, 2)", coordonnees.toString(), "Test de la méthode toString");
    }

    @Test
    public void equalsTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Coordonnees coordonnees2 = new Coordonnees(1, 2);
        assertEquals(true, coordonnees.equals(coordonnees2), "Test de la méthode equals");
    }

    @Test
    public void hashCodeTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        assertEquals(994, coordonnees.hashCode(), "Test de la méthode hashCode");
    }

}
