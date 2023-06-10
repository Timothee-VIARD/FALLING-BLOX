package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;
import org.junit.jupiter.api.Test;

import javax.swing.Timer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraviteTest {

    @Test
    void testConstructeurGravite(){
        Puits puits = new Puits(10, 20);
        VuePuits vuePuits = new VuePuits(puits);
        Gravite gravite = new Gravite(vuePuits);
        assertEquals(gravite.getPeriodicite(), 1000, "Probleme du constructeur de Gravite");
        assertEquals(gravite.getTimer().isRunning(), true, "Probleme du constructeur de Gravite");
        assertEquals(gravite.getVuePuits(), vuePuits, "Probleme du constructeur de Gravite");
        assertEquals(gravite.getPuits(), puits, "Probleme du constructeur de Gravite");
    }

    @Test
    void testSetterTimer(){
        Puits puits = new Puits(10, 20);
        VuePuits vuePuits = new VuePuits(puits);
        Gravite gravite = new Gravite(vuePuits);
        Timer timer = new Timer(5000, gravite);
        gravite.setTimer(timer);
        assertEquals(gravite.getTimer(), timer, "Probleme du setter de Timer");
    }

    @Test
    void testSetterPeriodicite(){
        Puits puits = new Puits(10, 20);
        VuePuits vuePuits = new VuePuits(puits);
        Gravite gravite = new Gravite(vuePuits);
        gravite.setPeriodicite(5000);
        assertEquals(gravite.getPeriodicite(), 5000, "Probleme du setter de Periodicite");
        assertEquals(gravite.getTimer().getDelay(), 5000, "Probleme du setter de Periodicite");
    }
}
