/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gameengine;

import api.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import model.MapModel;
import model.PlayerData;
import model.StorageFormat;
import utils.PrettyPrint;
/**
 * It is responsible for saving and/or loading the data of the game
 * 
 * e game
 * @author zange
 */
public class SaveLoadGame {

    
    MapModel[][] map;
    String name;
    int score;
    double time;
    private final Controller controller;

    public SaveLoadGame(Controller controller){
    
        this.controller = controller;
        
        this.map  = new MapModel[Constant.GRID_SIZE][Constant.GRID_SIZE];  
        
        for(int x=0;x<Constant.GRID_SIZE;x++){
            for(int y=0;y<Constant.GRID_SIZE;y++){
                map[x][y] = new MapModel(); 
            }
        }
        this.name = "Player1";
        this.score = 99;
        this.time = 7.0;
        
    }
    
    public boolean saveGame(){
        
        //HumanPlayer     
        PlayerData playerData = new PlayerData(this.controller.player.mapModel, 
                                          "player", 
                                          this.controller.finalScore, 
                                          this.controller.totalTime);
        
        //ComputerPlayer
        PlayerData oponentData = new PlayerData(this.controller.oponent.mapModel, 
                                          "oponent", 
                                          this.controller.finalScore, 
                                          this.controller.totalTime);
        
        
        StorageFormat game = new StorageFormat(playerData,oponentData);
        return saveGame(game);
        
        //return saveGame(this.map, this.name, this.score , this.time);
    }
    
    public boolean saveGame(StorageFormat game){
        
        boolean successful = false;

        try 
        {
            FileOutputStream f = new FileOutputStream("game.dat",false);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(game);
            o.close();
            f.close();
            successful = true;
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: "+ e.toString());
        } 
        catch (IOException e) 
        {
            System.out.println("Error initializing stream: " + e.toString());
        } 

        return successful;
    }
    
    /**
     * It takes the map model , time, score , player name and save it in file
     * @param map
     * @param name
     * @param score
     * @param time
     * @return boolean True if successful. False otherwise
     */
    public boolean saveGame(MapModel[][] map, String name, int score , double time){
        
        boolean successful = false;
        
        PlayerData p1 = new PlayerData(map, name, score, time);
        
        try 
        {
//            File file = new File("game.dat");
//            file.createNewFile();
            FileOutputStream f = new FileOutputStream("game.dat",false);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(p1);
            o.close();
            f.close();
            
            successful = true;
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        } 
        catch (IOException e) 
        {
            System.out.println("Error initializing stream");
        } 

        return successful;
    }
    
    
    /**
     * It reads a file and convert it into the map model , time, score , 
     * player name 
     * @return boolean True if successful. False otherwise
     */
    //public PlayerData loadGame(){
    public StorageFormat loadGame(){
        
        //PlayerData p1 = null;
        StorageFormat game = null;
        
        try{
            FileInputStream fi = new FileInputStream(new File("game.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            //p1 = (PlayerData) oi.readObject();
            game =  (StorageFormat) oi.readObject();   
//            System.out.println(pr1.toString());
//            System.out.println(pr2.toString());

            oi.close();
            fi.close();
            
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        } 
        catch (IOException e) 
        {
            System.out.println("Error initializing stream");
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("ClassNotFoundException");
        }
        
//        PrintResult(p1);
//        return p1;
        PrintResult(game);
        return game;
        
    }

    private void PrintResult(PlayerData p1) {
        
        System.out.println(p1.toString());
        
    }
    
     private void PrintResult(StorageFormat game) {
        
        System.out.println(game.toString());
        
    }
    
//    private class StorageFormat implements Serializable 
//    {
//        private PlayerData player;
//        private PlayerData oponent;
//                
//        public StorageFormat(PlayerData player, PlayerData oponent){
//            
//            this.player = player;
//            this.oponent = oponent;
//        }
//
//        @Override
//        public String toString() {
//            return "Player:" + player.toString() + "\n" +  "Oponent: " + oponent.toString() + "\n";
//        }
//    }
}
