package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.vue.VueMenu;

public class FallingBloxVersion3 {
    private FallingBloxVersion3(String[] args) {
        new VueMenu(args);
    }

    public static void main(String[] args) {
        new FallingBloxVersion3(args);
    }
}
