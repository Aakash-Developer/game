package gameengine;

import api.Constant;
import api.IPlayer;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import javafx.scene.input.MouseButton;
import model.MapModel;
import model.Ship;
import utils.PrettyPrint;
import utils.Tuple;

/**
 * This class is "only" a helper of the controller 
 * to manage the MODEL and VIEW of a player in a single class
 * 
 * This class is not a MODEL nor a VIEW but it contains both.
 * 
 * @author zange
 */
public class PlayerMap implements IPlayer{

    private final int totalShips    = Constant.totalShips;    
    public int iniShipsToPlace      = totalShips;
    
    private Controller controller;
    public final int[] ShipsSizeOrderedList = Constant.SHIPS_SIZE;
    public MapModel[][] mapModel;
    public GridMap mapView;
    
    public PlayerMap(Controller injectedController){
        
        this.controller = injectedController;
        this.mapModel       = new MapModel[Constant.GRID_SIZE][Constant.GRID_SIZE];  
        
        for(int x=0;x<Constant.GRID_SIZE;x++){
            for(int y=0;y<Constant.GRID_SIZE;y++){
                this.mapModel[x][y] = new MapModel(); 
            }
        }
        
        initialize(this.mapModel);
    }
    

    private void initialize(MapModel[][] mapModel) {
        
        mapView = new GridMap(mapModel, false, event -> {

            GridBox selectedGridBox = (GridBox) event.getSource();

            if (this.mapView.TryToPlaceShipOnMap(new Ship(ShipsSizeOrderedList[iniShipsToPlace-1], 
                    event.getButton() == MouseButton.PRIMARY), 
                    selectedGridBox.pos_x, selectedGridBox.pos_y)) {

                //PrintDebug.printModel("human placing ships in the model", mapModel);

                if (--iniShipsToPlace == 0) {
                    /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                            + "             Player's move             "));
                    */
                    controller.mainWindowView.displayMessage(3);
                    PrettyPrint.shipsInModel("human", mapModel);

                }
            }
        });
    }
    
    
    public void computerPlaceShipsAutomatically() {
        
        int numOfShips = ShipsSizeOrderedList.length;
        while (numOfShips > 0) {

            Tuple possibleCoordinate = controller.oponent.NextMove();
                       
            Ship ship = new Ship(ShipsSizeOrderedList[numOfShips-1] , Math.random() < 0.5 );
            
            if (controller.oponentView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2)) {
                numOfShips--;
            }
        }
        
        PrettyPrint.shipsInModel("human", mapModel);
        controller.oponentView.applyModelToView();
        controller.oponentView.finishIni = true;
    }
    
    
    @Override
    public Tuple NextMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
