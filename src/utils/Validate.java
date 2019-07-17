/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import api.Constant;
import gameui.GridMap;
import model.Ship;
import javafx.geometry.Point2D;

/**
 *
 * @author zange
 */
public class Validate {
    
    public static boolean isValidPosition(Point2D point) {
        // checking if a coordinate is valid?!
        return isValidPosition(point.getX(), point.getY());
    }

    public static boolean isValidPosition(double x, double y) {
        // checking if a coordinate is valid?!
        return x >= 0 && x < Constant.gridCellNum && y >= 0 && y < Constant.gridCellNum;
    }
    
    public static boolean IsCoordinateValid(Tuple value){
        
        return (int)value.t1 >= 0 && (int)value.t1 < Constant.GRID_SIZE &&
               (int)value.t2 >= 0 && (int)value.t2 < Constant.GRID_SIZE;        
    }
}
