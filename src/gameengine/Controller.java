
package gameengine;

import api.Constant;
import api.Constant.PlayerType;
import api.IBinding;
import api.IController;
import api.IPlayer;
import api.IPlayerView;
import gameui.GridMap;
import gameui.PlayerView;
import java.util.ArrayList;
import java.util.List;
/**
 * "Controller" module
 * @author zange
 */
public class Controller implements IController
{
    List<IPlayer> players;
    public GridMap computerMapView,playerMapView;
    public PlayerView mainWindowView;
    public int gridCellNum = Constant.gridCellNum;
    public boolean isPlayer2Turn = false;
    public boolean computerTurn = false;   
    //public final int[] hitCapacityType = Constant.hitCapacityType;
    //private final int totalShips = Constant.shipNum;
    public final ComputerPlayer ai;
    private final HumanPlayer userPlayer;
    //private int iniShipsToPlace = totalShips;
        
    public Controller(){
   
        //define the player type
        players = new ArrayList();
        //players.add(new HumanPlayer());
        userPlayer = new HumanPlayer(this);
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
    

    
    @Override
    public boolean IsMainController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    public void computerMove() {
        
        while (computerTurn) {
            
            Tuple nextmove = ai.NextMove();
            int x = (int) nextmove.t1;
            int y = (int) nextmove.t2;
            
            int counter=0;
            for(int i=0;i<10000;i++){
                counter+=i;
            }
            
            GridBox selectedGridBox = playerMapView.getGridBoxByCoordinate(x, y);
            if (selectedGridBox.isHitted)
                continue;
            
            computerTurn = selectedGridBox.hitGridBox();
            // If there is no ship left on the player's map, then computer wins!
            if (playerMapView.shipsNumOnMap == 0) {
                //root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
            }
        }
    }*/
}
