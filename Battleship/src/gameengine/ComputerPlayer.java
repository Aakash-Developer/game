/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;
import static battleship.Constants.MAP_SIZE;
import battleship.IPlayer;
import gameui.Ship;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
    private final int totalShips = 5;
    private byte[][]  _matrix;
    private State _currentState;
    private Tuple _currentMove;
    private int _counter;

    private enum State{
        NO_SHIPS_FOUND_YET,
        FIRST_HIT,          // orientation vertical or horizontal not known yet
        SECOND_HIT,         // oritentation is known
    }
    
    public ComputerPlayer( byte[][] matrix){
        
        _gridSize = matrix.length;
        _currentState = State.NO_SHIPS_FOUND_YET;  
        _counter = 0;
    }
    
    @Override
    public Tuple NextMove() {
        
        switch(_currentState){
            
            case NO_SHIPS_FOUND_YET:
                return GetRandomPosition();
             case FIRST_HIT:
                return GetRandomPosition();
             case SECOND_HIT:
                return GetRandomPosition();    
             default:
                return GetRandomPosition(); 
        }
    }
    
    private Tuple GetRandomPosition(){

       _currentMove =  new Tuple( ThreadLocalRandom.current().nextInt(0, MAP_SIZE),
                         ThreadLocalRandom.current().nextInt(0, MAP_SIZE));
       
       return _currentMove;
    }
    
    private Tuple GetPosition(){
        

        switch(_counter){
            
            case 1:
                
                //GetMove();
                
                break;
                
            default:
                break;
        }
        
       
       return _currentMove;
    }
    
    private boolean IsValidMove(Tuple move){
        
        return true;
    }
    
    private Tuple GetClockwiseMove(){
        
        Tuple move;
        do{
            move = new Tuple(1,2);
        }
        while(IsValidMove(move));

        return move;
    }
    
    private Tuple GetVerticalTuple(){
        return new Tuple(1,2);
    }
    
    private Tuple GetHorizontalTuple(){
        return new Tuple(1,2);
    }
    
    
    
    @Override
    public void Hit() {
        
        _counter++;
        
    }
    
    @Override
    public void ShipWasDestroyed() {
        
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
