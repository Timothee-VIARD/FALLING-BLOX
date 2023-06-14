package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class PieceRotation extends MouseAdapter {
    private Puits puits;
    private VuePuits vuePuits;

    public PieceRotation(VuePuits vuePuits) {
        setVuePuits(vuePuits);
        setPuits(vuePuits.getPuits());
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public Puits getPuits() {
        return puits;
    }

    public void setVuePuits(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.puits.getPieceActuelle() != null) {
            if (isLeftMouseButton(e)) {
                try {
                    this.puits.getPieceActuelle().tourner(false);
                } catch (Exception ignored) {}
            } else if (isRightMouseButton(e)) {
                try {
                    this.puits.getPieceActuelle().tourner(true);
                } catch (Exception ignored) {}
            }
        }
        vuePuits.repaint();
    }
}
