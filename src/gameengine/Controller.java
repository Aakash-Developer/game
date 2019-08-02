package gameengine;

import api.Constant.GameType;
import api.Constant.Turn;
import gameui.GridMap;
import gameui.PlayerView;

/**
* The Controller class in the controller in the MVC architecture
* of the application. It is responsible of coordinating the view 
* and model
*/
public class Controller
{ 
    GameType gameType;
    public PlayerView mainWindowView;
    public boolean computerTurn = false;
    public final OponentMap oponent;
    public final PlayerMap player;
    public GridMap oponentView, playerView;
    public Turn turn;
    public ComputerPlayer ai;
    public SaveLoadGame saveLoadGame;
    
    /**
    * Initializes one controller per game
    */
    public Controller(){
   
        this.turn = Turn.None;
        
        this.player = new PlayerMap(this);
        this.playerView = player.mapView;     
        
        this.oponent = new OponentMap(this);        
        this.oponentView = oponent.mapView;
        
        this.oponent.placeShipsRandomly();
        this.ai = new ComputerPlayer(this.player.mapModel);
        
        this.saveLoadGame = new SaveLoadGame();
        
        CreateView();
    }
     
    /**
     * initializes the JavaFX framework with the
     * view of the game
     */
    private void CreateView(){        
        
        this.mainWindowView  = new PlayerView(this);
    }
}
