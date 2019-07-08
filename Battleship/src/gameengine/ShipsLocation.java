package gameengine;

import static battleship.Constants.MAP_SIZE;
import java.util.concurrent.ThreadLocalRandom;
import utils.Tuple;

public class ShipsLocation {
    
    private enum Orientation{
        VERTICAL ,
        HORIZONTAL
    }
    
    private enum Position{
        EMPTY,
        FULL,
    }
    
    private byte[][] _matrix;
    
    public byte[][] GetLocationMatrix(){
        return _matrix;
    }
    
    
    public ShipsLocation(byte[][] matrix, byte[] ships){
        _matrix = matrix;
        FillMapWithShips(_matrix, ships);
    }
    
    private void FillMapWithShips(byte[][] matrix, byte[] ships){

        for(int shipLenght : ships){
            
            Tuple initialPosition; 
            Orientation orientation;
            
            do{
                initialPosition = GetRandomPosition();
                orientation = GetRandomOrientation();
            }
            while( 
                    Position.FULL == DetermineSpaceIsAvailable(matrix, initialPosition,orientation,shipLenght) ||
                    Position.FULL == DetermineSpaceIsAvailable(matrix, initialPosition,orientation,shipLenght)
                 );
            
            PlaceShipOnTheMap(matrix, initialPosition, orientation, shipLenght);
        }
    }
    
    private Tuple GetRandomPosition(){

       return new Tuple( ThreadLocalRandom.current().nextInt(0, MAP_SIZE),
                         ThreadLocalRandom.current().nextInt(0, MAP_SIZE));
    }
    
    private Orientation GetRandomOrientation(){
       
       int random = ThreadLocalRandom.current().nextInt(1, 10); 
       
       if(random <= 5){
           return Orientation.VERTICAL;
       }
       else{
           return Orientation.HORIZONTAL;
       }
    }
    
    private Position DetermineSpaceIsAvailable(byte[][] matrix, Tuple position, Orientation orientation ,int lenght){
              
        if(orientation == Orientation.HORIZONTAL){
            
            if((int)position.t1 + lenght > MAP_SIZE-1){
            
                return Position.FULL;
            }
            
            for(int i = (int) position.t1 ; i < (int) position.t1  + lenght ; i++){
                if(matrix[i][(int) position.t2]==1){
                    return Position.FULL;
                }
            }
        }
        else{
            
            if( (int)position.t2 + lenght > MAP_SIZE-1){
            
                return Position.FULL;
            }
           
            for(int i = (int) position.t2 ; i < (int) position.t2  + lenght ; i++){
                if(matrix[(int) position.t1][i]==1){
                    return Position.FULL;
                }
            }
        }
        
        return Position.EMPTY;
    }
    
    private void PlaceShipOnTheMap(byte[][] matrix, Tuple position, Orientation orientation ,int lenght){
   
        if(orientation == Orientation.HORIZONTAL){

            for(int i = (int) position.t1 ; i < (int) position.t1 + lenght ; i++){
                matrix[i][(int) position.t2] = 1;
            }
        }
        else{
            
            for(int i = (int) position.t2 ; i < (int) position.t2 + lenght ; i++){
                matrix[(int) position.t1][i] = 1;
            }
        }
        
        PrintMatrix(matrix);
    }
    
    private void PrintMatrix(byte[][] matrix){
        
        for(int x = 0 ; x < matrix.length ; x++){
            System.out.println();
            for(int y = 0 ; y< matrix.length ; y++){
                System.out.print(matrix[x][y] + " ");
            }
         }
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++");
    }
}
