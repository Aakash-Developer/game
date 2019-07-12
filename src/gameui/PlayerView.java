/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameui;

import api.IBinding;
import api.IController;
import api.IPlayer;
import api.IPlayerView;

/**
 *
 * @author zange
 */
public class PlayerView implements IPlayerView, IBinding {
    
    IController controller;
    
    public PlayerView(IController controller, IPlayer player){
        
        
    }
    
    private void buildGridMap(){
        
    }

    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        controller.OnMouseLeftClickedInEnemyGrid();
    }
   
}
