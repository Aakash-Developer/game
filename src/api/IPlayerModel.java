/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import utils.Tuple;

/**
 *
 * @author zange
 */
public interface IPlayerModel {
    
    void SetPlayerMap(int[][] player_map);
    int[][] GetPlayerMap();
    
    void SetShootingMap(int[][] shooting_map);
    int[][] GetShootingMap();
    
    boolean SetPlayerMapValueInCoordinate(Tuple coordinate, int value);
    boolean SetShootingMapValueInCoordinate(Tuple coordinate, int value);
    
    int GetPlayerMapValueInCoordinate(Tuple coordinate);
    int GetShootingMapValueInCoordinate(Tuple coordinate);
    
    IController GetController();
}
