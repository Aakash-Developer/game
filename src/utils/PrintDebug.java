/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import api.Constant;
import model.MapModel;

/**
 *
 * @author zange
 */
public class PrintDebug {
    
    public static void printModel(String origin, MapModel[][] mapModel ) {
        System.out.print(origin);
        for(int x=0;x<Constant.GRID_SIZE;x++){
            System.out.println();
            for(int y=0;y<Constant.GRID_SIZE;y++){
                System.out.print("["+mapModel[y][x].ToString()+"]");
            }
        }
        System.out.println();
    }
}
