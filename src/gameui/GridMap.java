package gameui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.Validate;
import model.Ship;

/**
 * GridMap class creates the grid map for players.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */
public class GridMap extends Parent {
    
    private final VBox gridMatrix      = new VBox(); // default setting for placing ship is vertically!
    private boolean isEnemyMap      = false;  // if GirdMap belonged enymy's side, this flag is true
    private int gridLength            = 0;

    public int shipsNumOnMap        = 5;    
    public boolean[] salvoMoveCheck = {false,false,false,false,false};
    public int moveCounter          = 0;
    public boolean finishIni        = false;
    
    // Constructor of GridMap

    /**
     *
     * @param isEnemyMap
     * @param handler
     * @param length
     */
    public GridMap(boolean isEnemyMap, 
                   EventHandler<? super MouseEvent> handler, 
                   int length) 
    {
        this.isEnemyMap     = isEnemyMap;
        this.gridLength     = length;
 
        for (int pos_y = 0; pos_y < gridLength; pos_y++) {
            
            HBox row = new HBox();
            
            for (int pos_x = 0; pos_x < gridLength; pos_x++) {
                
                GridBox gridBoxObj = new GridBox(this, pos_x, pos_y);
                gridBoxObj.setOnMouseClicked(handler);
                row.getChildren().add(gridBoxObj);
            }
            
            gridMatrix.getChildren().add(row);
        }
        getChildren().add(gridMatrix);
    }
 
    /**
     * method for placing the ship
     * @param ship
     * @param pos_x
     * @param pos_y
     * @return 
     */
    public boolean placingBattleShipOn_X_Y(Ship ship, int pos_x, int pos_y) {
        
        if (doesTheShipFitsInAtThisPosition(pos_x, pos_y, ship)) {
            
            int length = ship.startingLength;

            if (ship.verticalPlacement) { // Vertically place the ship for the player
                for (int i = pos_y; i < pos_y + length; i++) {
                    GridBox grigBoxesOfOneShip = getGridBoxByCoordinate(pos_x, i);
                    grigBoxesOfOneShip.ship = ship;
                    if (!isEnemyMap) {
                        grigBoxesOfOneShip.setFill(Color.WHITE);
                        grigBoxesOfOneShip.setStroke(Color.BLUE);
                    }
                }
            }
            else {
                // Horizontally place the ship for the player
                for (int i = pos_x; i < (pos_x + length); i++) {
                    GridBox grigBoxesOfOneShip = getGridBoxByCoordinate(i, pos_y);
                    grigBoxesOfOneShip.ship = ship;
                    if (!isEnemyMap) {
                        grigBoxesOfOneShip.setFill(Color.WHITE);
                        grigBoxesOfOneShip.setStroke(Color.BLUE);
                    }
                }
            }

            return true;
        }

        return false;
    }
    
    /**
     * method for validating the proper position to place a ship
     * @param pos_x
     * @param pos_y
     * @return 
     */
    public GridBox getGridBoxByCoordinate(int pos_x, int pos_y) {
        return (GridBox)((HBox)gridMatrix.getChildren().get(pos_y)).getChildren().get(pos_x);
    }
    
    /**
     * method for getting the neighbor boxes of a ship
     */
    private GridBox[] getSurroundingBoxes(int pos_x, int pos_y) {
        // only checking for the up, down, left, and right boxes!!
        Point2D[] neighborPointsArr = new Point2D[] {
                new Point2D(pos_x - 1, pos_y),
                new Point2D(pos_x + 1, pos_y),
                new Point2D(pos_x, pos_y - 1),
                new Point2D(pos_x, pos_y + 1)
        };

        List<GridBox> neighborBoxes = new ArrayList<>();
        
        // if there is a valid box, place it in an arrayList.
        for (Point2D neighborPoint : neighborPointsArr) {
            if (isValidPosition(neighborPoint)) {
                neighborBoxes.add(getGridBoxByCoordinate((int)neighborPoint.getX(), (int)neighborPoint.getY()));
            }
        }
        // return the valid boxes as an array.
        return neighborBoxes.toArray(new GridBox[0]);
    }
    
    /**
     * method for validating the proper position to place a ship
     */
    

    private boolean doesTheShipFitsInAtThisPosition(int x, int y, Ship ship) {
        int length = ship.startingLength;
        // Check if vertically placing the ships is ok ?!
        if (ship.verticalPlacement) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPosition(x, i))
                    return false;

                GridBox selectedBox = getGridBoxByCoordinate(x, i);
                // if this box belongs to a ship, then it is invalid!
                if (selectedBox.ship != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(x, i)) {
                    if (!isValidPosition(x, i))
                        return false;
                    // rechack again!!
                    if (neighborBox.ship != null)
                        return false;
                }
            }
        }
        else { // Check if horizontally placing the ships is ok ?!
            for (int i = x; i < x + length; i++) {
                if (!isValidPosition(i, y))
                    return false;
                // if this box belongs to a ship, then it is invalid!
                GridBox singleGridBox = getGridBoxByCoordinate(i, y);
                if (singleGridBox.ship != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(i, y)) {
                    if (!isValidPosition(i, y))
                        return false;
                    // rechack again!!
                    if (neighborBox.ship != null)
                        return false;
                }
            }
        }

        return true;
    }

    private boolean isValidPosition(Point2D point) {
        // checking if a coordinate is valid?!
        return isValidPosition(point.getX(), point.getY());
    }

    private boolean isValidPosition(double x, double y) {
        // checking if a coordinate is valid?!
        return x >= 0 && x < gridLength && y >= 0 && y < gridLength;
    }
     
    /**
     * An inner GridBox class for a single hitting unit object
     */
    public class GridBox extends Rectangle {
        
        public int pos_x, pos_y;
        public Ship ship = null;
        public boolean isHitted = false;
        private final GridMap gridMap;

        /**
         *
         * @param owner
         * @param input_x
         * @param input_y
         */
        public GridBox(GridMap owner, int input_x, int input_y) {
            
            super(20, 20);
            
            this.pos_x = input_x;
            this.pos_y = input_y;
            
            this.gridMap = owner;
            
            if (!isEnemyMap) {
                setFill(Color.CADETBLUE);// initially set the box to blue color
                setStroke(Color.BLACK); // if a box was selected as hitted, set it to black color.
            }
            else{
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
}
