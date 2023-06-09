package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.JPanel;
import java.awt.*;
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
        // Dans la méthode propertyChanged, vérifier si le changement est un changement de la prochaine
        //pièce, et si c’est le cas de créer une nouvelle VuePiece avec comme Piece, la nouvelle valeur (de
        //le PropertyChangeEvent et comme taille la valeur 10. Stocker cette VuePiece dans une variable
        //d’instance
        if (evt.getPropertyName().equals("prochainePiece")) {
            this.vuePiece = new VuePiece(this.puits.getPieceSuivante(), 10);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(java.awt.Color.LIGHT_GRAY);
        g2D.fillRect(0, 0, getWidth(), getHeight());
        if (this.vuePiece != null) {
            this.vuePiece.afficherPiece(g2D);
        }
    }
}
