
package gameengine;

import api.Constant;
import api.IPlayer;
import gameui.GridBox;
import gameui.GridMap;
import gameui.Ship;
import javafx.scene.input.MouseButton;
import utils.Tuple;

/**
 * one of the "Controller" module.
 * Compared to a computer, the human player needs the map to be created first
 * so it can position the ships by himself
 */
public class HumanPlayer implements IPlayer {
    public boolean isComputerTurn;
    //public boolean computerTurnFlag;
    public final int[] hitCapacityType = Constant.hitCapacityType;
    
    private int gridSize=Constant.gridCellNum;
    private final int totalShips = Constant.shipNum;   
    private int iniShipsToPlace = totalShips;
    private Controller mainController;
    // Initialize the grid map for player side
    public GridMap playerMapView = new GridMap(false, event -> {
        if (isComputerTurn){
            return;
        }
        GridBox selectedGridBox = (GridBox) event.getSource();

        if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(hitCapacityType[iniShipsToPlace-1], 
                event.getButton() == MouseButton.PRIMARY), 
                selectedGridBox.pos_x, selectedGridBox.pos_y)) {
            if (--iniShipsToPlace == 0) {
                /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                        + "             Player's move             "));
                */
                mainController.mainWindowView.displayMessage(3);
                iniComputerSide();
            }
        }

    },gridSize);
    
    /**
     * Method for initiating a computer player.
     * 
     * @param injectedController
     */
    //public ComputerPlayer(PlayerView mainRootWindowView, Controller injectedController){
    public HumanPlayer(Controller injectedController){
        //this.mainWindowView = mainRootWindowView;
        this.mainController = injectedController;
        this.gridSize = injectedController.gridCellNum;
        this.isComputerTurn = injectedController.isPlayer2Turn;
        //this.computerTurnFlag = injectedController.computerTurn;
        //mainController.computerMapView = computerMapView2;
    }
    
    @Override
    public Tuple NextMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetNumberOfShips() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetPlayerType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] GetMatrix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void iniComputerSide() {
        // computer's turn of placing ships
        int type = mainController.ai.GetNumberOfShips();
        System.out.println("initialize Computer!!!");
        while (type > 0) {

            Tuple coordinates = mainController.ai.NextMove();
            int pos_x = (int) coordinates.t1;
            int pos_y = (int) coordinates.t2;

            if (mainController.computerMapView.placingBattleShipOn_X_Y(new Ship(hitCapacityType[type-1], Math.random() < 0.5), pos_x, pos_y)) {
                type--;
            }
        }
        System.out.println("Finish initializing Computer!!!");
        mainController.isPlayer2Turn = true;
    }
}
