package gameengine;

import api.Constant;
import api.Constant.GameType;
import api.Constant.Turn;
import api.IController;
import api.IPlayer;
import api.IPlayerView;
import gameui.GridMap;
import gameui.PlayerView;

public class Controller implements IController
{ 
    IPlayer p1;
    IPlayer p2;
    
    GameType gameType;
    
    public PlayerView mainWindowView;
    
    //public boolean isPlayer2Turn = false;
    public boolean computerTurn = false;
    
    
    public final OponentMap oponent;
    public final PlayerMap player;
    public GridMap oponentView, playerView;
    public Turn turn;
    public ComputerPlayer ai;
    
    public Controller(){
   
        this.turn = Turn.None;
        
        this.player = new PlayerMap(this);
        this.playerView = player.mapView;     
        
        this.oponent = new OponentMap(this);        
        this.oponentView = oponent.mapView;
        
        this.oponent.placeShipsRandomly();
        this.ai = new ComputerPlayer(this.player.mapModel);
        
        CreateView();
    }
     
    private void CreateView(){        
        
        this.mainWindowView  = new PlayerView(this);
    }
    
    @Override
    public int GetGridSize() {
        return Constant.GRID_SIZE;
    }
    //region interface implementations

    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnPlayerClicksStart(IPlayerView viewObject) {
        /*
        if(viewObject == p1.GetView()){
            StartTheGame(p1.GetModel(),p1.GetView());
        }
        else if(viewObject == p2.GetView()){
            StartTheGame(p2.GetModel(),p2.GetView());
        }
        else{
            System.out.println("unrecognized viewObject instance");
        }*/
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
