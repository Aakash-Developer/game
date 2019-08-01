package gameengine;

import api.Constant;
import api.Constant.ProcessState;
import api.Constant.Space;
import api.Constant.Uncover;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import model.MapModel;
import utils.PrettyPrint;
import utils.Tuple;
import static utils.Validate.IsCoordinateValid;

/**
 * The ComputerPlayer class creates an AI player capable of
 * generating movement to play battleship
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */
public class ComputerPlayer {
    
    ProcessState processState;
    Tuple nextPosition;
    Tuple prevPosition;
    Tuple firstHit;
    int counterFirstHit;
    boolean left,right,up,down;
    int exitCount;
    int index = 0;
    List<Tuple> list;
    int nextX;
    int nextY;
    private final MapModel[][] mapModel;
    
    
    /**
     * This is the constructor of a Computer/enemy player
     * @param mapModel Represent the information/model of the enemy map
     */
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
        
        this.exitCount = 0;
        int seed = 5;
        nextX = 0;
        nextY = seed;
        list = GetTestSample();
    }

    /**
     * This method calculates based on a predefine algorithm 
     * the next movement of the AI
     * @return Tuple or coordinates of the next movement
     */
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
            
            if(exitCount++>Constant.GRID_SIZE){
                processState = ProcessState.GoEmpty;
            }
            
            switch(processState){
                case Initial:
                    
//                    nextPosition = list.get(index);   //  TEST PURPOSE. TO BE REMOVED
//                    index++;
                    processState = ProcessState.Random;
                    continue;
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
                        this.left = false;
                        processState = ProcessState.Down;
                        nextPosition = new Tuple(this.prevPosition.t1 + 1, this.prevPosition.t2);
                        continue;
                    }
                    else{
                        nextPosition = new Tuple(this.prevPosition.t1 + 1, this.prevPosition.t2);
                    }
                    break;
                case Random:          
//                    nextPosition = list.get(index);   //  TEST PURPOSE. TO BE REMOVED
//                    index++;
                    nextPosition = new Tuple(
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1), 
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1));
                    break;
                case GoEmpty:      
                    nextPosition = FindEmptyPosition();
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
            
                if(cell.IsUncover()){ // if the cell has been hit/uncover already, try the next one
                
                    this.prevPosition = this.nextPosition;
                    nextPosition = null;
                }
            }
        }
        
        this.prevPosition = this.nextPosition;
        
        printNextMove(processState);
        this.exitCount = 0;
        return nextPosition;
    }

    private void printNextMove(ProcessState processState) {
        System.out.print("Computer ->");
        switch(processState){
            case Up:
                System.out.println("Down:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Down:
                System.out.println("Up:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
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
    }

    private Tuple FindEmptyPosition(){
        
        System.out.println("FndEmptyPos");
        for(int x = 0; x<Constant.GRID_SIZE; x++){
               
            for(int y = 0; y<Constant.GRID_SIZE; y++){
              
               if(mapModel[x][y].uncover == Uncover.No)
               {
                       return new Tuple(x,y);
               } 
            }
        }
        return null;
    }
    
    /**
     * This method is used to seed the enemy map with predefine values
     * @return List<Tuple> List of pre-defined coordinates
     */
    private List<Tuple> GetTestSample() {
              
        List<Tuple> coordinates = new ArrayList<Tuple>() { 
            { 
                add(new Tuple(5,1)); 
                add(new Tuple(3,1)); 
                add(new Tuple(1,1)); 
                add(new Tuple(2,2)); 
                add(new Tuple(4,4)); 
                add(new Tuple(5,5));
                add(new Tuple(6,6)); 
                add(new Tuple(7,7)); 
                add(new Tuple(8,8));
            } 
        };
        
        return coordinates;
    }
}
