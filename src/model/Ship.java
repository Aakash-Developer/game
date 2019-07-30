package model;

import javafx.scene.Parent;


/**
 * It represents the ship in the game.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */
public class Ship extends Parent {
    
    /**
     *  This parameter indicates the direction of a placed ship.
     *  True for vertical placement, false for horizontal placement.
     */
    public boolean isVertical = true;

    /**
     * This parameter indicates the capacity of getting hit for a placed ship.
     */
    public final int length;    
    private int remainingLength;
    
    /**
     * Constructor of a ship.
     * @param length ,integer
     * @param isVertical , boolean
     */
    public Ship(int length, boolean isVertical) {
        
        this.length      = length;
        this.isVertical   = isVertical;
        remainingLength          = length;
    }

    public boolean isAlive() {
        return remainingLength > 0;
    }

    public void gotHit() {
        remainingLength--;
    }
}