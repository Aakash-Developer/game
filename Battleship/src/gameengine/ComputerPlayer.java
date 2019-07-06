/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;
import battleship.IPlayer;
import gameui.Ship;
import java.util.Random;
import utils.Tuple;

/*
 * @author Team 4
 * @Zbigniew Ivan Angelus
 * @Chen-Fang Chung
 * @Ayush Dave
 * @Aakash Ahuja
 * @Pulkit Wadhwa
 */

public class ComputerPlayer implements IPlayer {

    private final int _gridSize;
    private Random _random = new Random();
    private final int totalShips = 5;
    
    public ComputerPlayer(int gridSize){
        
        _gridSize = gridSize;
    }
    
    @Override
    public Tuple NextMove() {
        
        int a = _random.nextInt(_gridSize);
        int b = _random.nextInt(_gridSize);
        
        return new Tuple(_random.nextInt(_gridSize),_random.nextInt(_gridSize));
    }
    
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
