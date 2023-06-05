package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Random;

public class Tas {
    private final Puits puits;
    private final Element[][] elements;

    public Tas(Puits puits) {
        this(puits, 0, 0);
    }

    public Tas(Puits puits, int nbElements) {
        this(puits, nbElements, (nbElements / puits.getLargeur()) + 1);
    }

    public Tas(Puits puits, int nbElements, int nbLignes) {
        this.puits = puits;
        this.elements = new Element[puits.getLargeur()][puits.getProfondeur()];
        Random rand = new Random();
        construireTas(nbElements, nbLignes, rand);
    }

    private void construireTas(int nbElements, int nbLignes, Random rand) {
        if (nbElements != 0 && nbElements >= this.puits.getLargeur() * nbLignes) {
            throw new IllegalArgumentException("Le nombre d'éléments est trop grand");
        } else {
            int nbPlace = 0;
            while (nbElements != nbPlace) {
                int ordon = this.puits.getProfondeur() - (rand.nextInt(nbLignes) + 1);
                int absci = rand.nextInt(this.puits.getLargeur());
                if (this.elements[absci][ordon] == null) {
                    int indiceCouleur = rand.nextInt(Couleur.values().length);
                    this.elements[absci][ordon] =
                            new Element(new Coordonnees(absci, ordon), Couleur.values()[indiceCouleur]);
                    nbPlace++;
                }
            }
        }
    }

    public Puits getPuits() {
        return this.puits;
    }

    public Element[][] getElements() {
        return this.elements;
    }

}
