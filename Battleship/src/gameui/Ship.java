package gameui;

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
     * This parameter indicates the capacity of getting hited for a placed ship.
     */
    public final int capacityType;    
    private int hitCapacityVal;
    
    /**
     * Constructor of a ship.
     * @param type
     * @param isVertical
     */
    public Ship(int type, boolean isVertical) {
        this.capacityType = type;
        this.verticalPlacement = isVertical;
        hitCapacityVal = type;
    }
    /**
     * Method for checking whether a ship is sinked or not.
     * @return 
     */
    public boolean isAlive() {
        return hitCapacityVal > 0;
    }
    
    /**
     * Method for updating the health points of the ship
     */
    public void gotHit() {
        hitCapacityVal--;
    }

    
}