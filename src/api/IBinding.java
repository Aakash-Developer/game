/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

/**
 *
 * @author zange
 */
public interface IBinding {
    
    /**
     * 
     * 
     */
    void OnMouseLeftClickedInEnemyGrid();
    
    /**
     * The VIEW calls this function when the user clicks the START button
     * The CONTROLLER receives the notification to prepare the model
     * @param viewObject is the player's view
     */
    void OnPlayerClicksStart(IPlayerView viewObject);  
    
}
