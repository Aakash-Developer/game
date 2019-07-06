package gameui;

import javafx.scene.Parent;

/*
 * @author Team 4
 * @Zbigniew Ivan Angelus
 * @Chen-Fang Chung
 * @Ayush Dave
 * @Aakash Ahuja
 * @Pulkit Wadhwa
 */

public class Ship extends Parent {
    
    public boolean verticalPlacement = true;
    public final int capacityType;    
    private int hitCapacityVal;
    
    public Ship(int type, boolean isVertical) {
        this.capacityType = type;
        this.verticalPlacement = isVertical;
        hitCapacityVal = type;
    }
    /**
     * method for checking whether a ship is sinked or not.
     * @return 
     */
    public boolean isAlive() {
        return hitCapacityVal > 0;
    }
    
    /**
     * method for updating the health points of the ship
     */
    public void gotHit() {
        hitCapacityVal--;
    }

    
}