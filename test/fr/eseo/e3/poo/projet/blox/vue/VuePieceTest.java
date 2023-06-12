package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VuePieceTest {

    @Test
    public void testConstructeurVuePiece() {
        IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.ROUGE);
        VuePiece vuePiece = new VuePiece(piece, 10);
        assertEquals(10, vuePiece.getTaille());
        assertEquals(piece, vuePiece.getPiece(), "Problème lors de la construction de la vue de la pièce");
    }

    @Test
    public void testTeinte() {
        IPiece piece = new IPiece(new Coordonnees(0, 0), Couleur.ROUGE);
        VuePiece vuePiece = new VuePiece(piece, 10);
        Color color = new Color(
                (int) (Couleur.ROUGE.getCouleurPourAffichage().getRed() + (255 - Couleur.ROUGE.getCouleurPourAffichage().getRed()) * 0.3),
                (int) (Couleur.ROUGE.getCouleurPourAffichage().getGreen() + (255 - Couleur.ROUGE.getCouleurPourAffichage().getGreen()) * 0.3),
                (int) (Couleur.ROUGE.getCouleurPourAffichage().getBlue() + (255 - Couleur.ROUGE.getCouleurPourAffichage().getBlue()) * 0.3)
        );
        assertEquals(color, vuePiece.teinte(Couleur.ROUGE.getCouleurPourAffichage()), "Problème lors de la teinte de la pièce");
    }

}
