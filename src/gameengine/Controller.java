package gameengine;

import api.Constant;
import api.Constant.GameType;
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
    
    public final ComputerPlayer ai;
    public final Player human;
    public GridMap computerView, humanView;

    public Controller(){
   
        this.human = new Player(this);
        this.humanView = human.mapView;     
        
        this.ai = new ComputerPlayer(this);        
        this.computerView = ai.mapView;
        this.ai.placeShipsRandomly();
        
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
