package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
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

    public void deplacerDe(int deltaX, int deltaY) throws IllegalArgumentException, BloxException {
        if (deltaY < 0 || deltaY > 1 || deltaX < -1 || deltaX > 1) {
            throw new IllegalArgumentException("Le déplacement ne peut se faire que vers le bas, la droite et la gauche");
        }

        List<Element> elementsCopies = copieElements();

        if (getPuits() != null) {

            // Déplacer les éléments
            elementsCopies = deplacerElements(deltaX, deltaY, copieElements());

            // Verifier la sortie du puits
            testSortie(elementsCopies);

            // Vérifier la collision avec le fond du puits
            testCollisionFond(elementsCopies);

            // Vérifier la collision avec les éléments du tas
            testCollisionDeplacement(elementsCopies);
        }

        // Déplacer les éléments
        deplacerElements(deltaX, deltaY, this.getElements());
    }

    private List<Element> deplacerElements(int deltaX, int deltaY, List<Element> elementsToMove){
        for (Element element : elementsToMove) {
            element.deplacerDe(deltaX, deltaY);
        }
        return elementsToMove;
    }

    private List<Element> copieElements() {
        List<Element> elementsCopies = new ArrayList<>();

        for (Element element : this.elements) {
            Element elementCopie = new Element(element.getCoordonnees().getAbscisse(), element.getCoordonnees().getOrdonnee());

            elementsCopies.add(elementCopie);
        }

        return elementsCopies;
    }

    private void testSortie(List<Element> elementsCopies) throws BloxException {

        for (Element element : elementsCopies) {
            if (element.getCoordonnees().getAbscisse() >= this.puits.getLargeur()) {
                throw new BloxException("Le déplacement ne peut se faire en dehors du puits", BloxException.BLOX_SORTIE_PUITS);
            }
            if (element.getCoordonnees().getAbscisse() < 0) {
                throw new BloxException("Le déplacement ne peut se faire en dehors du puits", BloxException.BLOX_SORTIE_PUITS);
            }
        }
    }

    private void testCollisionFond(List<Element> elementsCopies) throws BloxException {
        for (Element element : elementsCopies) {
            if (element.getCoordonnees().getOrdonnee() >= this.puits.getProfondeur()) {
                throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
            }
        }
    }

    private void testCollisionDeplacement(List<Element> elementsCopies) throws BloxException {
        for (Element element : elementsCopies) {
            for (Element[] ligne : this.puits.getTas().getElements()) {
                for (Element tasElement : ligne) {
                    if (tasElement != null && tasElement.getCoordonnees().equals(element.getCoordonnees())) {
                        throw new BloxException("Collision avec un élément du tas", BloxException.BLOX_COLLISION);
                    }
                }
            }
        }
    }

    public void tourner(boolean sensHoraire) throws BloxException {
        // Créer une copie des éléments pour simuler la rotation
        List<Element> elementsCopies = copieElements();
        List<Element> rotatedElements = rotation(sensHoraire, elementsCopies);

        if (getPuits() != null) {
            // Vérifie les collisions avec les éléments du tas
            testCollisionRotation(rotatedElements);

            // Vérifie la sortie du puits
            testSortiePuits(rotatedElements);
        }

        // Met à jour les éléments avec les positions après rotation
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).setCoordonnees(rotatedElements.get(i).getCoordonnees());
        }
    }

    private List<Element> rotation(boolean sensHoraire, List<Element> elementsToRotate) {
        // Pivot de la rotation
        Element pivot = elementsToRotate.get(0);
        int pivotX = pivot.getCoordonnees().getAbscisse();
        int pivotY = pivot.getCoordonnees().getOrdonnee();

        // Calcul des cosinus et sinus de l'angle de rotation
        int sign = sensHoraire ? 1 : -1;

        double cos = Math.cos(sign * Math.PI / 2);
        double sin = Math.sin(sign * Math.PI / 2);

        List<Element> rotatedElements = new ArrayList<>();
        for (Element element : elementsToRotate) {
            // Transforme les coordonnées de l'élément par rapport au pivot
            int x = element.getCoordonnees().getAbscisse() - pivotX;
            int y = element.getCoordonnees().getOrdonnee() - pivotY;

            // Applique la rotation
            int rotatedX = (int) Math.round(x * cos - y * sin);
            int rotatedY = (int) Math.round(x * sin + y * cos);

            // Retransforme les coordonnées de l'élément par rapport au pivot et crée un nouvel élément
            int newAbscisse = rotatedX + pivotX;
            int newOrdonnee = rotatedY + pivotY;
            rotatedElements.add(new Element(new Coordonnees(newAbscisse, newOrdonnee), element.getCouleur()));
        }
        return rotatedElements;
    }

    private void testSortiePuits(List<Element> rotatedElements) throws BloxException {
        for (Element element : rotatedElements) {
            if (element.getCoordonnees().getAbscisse() >= this.puits.getLargeur()) {
                throw new BloxException("La rotation ne peut se faire en dehors du puits", BloxException.BLOX_SORTIE_PUITS);
            }
            if (element.getCoordonnees().getAbscisse() < 0) {
                throw new BloxException("La rotation ne peut se faire en dehors du puits", BloxException.BLOX_SORTIE_PUITS);
            }
            if (element.getCoordonnees().getOrdonnee() >= this.puits.getProfondeur()) {
                throw new BloxException("La rotation ne peut se faire contre le fond du puits", BloxException.BLOX_COLLISION);
            }
        }
    }

    private void testCollisionRotation(List<Element> rotatedElements) throws BloxException {
        for (Element[] ligne : this.puits.getTas().getElements()) {
            for (Element element : ligne) {
                if (element != null) {
                    for (Element rotatedElement : rotatedElements) {
                        if (element.getCoordonnees().equals(rotatedElement.getCoordonnees())) {
                            throw new BloxException("Collision avec un élément du tas", BloxException.BLOX_COLLISION);
                        }
                    }
                }
            }
        }
    }

}
