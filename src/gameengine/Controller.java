package gameengine;

import api.Constant;
import api.Constant.GameType;
import api.IController;
import api.IPlayer;
import api.IPlayerModel;
import api.IPlayerView;
import gameui.GridMap;
import gameui.PlayerView;
import model.Ship;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseButton;
import model.PlayerModel;

/**
 *
 * @author team 4
 */
public class Controller implements IController
{ 
    IPlayer p1;
    IPlayer p2;
    
    GameType gameType;
    List<IPlayer> players;
    public GridMap computerMapView,playerMapView;
    public PlayerView mainWindowView;
    public int gridCellNum = Constant.gridCellNum;
    public boolean isPlayer2Turn = false;
    public boolean computerTurn = false;
    public final ComputerPlayer ai;
    private final Player userPlayer;
                
    public final int grid_size = Constant.GRID_SIZE;
    /*
    public Controller() throws Exception{
        
        
        //Momentarily set the game to only one player in the ctor. 
        //IMPLEMENTATION MISSING: The game type should be provided by the UI.
        
        gameType = GameType.ONE_PLAYER;
        
        CreateNewGame(gameType);

    }
         
    public void CreateNewGame(GameType gameType){ 
        
        switch(gameType){
            case ONE_PLAYER:
                
                p1 = new Player(new PlayerModel(this),new PlayerView(this));
                ((PlayerView) p1.GetView()).SimulateUserEnterTheValuesAndClickedStart();
                
                p2 = new ComputerPlayer(new PlayerModel(this), new PlayerView(this));
                ((PlayerView) p2.GetView()).SimulateUserEnterTheValuesAndClickedStart();
                
                break;
                
            case TWO_PLAYER:
                //IMPLEMENTATION MISSING
                break;
                
            default:
                //IMPLEMENTATION MISSING
                break;
        }
    }
    
    private void StartTheGame(IPlayerModel model, IPlayerView view){
        
        model.SetPlayerMap(view.GetPlayerMap());
        
        PrintMatrix(model.GetPlayerMap());
    }
    
    
    
    
    private void PrintMatrix(int[][] matrix){
        
        for(int x =0;x<matrix.length;x++){
            System.out.println();
            for(int y =0;y<matrix.length;y++){
                   System.out.print(matrix[x][y]);
            }    
        }       
    }
    */
    
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
    // #end region interface implementation
    
    
    public Controller(){
   
        //define the player type
        players = new ArrayList();
        //players.add(new HumanPlayer());
        userPlayer = new Player(this);
        this.playerMapView = userPlayer.playerMapView;       
        ai = new ComputerPlayer(this);        
        this.computerMapView = ai.computerMapView;
        players.add(userPlayer);
        players.add(ai);
        //define the view
        //CreateView(players.get(0));
        CreateView();
    }
     
    //public void CreateView(IPlayer player){
    private void CreateView(){        
        //IPlayerView view = new PlayerView(this, player);
        this.mainWindowView  = new PlayerView(this);
    }
    
    
}
