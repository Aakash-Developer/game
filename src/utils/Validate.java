package utils;

import api.Constant;
import javafx.geometry.Point2D;

/**
 * Generic class used for validation of data
 * @author Team 4
 */
public class Validate {
    
    /**
     * It validates position
     * @param point x,y coordinate
     * @return boolean True if valid. False otherwise
     */
    public static boolean isValidPosition(Point2D point) {
        // checking if a coordinate is valid?!
        return isValidPosition(point.getX(), point.getY());
    }

    /**
     * it validates the position of a coordinate
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean True if valid. False otherwise
     */
    public static boolean isValidPosition(double x, double y) {
        // checking if a coordinate is valid?!
        return x >= 0 && x < Constant.gridCellNum && y >= 0 && y < Constant.gridCellNum;
    }
    
    /**
     *  It validates a coordinate in the given range current range
     * @param value is the coordinate to evaluate
     * @return boolean True if valid. False otherwise
     */
    public static boolean IsCoordinateValid(Tuple value){
        
        return value.t1 >= 0 && value.t1 < Constant.GRID_SIZE &&
               value.t2 >= 0 && value.t2 < Constant.GRID_SIZE;        
    }
}
