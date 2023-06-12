package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.vue.VuePause;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.*;

public class GamePause implements KeyListener {
    private VuePuits vuePuits;
    private boolean pause = false;

    public GamePause(VuePuits vuePuits) {
        this.vuePuits = vuePuits;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.pauseHandler();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void pauseHandler() {
        this.setPause(!this.isPause());
        if (this.isPause()) {
            this.getVuePuits().removeMouseMotionListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().removeMouseListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().removeMouseWheelListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().removeKeyListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().removeMouseListener(this.getVuePuits().getPieceRotation());
            this.getVuePuits().removeKeyListener(this.getVuePuits().getPieceRotation());
            this.getVuePuits().getPuits().getGravite().getTimer().stop();
            new VuePause(this.getVuePuits());
        }
        else{
            this.getVuePuits().addMouseMotionListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().addMouseListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().addMouseWheelListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().addKeyListener(this.getVuePuits().getPieceDeplacement());
            this.getVuePuits().addMouseListener(this.getVuePuits().getPieceRotation());
            this.getVuePuits().addKeyListener(this.getVuePuits().getPieceRotation());
            this.getVuePuits().getPuits().getGravite().getTimer().start();
        }
    }

    public VuePuits getVuePuits() {
        return vuePuits;
    }

    public void setVuePuits(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
