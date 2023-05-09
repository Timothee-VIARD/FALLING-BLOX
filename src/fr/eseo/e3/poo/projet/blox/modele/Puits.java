package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Puits {
    public static final int LARGEUR_PAR_DEFAUT = 5;
    public static final int PROFONDEUR_PAR_DEFAUT = 15;

    public static final String MODIFICATION_PIECE_ACTUELLE = "modification piece actuelle";

    public static final String MODIFICATION_PIECE_SUIVANTE = "modification piece suivante";

    private int largeur;
    private int profondeur;

    private Piece pieceActuelle;
    private Piece pieceSuivante;

    private PropertyChangeSupport pcs;

    public Puits() {
        this.largeur = LARGEUR_PAR_DEFAUT;
        this.profondeur = PROFONDEUR_PAR_DEFAUT;
        this.pcs = new PropertyChangeSupport(this);
    }

    public Puits(int largeur, int profondeur) {
        this();
        if(largeur < 5 || largeur > 15){
            throw new IllegalArgumentException("La largeur doit être dans l'intervalle [5, 15]");
        }
        if(profondeur < 15 || profondeur > 25){
            throw new IllegalArgumentException("La profondeur doit être dans l'intervalle [15, 25]");
        }
        this.largeur = largeur;
        this.profondeur = profondeur;
    }

    public Piece getPieceActuelle() {
        return pieceActuelle;
    }

    public Piece getPieceSuivante() {
        return pieceSuivante;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public int getLargeur() {
        return largeur;
    }
    public void setPieceSuivante(Piece piece) {
        if (this.pieceSuivante != null) {
            Piece anciennePieceActuelle = this.pieceActuelle;
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(this.largeur/2, -4);
            this.pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePieceActuelle, this.pieceActuelle);
        }
        Piece anciennePieceSuivante = this.pieceSuivante;
        this.pieceSuivante = piece;
        this.pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, anciennePieceSuivante, this.pieceSuivante);
    }

    public void setProfondeur(int profondeur) {
        if(profondeur < 15 || profondeur > 25){
            throw new IllegalArgumentException("La profondeur doit être dans l'intervalle [15, 25]");
        }
        this.profondeur = profondeur;
    }

    public void setLargeur(int largeur) {
        if(largeur < 5 || largeur > 15){
            throw new IllegalArgumentException("La largeur doit être dans l'intervalle [5, 15]");
        }
        this.largeur = largeur;
    }

    @Override
    public String toString() {
        return "Puits : Dimension " + largeur + " x " + profondeur + "\n" +
                "Piece Actuelle : " + ((pieceActuelle != null)? pieceActuelle.toString(): "<aucune>\n")+
                "Piece Suivante : " + ((pieceSuivante != null)? pieceSuivante.toString(): "<aucune>\n");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
