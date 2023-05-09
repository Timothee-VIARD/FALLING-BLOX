package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ElementTest {
    @Test
    public void constructeurTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees);
        assertEquals(1, element.getCoordonnees().getAbscisse());
        assertEquals(2, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.ROUGE, element.getCouleur(), "Test du constructeur avec un objet Coordonnees en paramètre");
    }

    @Test
    public void constructeurTest2() {
        Element element = new Element(1, 2);
        assertEquals(1, element.getCoordonnees().getAbscisse());
        assertEquals(2, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.ROUGE, element.getCouleur(), "Test du constructeur avec deux entiers en paramètre");
    }

    @Test
    public void constructeurTest3() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        assertEquals(1, element.getCoordonnees().getAbscisse());
        assertEquals(2, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.BLEU, element.getCouleur(), "Test du constructeur avec un objet Coordonnees et un objet Couleur en paramètre");
    }

    @Test
    public void constructeurTest4() {
        Element element = new Element(1, 2, Couleur.BLEU);
        assertEquals(1, element.getCoordonnees().getAbscisse());
        assertEquals(2, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.BLEU, element.getCouleur(), "Test du constructeur avec deux entiers et un objet Couleur en paramètre");
    }

    @Test
    public void getterCoordonneesTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees);
        assertEquals(1, element.getCoordonnees().getAbscisse());
        assertEquals(2, element.getCoordonnees().getOrdonnee(), "Test du getter de l'attribut coordonnees");
    }

    @Test
    public void setterCoordonneesTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees);
        element.setCoordonnees(new Coordonnees(3, 4));
        assertEquals(3, element.getCoordonnees().getAbscisse());
        assertEquals(4, element.getCoordonnees().getOrdonnee(), "Test du setter de l'attribut coordonnees");
    }

    @Test
    public void getterCouleurTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        assertEquals(Couleur.BLEU, element.getCouleur(), "Test du getter de l'attribut couleur");
    }

    @Test
    public void setterCouleurTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        element.setCouleur(Couleur.ROUGE);
        assertEquals(Couleur.ROUGE, element.getCouleur(), "Test du setter de l'attribut couleur");
    }

    @Test
    public void toStringTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        assertEquals("(1, 2) - BLEU", element.toString(), "Test de la méthode toString");
    }

    @Test
    public void equalsTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        Element element2 = new Element(coordonnees, Couleur.BLEU);
        assertEquals(true, element.equals(element2), "Test de la méthode equals");
    }

    @Test
    public void hashCodeTest() {
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        Element element2 = new Element(coordonnees, Couleur.BLEU);
        assertEquals(element.hashCode(), element2.hashCode(), "Test de la méthode hashCode");
    }

    @Test
    public void testDeplacerDe(){
        Coordonnees coordonnees = new Coordonnees(1, 2);
        Element element = new Element(coordonnees, Couleur.BLEU);
        element.deplacerDe(1, 1);
        assertEquals(2, element.getCoordonnees().getAbscisse(), "Nouvelle coordonnées incorectes");
        assertEquals(3, element.getCoordonnees().getOrdonnee(), "Nouvelle coordonnées incorectes");

        element.deplacerDe(0, 0);
        assertEquals(2, element.getCoordonnees().getAbscisse(), "Nouvelle coordonnées incorectes");
        assertEquals(3, element.getCoordonnees().getOrdonnee(), "Nouvelle coordonnées incorectes");
    }
}
