package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class IPiece extends Piece{
    public IPiece(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {
        this.getElements().clear();
        this.getElements().add(
                new Element(coordonnees, couleur));
        this.getElements().add(
                new Element(
                        new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() + 1), couleur));
        this.getElements().add(
                new Element(
                        new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1), couleur));
        this.getElements().add(
                new Element(new Coordonnees(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 2), couleur));
    }

    @Override
    public String toString() {
        return "IPiece :" + "\n" +
                "\t" + this.getElements().get(0).getCoordonnees() + " - " + this.getElements().get(0).getCouleur() + "\n" +
                "\t" + this.getElements().get(1).getCoordonnees() + " - " + this.getElements().get(0).getCouleur() + "\n" +
                "\t" + this.getElements().get(2).getCoordonnees() + " - " + this.getElements().get(0).getCouleur() + "\n" +
                "\t" + this.getElements().get(3).getCoordonnees() + " - " + this.getElements().get(0).getCouleur() + "\n";
    }
}
