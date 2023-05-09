package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouleurTest {
    @Test
    public void testEnumCouleur() {
        Couleur couleur = Couleur.BLEU;
        assertEquals(Color.BLUE, couleur.getCouleurPourAffichage());
    }
}
