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
public final class Constant {
    
    public static int gridCellNum = 10;
    public static int shipNum = 5;
    public static final int[] hitCapacityType = {2,3,3,4,5}; 
    public enum PlayerType{
        HUMAN,
        AI
    }
}
