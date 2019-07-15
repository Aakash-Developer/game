/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameui;

import api.IController;

/**
 *
 * @author zange
 */
public class ComputerView extends PlayerViewAbstract {

    public ComputerView(IController controller) {
        super(controller);

    } 
    
    public void SimulateComputerEnterTheValuesAndClickedStart(){
        
        this.player_map = new int[][]{ 
             {1,0,0,0,0,0,0,0,0,1,1,1},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,1,1},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {1,1,1,1,0,0,0,0,0,1,1,1},
         };
        
        this.OnButtonStartClicked();
    }
}
