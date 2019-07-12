
package gameengine;
import battleship.IPlayer;
import gameui.Ship;
import java.util.Random;
import utils.Tuple;

/**
 * ComputerPlayer class implements IPlayer for computer player 
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */

public class ComputerPlayer implements IPlayer {

    private final int _gridSize;
    private Random _random = new Random();
    private final int totalShips = 5;
    
    /**
     * Method for initiating a computer player.
     * @param gridSize
     */
    public ComputerPlayer(int gridSize){
        
        _gridSize = gridSize;
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
     * Method of geting the number of ships for computer player.
     * @return
     */
    @Override
    public int GetNumberOfShips() {
        return totalShips;
    }
    
//     /**
//     * method for initializing the computer-player
//     */
//    private void iniComputerSide() {
//        // computer's turn of placing ships
//        int type = totalShips;
//        while (type > 0) {
//            int pos_x = _random.nextInt(_gridSize);
//            int pos_y = _random.nextInt(_gridSize);
//            
//            if (computerMap.placingBattleShipOn_X_Y(new Ship(hitCapacityType[type-1], Math.random() < 0.5), pos_x, pos_y)) {
//                type--;
//            }
//        }
//        
//        isPlayer2Turn = true;
//    }
 
}
