/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.Constant.Action;
import api.Constant.State;
import utils.Tuple;
import java.util.concurrent.ThreadLocalRandom;
import utils.Validate;
/**
 *
 * @author zange
 */
public class SearchEngine {
    
    State currentState;
    Action currentAction;

    enum Move{
        UP,
        DOWN,
        LEFT,
        RIGHT,
        RANDOM,
    }
    
    enum MoveFeedback{
        MISSED,
        HIT,
        INITIAL,
    }
    
    
    enum CellState{
        HIT,
        UNKNOWN
    }
    
    enum ShipState{
        HIT,
        DESTROYED
    }
    
    Move previousMove;
    
    MoveFeedback feedbackPreviousMove;
    Tuple firstHit;
    boolean firstHitFlag;
    boolean shipIsDestroyed;
    Tuple previousCoordinate;
    
    
    
    public SearchEngine(){
 
        //previousMove = Move.RANDOM;
        firstHitFlag = true;
        shipIsDestroyed = false;
        feedbackPreviousMove = MoveFeedback.INITIAL;
        
    }
    
    public Tuple GetNextCoordinate(ShipState shipState){
        
        Tuple coordinate = null;
        
        
        if(feedbackPreviousMove.equals(MoveFeedback.INITIAL)){
            if(!firstHitFlag) {
                firstHitFlag = true;
            }
            coordinate = GetTuple(new Tuple(0,0), Move.RANDOM);
        }
        else if(feedbackPreviousMove.equals(MoveFeedback.MISSED)){
            coordinate = GetTuple(new Tuple(0,0), Move.RANDOM);
        }
        else if(feedbackPreviousMove.equals(MoveFeedback.HIT)){
            
            if(shipIsDestroyed){
                
            }
            
            
            if(firstHitFlag) {
                firstHit = previousCoordinate;
                firstHitFlag = false;
            }
            
            coordinate = GetTuple(new Tuple(0,0), Move.RANDOM);
        } 
        
        previousCoordinate = coordinate;
        return coordinate;
    }
    
    void SaveFirstHit(Tuple coordinate){
        
        firstHit = coordinate;
    }
    
    Tuple GetTuple(Tuple currentPosition, Move move){
            
        Tuple coordinate = null; 
        
        switch(move){

            case UP:
                coordinate = new Tuple((int)currentPosition.t1 + 1, (int)currentPosition.t2);
                break;
            case DOWN:
                coordinate = new Tuple((int)currentPosition.t1 - 1, (int)currentPosition.t2);
                break;
            case LEFT:
                coordinate = new Tuple((int)currentPosition.t1, (int)currentPosition.t2 - 1);
                break;
            case RIGHT:
                coordinate = new Tuple((int)currentPosition.t1 + 1, (int)currentPosition.t2 + 1);
                break;
            case RANDOM:
                coordinate = new Tuple(
                        ThreadLocalRandom.current().nextInt(1, 12 + 1), 
                        ThreadLocalRandom.current().nextInt(1, 12 + 1));
                break;
            default:
                break;     
        }
        
        if(!Validate.IsCoordinateValid(coordinate)){
            coordinate = null;
        }
        
        return coordinate;
    }
    
    Move ChangeDirection(Move previousMove){
        
        Move newDirection;
        switch(previousMove){
            
            case UP:
              newDirection= Move.DOWN;
              break;
            case DOWN:
              newDirection= Move.UP;
              break;
            case LEFT:
              newDirection= Move.RIGHT;
              break;
            case RIGHT:
              newDirection= Move.LEFT;
              break;
            default:
              newDirection= Move.RANDOM;
              break;
        }
        
        return newDirection;
    }
    
//    Move ChangeAxis(){
//        
//    }
}

