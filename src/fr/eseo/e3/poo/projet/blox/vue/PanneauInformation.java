package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanneauInformation extends JPanel implements PropertyChangeListener {

    private Puits puits;

    private VuePiece vuePiece;

    public PanneauInformation(Puits puits) {
        super();
        this.puits = puits;
        this.puits.addPropertyChangeListener(this);
        this.setPreferredSize(new java.awt.Dimension(70, 70));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("modification piece suivante") || (evt.getPropertyName().equals("modification piece actuelle"))) {
            if (this.puits.getPieceSuivante() != null) {
                this.vuePiece = new VuePiece(this.puits.getPieceSuivante(), 10);
            }
        }
        repaint();
    }

    private void afficherScore(Graphics2D g2D) {
        g2D.setColor(Color.BLACK);
        g2D.drawString("Score : " + this.puits.getTas().getScore(), 5, 80);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        if (this.vuePiece != null) {
            this.vuePiece.afficherPiece(g2D);
        }

        afficherScore(g2D);
        g2D.dispose();
    }
}
