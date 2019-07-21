/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import api.Constant;
import api.Constant.*;
import gameui.GridMap;
import javafx.geometry.Point2D;
import model.Ship;

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
        
        return value.t1 >= 0 && value.t1 < Constant.GRID_SIZE &&
               value.t2 >= 0 && value.t2 < Constant.GRID_SIZE;        
    }
     
        
    // 0 - > Empty
    // 1 - > Ship
    // 2 - > Unknown
    // 3 - > Missed
    // 4 - > Hit
    public static boolean IsCoordinateUnknown(int[][] matrix,Tuple value){
        return 2 == matrix[(int)value.t1][(int)value.t2];
    }
    
    public static boolean IsCoordinateMissed(int[][] matrix,Tuple value){
        return 3 == matrix[(int)value.t1][(int)value.t2];
    }

    public static boolean IsCoordinateHit(int[][] matrix, Tuple value){
        return 4 == matrix[(int)value.t1][(int)value.t2];
    }
    

}
