/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import api.Constant;
import model.MapModel;

/**
 * It is used to print data in a template to debug the application
 * @author Team 4
 */
public class PrettyPrint {
    
    /**
     * It displays the position of ships in a matrix map for a given player
     * @param origin MEssage to display for debug purpose
     * @param mapModel The data model to display
     */
    public static void shipsInModel(String origin, MapModel[][] mapModel ) {
        System.out.print(origin);
        for(int x=0;x<Constant.GRID_SIZE;x++){
            System.out.println();
            for(int y=0;y<Constant.GRID_SIZE;y++){
                System.out.print("["+mapModel[y][x].space()+"]");
            }
        }
        System.out.println();
    }
    
    /**
     * It displays the position of the maps that have been uncover
     * @param origin MEssage to display for debug purpose
     * @param mapModel  The data model to display
     */
    public static void uncoverInModel(String origin, MapModel[][] mapModel ) {
        System.out.print(origin);
        for(int x=0;x<Constant.GRID_SIZE;x++){
            System.out.println();
            for(int y=0;y<Constant.GRID_SIZE;y++){
                System.out.print("["+mapModel[y][x].uncover()+"]");
            }
        }
        System.out.println();
    }
    
    /**
     * It displays the position of the maps that have been uncover
     * @param origin MEssage to display for debug purpose
     * @param mapModel  The data model to display
     */
    public static void attackeModel(String origin, boolean[][] mapModel ) {
        System.out.print(origin);
        for(int x=0;x<Constant.GRID_SIZE;x++){
            System.out.println();
            for(int y=0;y<Constant.GRID_SIZE;y++){
                if(true==mapModel[y][x]){
                    System.out.print("[1]");    
                }
                else{
                    System.out.print("[ ]");    
                }
            }
        }
        System.out.println();
    }
}
