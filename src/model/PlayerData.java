/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.io.Serializable;
import utils.PrettyPrint;
/**
 *
 * @author zange
 */
public class PlayerData implements Serializable  {
    
    private MapModel[][] map;
    private String name;
    private int score;
    private double time;
    
    
    public PlayerData(MapModel[][] map, String name, int score , double time){
        
        this.map = map;
        this.name = name;
        this.score = score;
        this.time = time;
        
    }
    
    @Override
    public String toString() {
            PrettyPrint.shipsInModel("ships",this.map);
            PrettyPrint.uncoverInModel("empty",this.map);
            return "Name:" + name + " Score: " + score + " Time: " + time + "\n";
    }
}
