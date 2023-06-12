package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion2;
import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.vue.VueMenu;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

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
    private Tas tas;
    private Gravite gravite;
    private VuePuits vuePuits;

    public Puits() {
        this(LARGEUR_PAR_DEFAUT, PROFONDEUR_PAR_DEFAUT, 0, 0);
    }

    public Puits(int largeur, int profondeur) {
        this(largeur, profondeur, 0, 0);
    }

    public Puits(int largeur, int profondeur, int nbElements, int nbLignes) {
        if(largeur < 5 || largeur > 15){
            throw new IllegalArgumentException("La largeur doit être dans l'intervalle [5, 15]");
        }
        if(profondeur < 15 || profondeur > 25){
            throw new IllegalArgumentException("La profondeur doit être dans l'intervalle [15, 25]");
        }
        setLargeur(largeur);
        setProfondeur(profondeur);
        this.pcs = new PropertyChangeSupport(this);
        this.tas = new Tas(this, nbElements, nbLignes);
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
        this.pieceSuivante.setPuits(this);
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

    public PropertyChangeSupport getPcs() {
        return pcs;
    }


    public Tas getTas() {
        return tas;
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }

    private void gererCollision() {
        try {
            this.tas.ajouterElements(this.pieceActuelle);
        } catch (IllegalArgumentException e) {
            gererEndGame();
        }
        this.setPieceSuivante(UsineDePiece.genererPiece());
    }

    public void gravite(){
        if(this.pieceActuelle != null){
            try {
                this.pieceActuelle.deplacerDe(0, 1);
            } catch (BloxException e) {
                if (e.getType() == BloxException.BLOX_COLLISION) {
                    gererCollision();
                }
            }
        }
    }

    public Gravite getGravite() {
        return gravite;
    }

    public void setGravite(Gravite gravite) {
        this.gravite = gravite;
    }

    private void gererEndGame() {
        new VueMenu(new String[0]);
        FallingBloxVersion2.getFrame().dispose();
        this.getVuePuits().getVueTas().getTas().resetTas();
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public void setVuePuits(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }
}
