package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.Timer;
import java.awt.event.*;

public class Gravite implements ActionListener {

    private Timer timer;

    private final VuePuits vuePuits;

    private final Puits puits;

    public int periodicite = 1000;

    public Gravite(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
        this.timer = new Timer(this.periodicite, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.puits.gravite();
        this.vuePuits.repaint();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public Puits getPuits() {
        return puits;
    }

    public int getPeriodicite() {
        return periodicite;
    }

    public void setPeriodicite(int periodicite) {
        this.periodicite = periodicite;
        this.timer.setDelay(periodicite);
    }

}
