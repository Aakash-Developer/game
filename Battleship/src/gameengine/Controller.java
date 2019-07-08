package gameengine;

import static battleship.Constants.*;
import java.util.concurrent.ThreadLocalRandom;
import utils.Tuple;

public class Controller {
    
    private ModelView _modelView;
    private ComputerPlayer _computerPlayer;
    
    private enum CellState{
        EMPTY,
        SHIP,
        HIT,
        DESTROYED
    }
       
    public ModelView GetModelView(){
        return _modelView;
    }
    
    private byte[][][] _maps;
    private byte[][] _player1_ship_map;
    private byte[][] _player1_enemy_map;
    private byte[][] _player2_ship_map;
    private byte[][] _player2_enemy_map;
    private byte[] _ships;
    
    public Controller()
    {
        _maps               = new byte[4][MAP_SIZE][MAP_SIZE];
        _player1_ship_map   = _maps[0];
        _player1_enemy_map  = _maps[1];
        _player2_ship_map   = _maps[2];
        _player2_enemy_map  = _maps[3];
        _ships = new byte[]{5,4,3,3,2};
        
        //byte[][] matrix = new byte[MAP_SIZE][MAP_SIZE];
        ShipsLocation player1ShipMap = new ShipsLocation(_player1_ship_map, _ships);
        ShipsLocation player2ShipMap = new ShipsLocation(_player2_ship_map, _ships);

        //create view Model
        _modelView = new ModelView();
        _modelView.initializeShipsPositionInMap(_player1_ship_map);
        
        //create computer player
        _computerPlayer = new ComputerPlayer(_player1_enemy_map);

    } 
}
