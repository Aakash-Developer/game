/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import api.Constant;
import gameengine.Controller;
import api.IPlayerModel;
import utils.Tuple;
import utils.Validate;

/**
 *
 * @author zange
 */
public class PlayerModel implements IPlayerModel {
    
    Controller controller;
    int[][] player_map;
    int[][] shooting_map;

    public PlayerModel(Controller controller){
        
        this.controller = controller;
        player_map = new int[Constant.GRID_SIZE][Constant.GRID_SIZE];
        shooting_map = new int[Constant.GRID_SIZE][Constant.GRID_SIZE];
    }

    @Override
    public Controller GetController(){
        return this.controller;
    }
    
    @Override
    public void SetPlayerMap(int[][] player_map) {
        this.player_map = player_map;
    }

    @Override
    public void SetShootingMap(int[][] shooting_map) {
        this.shooting_map = player_map;
    }

    @Override
    public int[][] GetPlayerMap() {
        return this.player_map;
    }

    @Override
    public int[][] GetShootingMap() {
        return this.shooting_map;
    }

    @Override
    public boolean SetPlayerMapValueInCoordinate(Tuple coordinate, int value) {
        
        if(Validate.IsCoordinateValid(coordinate)){
            this.player_map[(int)coordinate.t1][(int)coordinate.t2] = value;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean SetShootingMapValueInCoordinate(Tuple coordinate, int value) {
        
        if(Validate.IsCoordinateValid(coordinate)){
            this.shooting_map[(int)coordinate.t1][(int)coordinate.t2] = value;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int GetPlayerMapValueInCoordinate(Tuple coordinate) {
        
        if(Validate.IsCoordinateValid(coordinate)){
            return this.player_map[(int)coordinate.t1][(int)coordinate.t2];
        }
        else{
            return -1;
        }
    }

    @Override
    public int GetShootingMapValueInCoordinate(Tuple coordinate) {
        
        if(Validate.IsCoordinateValid(coordinate)){
            return this.shooting_map[(int)coordinate.t1][(int)coordinate.t2];
        }
        else{
            return -1;
        }
        
    }

}
