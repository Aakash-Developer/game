/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author zange
 */
public class StorageFormat implements Serializable {
    
     public PlayerData player;
        public PlayerData oponent;
                
        public StorageFormat(PlayerData player, PlayerData oponent){
            
            this.player = player;
            this.oponent = oponent;
        }

        @Override
        public String toString() {
            return player.toString() + "\n" +  oponent.toString() + "\n";
        }
}
