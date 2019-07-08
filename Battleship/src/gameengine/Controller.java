package gameengine;

import static battleship.Constants.*;
import java.util.concurrent.ThreadLocalRandom;
import utils.Tuple;

public class Controller {
    
    private ModelView _modelView;
    private ComputerPlayer _computerPlayer;
       
    public ModelView GetModelView(){
        return _modelView;
    }
    
    public Controller()
    {
        //position ships in a matrix
        int[] ships = new int[]{5,4,3,3,2};
        byte[][] matrix = new byte[MAP_SIZE][MAP_SIZE];
        DistributeShips randomLocateShips = new DistributeShips(matrix, ships);

        //create view Model
        _modelView = new ModelView();
        _modelView.initializeShipsPositionInMap(randomLocateShips.GetLocationMatrix());
        
        //create computer player
        _computerPlayer = new ComputerPlayer(matrix);

    } 
}
