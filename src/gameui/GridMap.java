package gameui;

import api.Constant;
import api.Constant.Space;
import api.Constant.Uncover;
import gameengine.Controller;
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
import model.MapModel;
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
    
    private final VBox gridMatrix   = new VBox(); // default setting for placing ship is vertically!
    private boolean isEnemyMap      = false;  // if GirdMap belonged enymy's side, this flag is true
    public int shipsNumOnMap        = 5;    
    public boolean[] salvoMoveCheck = {false,false,false,false,false};
    public int moveCounter          = 0;
    public boolean finishIni        = false;
    public MapModel[][] mapModel;
    public Controller controller;
    /**
     * It creates the maps of the main and enemy players.
     * @param mapModel mapModel of the current player
     * @param isEnemyMap indicate which map is to be construct
     * @param handler is the handler between controller and view to pass data
     */
    public GridMap(Controller injectedController,MapModel[][] mapModel, boolean isEnemyMap, 
                   EventHandler<? super MouseEvent> handler) 
    {
        this.isEnemyMap = isEnemyMap;
        this.mapModel   = mapModel;
        this.controller = injectedController;       
        for (int pos_y = 0; pos_y < Constant.GRID_SIZE; pos_y++) { // create view matrix
            
            HBox row = new HBox();
            
            for (int pos_x = 0; pos_x < Constant.GRID_SIZE; pos_x++) {
                
                GridBox gridBoxObj = new GridBox(this, pos_x, pos_y);
                gridBoxObj.setOnMouseClicked(handler);
                row.getChildren().add(gridBoxObj);
            }
            
            gridMatrix.getChildren().add(row);
        }
        getChildren().add(gridMatrix);
    }
 
    /**
     * It tries to place a ship given some coordinates
     * @param ship is the instance of the ship class
     * @param pos_x coordinate x
     * @param pos_y coordinate y
     * @return boolean True if successful. False otherwise.
     */
    public boolean TryToPlaceShipOnMap(Ship ship, int pos_x, int pos_y) {
        
        if (doesTheShipFitsAtThisPosition(ship , pos_x, pos_y)) {
            
            int length = ship.length;

            if (ship.isVertical) 
            {
                for (int i = pos_y; i < pos_y + length; i++) {
                    
                    this.mapModel[pos_x][i].SetAShip(ship);

                    GridBox rectangle = getGridBoxByCoordinate(pos_x, i);
                    rectangle.shipInstance = ship;
                    
                    if (!isEnemyMap) {
                        
                        rectangle.setFill(Color.WHITE);
                        rectangle.setStroke(Color.BLUE);
                    }
                }
            }
            else {
                for (int i = pos_x; i < (pos_x + length); i++) 
                {
                    this.mapModel[i][pos_y].SetAShip(ship);
                    
                    GridBox rectangle = getGridBoxByCoordinate(i, pos_y);
                    rectangle.shipInstance = ship;
                    
                    if (!isEnemyMap) {
                        rectangle.setFill(Color.WHITE);
                        rectangle.setStroke(Color.BLUE);
                    }
                }
            }
            String shipDeployInfo = pos_x+" "+pos_y+" "+ship.isVertical;
            controller.selfShipDeployInfo = controller.selfShipDeployInfo+"-"+shipDeployInfo;
            return true;
        }

        return false;
    }
    
     /**
     * It tries to place a ship in the model given some coordinates
     * @param ship is the instance of the ship class
     * @param pos_x coordinate x
     * @param pos_y coordinate y
     * @return  boolean True if successful. False otherwise
     */
    public boolean TryToPlaceShipOnModel(Ship ship, int pos_x, int pos_y) {
        
        boolean succesfull = false;
        
        applyModelToView();
        
        if (doesTheShipFitsAtThisPosition(ship , pos_x, pos_y)) {
            
            int length = ship.length;

            if (ship.isVertical) 
            {
                for (int i = pos_y; i < pos_y + length; i++) {
                    
                    this.mapModel[pos_x][i].SetAShip(ship);
                }
            }
            else {
                for (int i = pos_x; i < (pos_x + length); i++) 
                {
                    this.mapModel[i][pos_y].SetAShip(ship);
                }
            }
            
            succesfull = true;
        }

        return succesfull;
    }
    
    
    /**
     * Given a map model, it applies the model to the view
     */
    public void applyModelToView(){
        
        for(int x = 0 ; x< Constant.GRID_SIZE ; x++){
            for(int y = 0 ; y< Constant.GRID_SIZE ; y++){
                
                if(this.mapModel[x][y].space == Space.IsShip){
                    
                    GridBox rectangle = getGridBoxByCoordinate(x,y);
                    rectangle.shipInstance = this.mapModel[x][y].ShipInstance;

                    if (!isEnemyMap) {
                        rectangle.setFill(Color.WHITE);
                        rectangle.setStroke(Color.BLUE);
                    }    
                }
            }   
        }
    }
    
    /**
     * It gets the box of the map given some coordinates
     * @param pos_x coordinate x
     * @param pos_y coordinate y
     * @return GridBox The instance that contains the data of a box in the player's view
     */
    public GridBox getGridBoxByCoordinate(int pos_x, int pos_y) {
        return (GridBox)((HBox)gridMatrix.getChildren().get(pos_y)).getChildren().get(pos_x);
    }
    
    /**
     * it gets the neighbor boxes of a ship given some coordinates
     * @return GridBox[] A collection of surrounding boxed
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
     * it validates the proper position to place a ship
     * @return boolean If the ship is in a valid position.
     */
    private boolean isValidShipPosition(Ship ship , MapModel[][] model, int x, int y) {
        int length = ship.length;
        // Check if vertically placing the ships is ok ?!
        if (ship.isVertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPosition(x, i))
                    return false;

                //GridBox selectedBox = getGridBoxByCoordinate(x, i);
                
                // if this box belongs to a ship, then it is invalid!
                if (model[x][i].ShipInstance != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(x, i)) {
                    if (!isValidPosition(x, i))
                        return false;
                    // rechack again!!
                    if (neighborBox.shipInstance != null)
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
                if (singleGridBox.shipInstance != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(i, y)) {
                    if (!isValidPosition(i, y))
                        return false;
                    // rechack again!!
                    if (neighborBox.shipInstance != null)
                        return false;
                }
            }
        }

        return true;
    }

    
    /**
     * 
     * @param ship is the object of interest to be placed
     * @param x coordinate x
     * @param y coordinate y
     * @return boolean True if the ship fits in the position. False otherwise
     */
    private boolean doesTheShipFitsAtThisPosition(Ship ship , int x, int y) {
        int length = ship.length;
        // Check if vertically placing the ships is ok ?!
        if (ship.isVertical) {
            for (int i = y; i < y + length; i++) {
                if (!isValidPosition(x, i))
                    return false;

                GridBox selectedBox = getGridBoxByCoordinate(x, i);
                // if this box belongs to a ship, then it is invalid!
                if (selectedBox.shipInstance != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(x, i)) {
                    if (!isValidPosition(x, i))
                        return false;
                    // rechack again!!
                    if (neighborBox.shipInstance != null)
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
                if (singleGridBox.shipInstance != null)
                    return false;
                // if this neighborBox is at invalid position, then it is invalid!
                for (GridBox neighborBox : getSurroundingBoxes(i, y)) {
                    if (!isValidPosition(i, y))
                        return false;
                    // rechack again!!
                    if (neighborBox.shipInstance != null)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * It calculate a valid position given a 2d point
     * @param point Is a coordinate
     * @return Point2d is a coordinate
     */
    private boolean isValidPosition(Point2D point) {
        // checking if a coordinate is valid?!
        return isValidPosition(point.getX(), point.getY());
    }

    /**
     * It calculate a valid position given a 2d point
     * @param x is the x coordinate
     * @param y is teh y coordinate
     * @return boolean True it is a valid position. False otherwise
     */
    private boolean isValidPosition(double x, double y) {
        // checking if a coordinate is valid?!
        return x >= 0 && x < Constant.GRID_SIZE && y >= 0 && y < Constant.GRID_SIZE;
    }
     
    /**
     * An inner GridBox class for a single hitting unit object
     * It represent the basic using of the map that a user
     * can click on.
     */
    public class GridBox extends Rectangle {
        
        public int pos_x, pos_y;
        public Ship shipInstance = null;
        public boolean isHitted = false;
        private final GridMap gridMap;

        /**
         *
         * @param owner is the player's map
         * @param input_x coordinate x
         * @param input_y coordinate y
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
        * It controls the behavior of a GridBox
        * Hit a single box. if the box belongs to a ship, turn red, otherwise 
        * turn black.
        * @return boolean True if there was a ship. False otherwise.
        */ 
        
        public boolean hitGridBox(){

            isHitted = true;
            return DetermineShipWasHit();
        }
        
        
        /**
         * It determine if a ship belong to the cell being clicked
         * @return boolean True if there is a ship. False otherwise.
         */
        public boolean DetermineShipWasHit(){
            
            boolean isHit = false;
            
            MapModel cell = GetUpdatedMapModel();
            
            if(cell.IsShipFound()){
                
                Ship ship = cell.ShipInstance;
                
                if(ship != null)
                {
                    ship.gotHit();
                    setFill(Color.RED);
                    
                    if (!ship.isAlive()) {
                        gridMap.shipsNumOnMap--;
                    }
                    
                    isHit = true;
                }
            }
            else
            {
                this.setFill(Color.BLACK);
            }
            
            return isHit;
        }
        
        /**
         * It updates the current view in the current coordinate
         * given the current model of the map.
         * @return MapModel The model of the current map
         */
        public MapModel GetUpdatedMapModel(){
            
            this.gridMap.mapModel[this.pos_x ][this.pos_y].uncover = Uncover.Yes;
            return this.gridMap.mapModel[this.pos_x][this.pos_y];
        }
        
        /*
        public boolean hitGridBox() {
            isHitted = true;
            // if a box was selected as hitted, set it to black color.
            setFill(Color.BLACK);
            // if a box belongs to a ship and was hitted, set it to red color.
            if (shipInstance != null) {
                shipInstance.gotHit();
                setFill(Color.RED);
                // check if the ship still has the capacity to take the hitting.
                if (!shipInstance.isAlive()) {
                    // if the ship is sinked, reduce the number of ship on the map.
                    gridMap.shipsNumOnMap--;
                }
                return true;
            }

            return false;
        }
        */
    }
}
