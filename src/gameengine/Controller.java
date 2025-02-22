package gameengine;

import api.Constant.GameType;
import api.Constant.Turn;
import gameui.GridMap;
import gameui.PlayerView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StorageFormat;

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
    public  OponentMap oponent;
    public  PlayerMap player;
    public GridMap oponentView, playerView;
    public Turn turn;
    public ComputerPlayer ai;
    public SaveLoadGame saveLoadGame;
    public double totalTime = 0;
    public int finalScore = 0; 
    
    
    /**
    * Initializes one controller per game
    */
    public Controller(boolean newGame){
   
        this.saveLoadGame = new SaveLoadGame(this);
        
        if(newGame){
            this.turn = Turn.None;

            this.player = new PlayerMap(this);
            this.playerView = player.mapView;     

            this.oponent = new OponentMap(this);        
            this.oponentView = oponent.mapView;

            this.oponent.placeShipsRandomly();
            this.ai = new ComputerPlayer(this.player.mapModel);

            //this.saveLoadGame = new SaveLoadGame(this);

            CreateView();    
        }
        else{
            
            //this.saveLoadGame = new SaveLoadGame(this);
            loadGame();
        }
        
    }
    
    public void loadGame(){
        
        StorageFormat game = this.saveLoadGame.loadGame();
        
        if(game != null){
            
            this.player = new PlayerMap(this, game.player.map, game.player.iniShipsToPlace);
            this.playerView = player.mapView;     

            this.oponent = new OponentMap(this, game.oponent.map);        
            this.oponentView = oponent.mapView;
            
            this.ai = new ComputerPlayer(this.player.mapModel);
            
            CreateView();

        }
        else {
            System.out.println("problem loading the game");
        }
        
        
    }

    /**
     * initializes the JavaFX framework with the
     * view of the game
     */
    private void CreateView(){        
        
        this.mainWindowView  = new PlayerView(this);
    }
}
