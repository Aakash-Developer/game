
package gameengine;
import api.Constant;
import api.IPlayer;
import gameui.GridBox;
import gameui.GridMap;
import gameui.Ship;
import java.util.Random;
import utils.Tuple;

/**
 * ComputerPlayer class implements IPlayer for computer player
 * One of the "Controller" module.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */

public class ComputerPlayer implements IPlayer{
    
    public boolean isComputerTurn;
    public boolean computerTurnFlag;
    
    private int _gridSize=Constant.gridCellNum;
    private Random _random = new Random();
    private final int totalShips = Constant.shipNum;
    private Controller mainController;
    
    // Initialize the grid map for computer side
    public GridMap computerMapView = new GridMap(true, event -> {
        if (!mainController.isPlayer2Turn){
            return;
        }

        GridBox selectedGridBox = (GridBox) event.getSource();
        if (selectedGridBox.isHitted){
            return;
        }

        computerTurnFlag = !selectedGridBox.hitGridBox();
        // If there is no ship left on the computer's map, then player wins!
        if (this.computerMapView.shipsNumOnMap == 0) {
            //root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
            mainController.mainWindowView.displayMessage(2);
        }

        if (computerTurnFlag){
            //mainController.computerMove();
            computerMove(); 
        }
    },_gridSize);
    
    /**
     * Method for initiating a computer player.
     * 
     * @param injectedController
     */
    //public ComputerPlayer(PlayerView mainRootWindowView, Controller injectedController){
    public ComputerPlayer(Controller injectedController){
        this.mainController = injectedController;
        this._gridSize = injectedController.gridCellNum;
        this.isComputerTurn = injectedController.isPlayer2Turn;
        this.computerTurnFlag = injectedController.computerTurn;
    }
    
    ComputerPlayer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Method of making the next move for computer player.
     * @return
     */
    @Override
    public Tuple NextMove() {
        
        int a = _random.nextInt(_gridSize);
        int b = _random.nextInt(_gridSize);
        
        return new Tuple(_random.nextInt(_gridSize),_random.nextInt(_gridSize));
    }
    
    /**
     * Method of getting the number of ships for computer player.
     * @return
     */
    @Override
    public int GetNumberOfShips() {
        return totalShips;
    }

    @Override
    public int GetPlayerType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] GetMatrix() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void computerMove() {
        
        while (mainController.computerTurn) {
            
            Tuple nextmove = mainController.ai.NextMove();
            int x = (int) nextmove.t1;
            int y = (int) nextmove.t2;
            
            int counter=0;
            for(int i=0;i<10000;i++){
                counter+=i;
            }
            
            GridBox selectedGridBox = mainController.playerMapView.getGridBoxByCoordinate(x, y);
            if (selectedGridBox.isHitted)
                continue;
            
            mainController.computerTurn = selectedGridBox.hitGridBox();
            // If there is no ship left on the player's map, then computer wins!
            if (mainController.playerMapView.shipsNumOnMap == 0) {
                //root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
            }
        }
    }
}
