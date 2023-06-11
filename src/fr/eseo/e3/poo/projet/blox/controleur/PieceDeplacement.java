package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PieceDeplacement extends MouseAdapter {

    private Puits puits;
    private VuePuits vuePuits;
    private Coordonnees coordonneesSourisPrev;

    public PieceDeplacement(VuePuits vuePuits) {
        setVuePuits(vuePuits);
        setPuits(vuePuits.getPuits());
    }

    public Puits getPuits() {
        return puits;
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public void setVuePuits(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    public void setCoordonneesSourisPrev(Coordonnees coordonneesSourisPrev) {
        this.coordonneesSourisPrev = coordonneesSourisPrev;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.puits.getPieceActuelle() != null) {
            if (this.coordonneesSourisPrev == null) {
                int posX = e.getX();
                int posY = e.getY();
                this.coordonneesSourisPrev = transformerCoordonnees(posX, posY);
            } else {
                if (!this.coordonneesSourisPrev.equals(transformerCoordonnees(e.getX(), e.getY()))) {
                    int deltaX = transformerCoordonnees(e.getX(), e.getY()).getAbscisse() - this.coordonneesSourisPrev.getAbscisse();
                    int deltaY = 0;
                    try {
                        this.puits.getPieceActuelle().deplacerDe(deltaX, deltaY);
                    } catch (BloxException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.coordonneesSourisPrev = transformerCoordonnees(e.getX(), e.getY());
                }
            }
        }
        vuePuits.repaint();
    }

    private Coordonnees transformerCoordonnees(int posX, int posY) {
        int x = posX / vuePuits.getTaille();
        int y = posY / vuePuits.getTaille();
        return new Coordonnees(x, y);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        this.coordonneesSourisPrev = null;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
        if (this.puits.getPieceActuelle() != null && e.getWheelRotation() > 0) {
            try {
                this.puits.getPieceActuelle().deplacerDe(0, 1);
            } catch (BloxException ex) {
                throw new RuntimeException(ex);
            }
        }
        vuePuits.repaint();
    }
}
