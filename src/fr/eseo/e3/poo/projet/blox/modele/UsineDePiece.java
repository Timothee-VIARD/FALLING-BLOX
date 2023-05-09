package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.util.Random;

public class UsineDePiece {
    public static final int ALEATOIRE_COMPLET = 0;
    public static final int ALEATOIRE_PIECE = 1;
    public static final int CYCLIC = 2;
    private static int mode;
    private static int index = 0;
    private static Random r = new Random();

    private UsineDePiece() {
    }

    public static void setMode(int mode) {
        UsineDePiece.mode = mode;
    }

    public static Piece genererPiece() {
        switch (mode) {
            case ALEATOIRE_COMPLET:
                return randomPiece(randomCouleur());
            case CYCLIC:
                return piece(index, null, true);
            default:
                return randomPiece(null);
        }
    }

    private static Piece piece(int nb, Couleur couleur, Boolean cyclic) {
        switch (nb) {
            case 0:
                if (Boolean.TRUE.equals(cyclic))
                    UsineDePiece.index++;
                return new OPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.ROUGE));
            default:
                UsineDePiece.index = 0;
                return new IPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.ORANGE));
        }
    }
    private static Couleur choixCouleur(Couleur couleur, Couleur couleurParDefaut){
        return (couleur == null) ? couleurParDefaut : couleur;
    }

    private static Piece randomPiece(Couleur couleur){
        int random = r.nextInt(2);
        return piece(random, couleur, false);
    }

    private static Couleur randomCouleur() {
        int random = r.nextInt(7);
        Couleur couleur;
        switch (random) {
            case 0:
                couleur = Couleur.ROUGE;
                break;
            case 1:
                couleur = Couleur.ORANGE;
                break;
            case 2:
                couleur = Couleur.BLEU;
                break;
            case 3:
                couleur = Couleur.VERT;
                break;
            case 4:
                couleur = Couleur.JAUNE;
                break;
            case 5:
                couleur = Couleur.CYAN;
                break;
            default:
                couleur = Couleur.VIOLET;
                break;
        }
        return couleur;
    }

    public static int getMode() {
        return mode;
    }
}
