/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.IPlayer;
import api.IPlayerModel;
import api.IPlayerView;
import utils.Tuple;

/**
 * This class is "only" a helper of the controller 
 * to manage the MODEL and VIEW of a player in a single class
 * 
 * This class is not a MODEL nor a VIEW but it contains both.
 * 
 * @author zange
 */
public class Player implements IPlayer{
        
    public IPlayerModel model;
    public IPlayerView view;
        
    public Player(IPlayerModel model, IPlayerView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public Tuple NextMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int GetNumberOfShips() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IPlayerModel GetModel() {
        return this.model;
    }

    @Override
    public IPlayerView GetView() {
        return this.view;
    }
}
