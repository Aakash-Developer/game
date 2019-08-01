/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

/**
 *
 * @author zange
 */
public class Constant {
    
    //LEGACY
    public static int gridCellNum = 10;
    public static int totalShips = 5;
    
    //NEW ONES
    public static int GRID_SIZE = 10;
    public static int[] SHIPS_SIZE = new int[]{2,3,3,4,5};
    
    public enum Space{
        IsEmpty,
        IsShip
    }
    
    public enum Uncover{
        Yes,
        No,
    }
    
    public enum Turn{
        None,
        Player1,
        Player2
    }
    
    public enum ProcessState {
        Initial,
        Random,
        Left,
        Right,
        Up,
        Down,
        Repeat,
        GoEmpty
    }
        
    public enum ShootingMapStatus{
        
        HIT,
        TESTED,
        UNKNOWN
        
    }
    //Messages
    
    public static final String SHIPS_MUST_BE_IN_ALL_IN_PLACE = "Ships must be place in the map prior to start a game";
     
    public enum PlayerType{
        HUMAN,
        AI
    }
    
    public enum State{
        Invalid,
        A,
        B,
        C,
        D,
        RANDOM_SEARCH,
        CLOCKWISE,
        VERTICAL,
        HORIZONTAL,
    }
    
    public enum Action{
        INIT,
        NEXT,
        HIT
    }
    
    public enum GameType{
        ONE_PLAYER,
        TWO_PLAYER
    }
    
}
