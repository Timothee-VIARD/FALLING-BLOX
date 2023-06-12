package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.controleur.GamePause;
import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VuePuits extends JPanel implements PropertyChangeListener {
    public static final int TAILLE_PAR_DEFAUT = 15;

    private Puits puits;

    private int taille;

    private VuePiece vuePiece;

    private final VueTas vueTas;

    private PieceDeplacement pieceDeplacement;

    private PieceRotation pieceRotation;

    private GamePause gamePause;

    private Gravite gravite;

    public VuePuits(Puits puits) {
        this(puits, TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits puits, int taille) {
        super();
        this.setPuits(puits);
        this.setTaille(taille);
        setPreferredSize(new Dimension(taille * puits.getLargeur(), taille * puits.getProfondeur()));
        setBackground(java.awt.Color.WHITE);
        this.vuePiece = null;
        this.vueTas = new VueTas(this);
        pieceDeplacement = new PieceDeplacement(this);
        addMouseMotionListener(pieceDeplacement);
        addMouseListener(pieceDeplacement);
        addMouseWheelListener(pieceDeplacement);
        addKeyListener(pieceDeplacement);
        this.pieceRotation = new PieceRotation(this);
        addMouseListener(pieceRotation);
        addKeyListener(pieceRotation);
        this.gamePause = new GamePause(this);
        addKeyListener(this.getGamePause());
        this.setFocusable(true);
        this.puits.setVuePuits(this);
    }

    public Puits getPuits() {
        return puits;
    }

    public int getTaille() {
        return taille;
    }

    public VuePiece getVuePiece() {
        return this.vuePiece;
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
        setPreferredSize(new Dimension(this.taille * puits.getLargeur(), this.taille * puits.getProfondeur()));
        this.puits.addPropertyChangeListener(this);
    }

    public void setTaille(int taille) {
        this.taille = taille;
        setPreferredSize(new Dimension(this.taille * this.puits.getLargeur(), this.taille * this.puits.getProfondeur()));
    }

    private void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, puits.getLargeur()*taille, puits.getProfondeur()*taille);

        g2D.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i<= puits.getLargeur(); i++){
            int x = i * taille;
            g2D.drawLine(x, 0, x, puits.getProfondeur() * taille);
        }
        for (int i = 0; i <= puits.getProfondeur(); i++) {
            int y = i * taille;
            g2D.drawLine(0, y, puits.getLargeur() * taille, y);
        }

        if (this.vuePiece != null){
            this.vuePiece.afficherPiece(g2D);
        }

        this.vueTas.afficher(g2D);
        g2D.dispose();

    }

    public void setGravite(Gravite gravite) {
        this.gravite = gravite;
        this.puits.setGravite(gravite);
    }

    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
            Piece piece = (Piece) event.getNewValue();
            this.setVuePiece(new VuePiece(piece, this.taille));
        }
    }

    public VueTas getVueTas() {
        return vueTas;
    }

    public PieceDeplacement getPieceDeplacement() {
        return pieceDeplacement;
    }

    public PieceRotation getPieceRotation() {
        return pieceRotation;
    }

    public GamePause getGamePause() {
        return gamePause;
    }
}
