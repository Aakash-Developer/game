package gameengine;

import static battleship.Constants.*;
import java.util.concurrent.ThreadLocalRandom;
import utils.Tuple;

public class Controller {
    
    private ModelView _modelView;
    
    private enum Orientation{
        VERTICAL ,
        HORIZONTAL
    }
    
    private enum Position{
        EMPTY,
        FULL,
    }
    
    public ModelView GetModelView(){
        return _modelView;
    }
    
    public Controller()
    {
        _modelView = new ModelView();
        
        int[] ships = new int[]{5,4,3,3,2};
        byte[][] matrix = new byte[MAP_SIZE][MAP_SIZE];
        
        FillMapWithShips(matrix,ships);
        //PrintMatrix(matrix);
        _modelView.initializeShips(matrix);
    }
    
    public void FillMapWithShips(byte[][] matrix, int[] ships){

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
            
//            if(orientation==Orientation.HORIZONTAL)
//            {
//               System.out.println("H");
//            }
//            else{
//               System.out.println("V"); 
//            }
               
//            System.out.println("y:"+initialPosition.t1 + " " + "x:" +initialPosition.t2 + " " + shipLenght );
            PlaceShipOnTheMap(matrix, initialPosition, orientation, shipLenght);
        }
    }
    
    public Tuple GetRandomPosition(){

       return new Tuple( ThreadLocalRandom.current().nextInt(0, MAP_SIZE),
                         ThreadLocalRandom.current().nextInt(0, MAP_SIZE));
    }
    
    public Orientation GetRandomOrientation(){
       
       int random = ThreadLocalRandom.current().nextInt(1, 10); 
       
       if(random <= 5){
           return Orientation.VERTICAL;
       }
       else{
           return Orientation.HORIZONTAL;
       }
    }
    
    public Position DetermineSpaceIsAvailable(byte[][] matrix, Tuple position, Orientation orientation ,int lenght){
        
        if(orientation==Orientation.HORIZONTAL)
            {
               System.out.println("H");
            }
            else{
               System.out.println("V"); 
            }
        System.out.println("y:"+position.t1 + " " + "x:" +position.t2 + " " + lenght );
        
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
    
    public void PlaceShipOnTheMap(byte[][] matrix, Tuple position, Orientation orientation ,int lenght){
   
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
