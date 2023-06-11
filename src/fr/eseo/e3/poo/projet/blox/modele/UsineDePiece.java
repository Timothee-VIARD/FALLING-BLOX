package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.IPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.JPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.LPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.OPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.SPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.TPiece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.ZPiece;

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
        Piece piece;
        switch (nb) {
            case 0:
                usineIncrement(cyclic);
                piece = new OPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.ROUGE));
                break;
            case 1:
                usineIncrement(cyclic);
                piece = new IPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.ORANGE));
                break;
            case 2:
                usineIncrement(cyclic);
                piece = new TPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.BLEU));
                break;
            case 3:
                usineIncrement(cyclic);
                piece = new LPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.VERT));
                break;
            case 4:
                usineIncrement(cyclic);
                piece = new JPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.JAUNE));
                break;
            case 5:
                usineIncrement(cyclic);
                piece = new ZPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.CYAN));
                break;
            default:
                UsineDePiece.index = 0;
                piece = new SPiece(new Coordonnees(2, 3), choixCouleur(couleur, Couleur.VIOLET));
        }
        return piece;
    }

    private static void usineIncrement(Boolean cyclic){
        if (Boolean.TRUE.equals(cyclic))
            UsineDePiece.index++;
    }
    private static Couleur choixCouleur(Couleur couleur, Couleur couleurParDefaut){
        return (couleur == null) ? couleurParDefaut : couleur;
    }

    private static Piece randomPiece(Couleur couleur){
        int random = r.nextInt(7);
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
