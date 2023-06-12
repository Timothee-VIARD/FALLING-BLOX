package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Tas;

import java.awt.Color;
import java.awt.Graphics2D;

public class VueTas {

    public static final double MULTIPLIER_NUANCE = 0.4;

    private final Tas tas;

    private final VuePuits vuePuits;

    public VueTas(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.tas = vuePuits.getPuits().getTas();
    }

    public Color nuance(Color color) {
        return new Color(
                (int) (color.getRed() * (1 - MULTIPLIER_NUANCE)),
                (int) (color.getGreen() * (1 - MULTIPLIER_NUANCE)),
                (int) (color.getBlue() * (1 - MULTIPLIER_NUANCE))
        );
    }

    public void afficher(Graphics2D g2D) {
        for (int i = 0; i < tas.getElements().length; i++) {
            for (int j = 0; j < tas.getElements()[i].length; j++) {
                if (tas.getElements()[i][j] != null) {
                    g2D.setColor(nuance(tas.getElements()[i][j].getCouleur().getCouleurPourAffichage()));
                    g2D.fill3DRect(j * vuePuits.getTaille(), i * vuePuits.getTaille(), vuePuits.getTaille(), vuePuits.getTaille(), true);
                }
            }
        }

    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public Tas getTas() {
        return tas;
    }
}
