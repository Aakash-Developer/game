/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameui;

import javafx.scene.paint.Color;
import model.RectangleShipObj;

/**
 *
 * @author zange
 */
public class DragableShip extends RectangleShipObj{

    public int size;
            
    public DragableShip(int x, int y, Color color, int size) {
        super(x, y, color);
        this.size=size;
    }
    
}
