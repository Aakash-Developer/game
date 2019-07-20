package model;

import javafx.scene.Parent;


/**
 *
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
    public boolean verticalPlacement = true;

    /**
     * This parameter indicates the capacity of getting hit for a placed ship.
     */
    public final int startingLength;    
    private int remainingLength;
    
    /**
     * Constructor of a ship.
     * @param length
     * @param isVertical
     */
    public Ship(int length, boolean isVertical) {
        
        this.startingLength      = length;
        this.verticalPlacement   = isVertical;
        remainingLength          = length;
    }

    public boolean isAlive() {
        return remainingLength > 0;
    }

    public void gotHit() {
        remainingLength--;
    }
}