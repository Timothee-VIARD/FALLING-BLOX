package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private List<Element> elements = new ArrayList<>();

    private Puits puits;

    public Piece(Coordonnees coordonnees, Couleur couleur) {
        this.setElements(coordonnees, couleur);
    }

    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);

    public List<Element> getElements() {
        return this.elements;
    }

    public void setPosition(int abscisse, int ordonnee) {
        this.setElements(new Coordonnees(abscisse, ordonnee), this.getElements().get(0).getCouleur());
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        for (Element element : this.getElements()) {
            str.append("\t").append(element.getCoordonnees()).append(" - ").append(element.getCouleur()).append("\n");
        }
        return this.getClass().getSimpleName() + " :" + "\n" + str;
    }

    public Puits getPuits() {
        return this.puits;
    }

    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    public void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException{
        if (deltaY < 0 || deltaY > 1 || deltaX < -1 || deltaX > 1 ) {
            throw new IllegalArgumentException("Le déplacement ne peut se faire que vers le bas, la droite et la gauche");
        } else{
            for (Element element : this.getElements()) {
                element.deplacerDe(deltaX, deltaY);
            }
        }

    }

    public void tourner(boolean sensHoraire) {
        // Get the pivot
        Element pivot = this.elements.get(0);
        int pivotX = pivot.getCoordonnees().getAbscisse();
        int pivotY = pivot.getCoordonnees().getOrdonnee();

        // Calculate sin and cos based on rotation direction
        int sign = sensHoraire ? 1 : -1;
        double cos = Math.cos(sign * Math.PI / 2);
        double sin = Math.sin(sign * Math.PI / 2);


        // Iterate over all elements and rotate them around the pivot
        for (Element element : elements) {
            // Translate element coordinates to origin
            int x = element.getCoordonnees().getAbscisse() - pivotX;
            int y = element.getCoordonnees().getOrdonnee() - pivotY;

            // Apply rotation transformation
            int rotatedX = (int) Math.round(x * cos - y * sin);
            int rotatedY = (int) Math.round(x * sin + y * cos);

            // Translate back to original coordinates and update element
            element.setCoordonnees(new Coordonnees(rotatedX + pivotX, rotatedY + pivotY));
        }
    }
}
