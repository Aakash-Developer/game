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
    
    public MapModel[][] map;
    public String name;
    public int score;
    public double time;
    public int iniShipsToPlace;
    
    
    public PlayerData(MapModel[][] map, String name, int score , double time, int iniShipsToPlace ){
        
        this.map = map;
        this.name = name;
        this.score = score;
        this.time = time;
        this.iniShipsToPlace = iniShipsToPlace;
        
    }
    
    @Override
    public String toString() {
        
            System.out.print("Name:" + name + " Score: " + score + " Time: " + time + " iniShipsToPlace: " + iniShipsToPlace + "\n");
            PrettyPrint.shipsInModel("ships position",this.map);
            PrettyPrint.uncoverInModel("hitted cells",this.map);
            return "\n";
    }
}
