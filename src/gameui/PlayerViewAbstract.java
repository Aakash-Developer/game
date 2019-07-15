/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameui;

import api.Constant;
import api.IBinding;
import api.IController;
import api.IPlayerView;

/**
 *
 * @author zange
 */
public abstract class  PlayerViewAbstract implements IPlayerView, IBinding {
    
    private final IController controller;
    
    int[][] player_map;
    int[][] enemy_map;
    
    public PlayerViewAbstract(IController controller){
        
        this.controller = controller;
        
        player_map = new int[this.controller.GetGridSize()][this.controller.GetGridSize()];
        enemy_map = new int[this.controller.GetGridSize()][this.controller.GetGridSize()]; 
    }
    
    public void OnButtonStartClicked(){
        this.OnPlayerClicksStart(this);
    } 
     
    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        controller.OnMouseLeftClickedInEnemyGrid();
    }

    @Override
    public int[][] GetPlayerMap() {
        return player_map;
    }

    /*  IMPLEMENTATION MISSING
        CALL THIS METHOD IN PlayerView WHEN USER CLICKS START
    */ 
    @Override
    public void OnPlayerClicksStart(IPlayerView viewObject){
        
        if(DetermineIfAllShipsAreInPlace())
        {
            FillPlayerMap();
            this.controller.OnPlayerClicksStart(viewObject);
        }
        else{
            
            DisplayDialogMessage(Constant.SHIPS_MUST_BE_IN_ALL_IN_PLACE);
        }
    }

    @Override
    public boolean DetermineIfAllShipsAreInPlace(){
        
        boolean allShipsAreInPlace = false;
        
        /* 
        IMPLEMENTATION MISSING 
        
        Requierements:
        1) Make sure all ships are already placed in the map after drag & drop operations of the ships and
        vertical or horizontal repositioning.  
        */
        
        allShipsAreInPlace = true;
        
        return allShipsAreInPlace;
    }
    
    private void FillPlayerMap(){
        
        /*
        MISSING IMPLEMENTATION
        
        Requierements:
        1) Fill with ones the space ocupied with ships and zeroes the empty spaces
        2) The information is persisted in player_map variable.
        */
    }
    
    private void DisplayDialogMessage(String message){
        /*
        MISSING IMPLEMENTATION
        */
        System.out.println(message);
        
    }
}
