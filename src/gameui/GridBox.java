
package gameui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

    /**
     * One of the "Model" module.
     * An inner GridBox class for a single hitting unit object
     */
    public class GridBox extends Rectangle {

        /**
         *
         */
        public int pos_x,

        /**
         *
         */
        pos_y;

        /**
         *
         */
        public Ship ship = null;

        /**
         *
         */
        public boolean isHitted = false;

        private final GridMap gridMap;
        
        /**
         *
         * @param mapObj
         * @param input_x
         * @param input_y
         */
        public GridBox(GridMap mapObj, int input_x, int input_y,boolean player2Flag) {
            super(20, 20);
            this.pos_x = input_x;
            this.pos_y = input_y;
            this.gridMap = mapObj;
            if (!player2Flag) {
                // initially set the box to blue color
                setFill(Color.CADETBLUE);
                // if a box was selected as hitted, set it to black color.
                setStroke(Color.BLACK);
            }else{
                setFill(Color.BLUE);
                setStroke(Color.BLACK);
            }
        }
        
        /**
        * method for controlling the behavior of a GridBox
        * Hit a single box. if the box belongs to a ship, turn red, otherwise turn black.
         * @return 
        */ 
        public boolean hitGridBox() {
            isHitted = true;
            // if a box was selected as hitted, set it to black color.
            setFill(Color.BLACK);
            // if a box belongs to a ship and was hitted, set it to red color.
            if (ship != null) {
                ship.gotHit();
                setFill(Color.RED);
                // check if the ship still has the capacity to take the hitting.
                if (!ship.isAlive()) {
                    // if the ship is sinked, reduce the number of ship on the map.
                    gridMap.shipsNumOnMap--;
                }
                return true;
            }

            return false;
        }
    }