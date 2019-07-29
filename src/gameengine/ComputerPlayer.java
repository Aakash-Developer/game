/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.Constant;
import api.Constant.ProcessState;
import java.util.concurrent.ThreadLocalRandom;
import model.MapModel;
import utils.PrettyPrint;
import utils.Tuple;
import static utils.Validate.IsCoordinateValid;

/**
 *
 * @author zange
 */
public class ComputerPlayer {
    
    ProcessState processState;
    Tuple nextPosition;
    Tuple prevPosition;
    Tuple firstHit;
    int counterFirstHit;
    boolean left,right,up,down;

    private final MapModel[][] mapModel;
    
    public ComputerPlayer(MapModel[][] mapModel){
    
        this.mapModel = mapModel;
        
        processState     = ProcessState.Initial;
        nextPosition     = null;
        prevPosition     = null;
        firstHit         = null;
        left             = false;
        right            = false;
        up               = false;
        down             = false;
    }

    public Tuple getNextComputerMove(){

        if(this.prevPosition != null){ //Determine next action from previous move
            
            MapModel cell = mapModel[this.prevPosition.t1][this.prevPosition.t2];
            
            if(cell.IsUncover())
            {
                if(cell.IsSpaceEmpty()){ //change direction/cardinality
                    
                    switch(processState) { //search in oposite direction
                        case Up:
                            processState = ProcessState.Down;
                            break;
                        case Down:
                            processState = ProcessState.Up;
                            break;
                        case Left:
                            processState = ProcessState.Right;
                            break;
                        case Right:
                            processState = ProcessState.Left;
                            break;
                        default:
                            processState = ProcessState.Random;
                            break;  
                    }
                }
                else{   // ship is there , continue same direction
                    
                    if(!cell.ShipInstance.isAlive()){
                        System.out.println("ship is dead!");
                        processState = ProcessState.Initial;
                    }
                    
                    switch(processState) { //Continue searching in the same direction 
                        case Initial:  // first hit, change strategy
                            processState    = ProcessState.Random;
                            break;
                        case Up:
                            processState = ProcessState.Up;
                            break;
                        case Down:
                            processState = ProcessState.Down;
                            break;
                        case Left:
                            processState = ProcessState.Left;
                            break;
                        case Right:
                            processState = ProcessState.Right;
                            break;
                        case Random:  // first hit, change strategy
                            processState    = ProcessState.Right;
                            break;
                        default:
                            processState = ProcessState.Random;
                            break;  
                    }
                    
                }
                
            }
        }
        
        this.nextPosition = null; //initialize a new search
        
        while(this.nextPosition == null){

            switch(processState){

                case Initial:
                    nextPosition = new Tuple(1,0);  //  TEST PURPOSE. TO BE REMOVED
                    processState = ProcessState.Random;
                    break;
                case Up:
                    nextPosition = new Tuple(this.prevPosition.t1, this.prevPosition.t2 + 1);
                    break;
                case Down:
                    nextPosition = new Tuple(this.prevPosition.t1, this.prevPosition.t2 - 1);
                    break;
                case Left:
                    this.left = true;
                    nextPosition = new Tuple(this.prevPosition.t1 - 1, this.prevPosition.t2);
                    break;
                case Right:
                    this.right = true;
                    if(this.left){ //let it go right one step and change axis
                        processState = ProcessState.Down;
                        continue;
                    }
                    else{
                        nextPosition = new Tuple(this.prevPosition.t1 + 1, this.prevPosition.t2);
                    }
                    break;
                case Random:
                    nextPosition = new Tuple(
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1), 
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1));
                    break;
                default:

                    break;     
            }
            
            
            if(!IsCoordinateValid(nextPosition)){ //coordinate out of range, change direction
                
                nextPosition = null;
                
                switch(processState) {
                    case Up:
                        processState = ProcessState.Down;
                        break;
                    case Down:
                        processState = ProcessState.Up;
                        break;
                    case Left:
                        processState = ProcessState.Right;
                        break;
                    case Right:
                        processState = ProcessState.Left;
                        break;
                    default:
                        processState = ProcessState.Random;
                    break;  
                }
            }
            else{
                MapModel cell = mapModel[this.nextPosition.t1][this.nextPosition.t2];
            
                if(cell.IsUncover()){ //coordinate out of range, change direction
                
                    this.prevPosition = this.nextPosition;
                    nextPosition = null;
                }
            }
        }
        
        this.prevPosition = this.nextPosition;
        
        
        switch(processState){
            case Up:
                System.out.println("Up:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Down:
                System.out.println("Down:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Left:
                System.out.println("Left:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Right:
                System.out.println("Right:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Random:
                System.out.println("Random:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
        }
        
        return nextPosition;
    }
}