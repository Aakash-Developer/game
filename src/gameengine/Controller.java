/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameengine;

import api.Constant.PlayerType;
import api.IBinding;
import api.IController;
import api.IPlayer;
import api.IPlayerView;
import gameui.PlayerView;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author zange
 */
public class Controller implements IController
{
    List<IPlayer> players;
    
    public Controller(){
    
        //define the player type
        players = new ArrayList();
        players.add(new HumanPlayer());
        players.add(new ComputerPlayer());
        
        //define the view
        CreateView(players.get(0));
    }
     
    public void CreateView(IPlayer player){
        
        IPlayerView view = new PlayerView(this, player);
    }
    

    
    @Override
    public boolean IsMainController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
