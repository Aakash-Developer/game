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
import model.MapModel;
import model.PlayerData;
import model.StorageFormat;
/**
 * It is responsible for saving and/or loading the data of the game
 * 
 * e game
 * @author zange
 */
public class SaveLoadGame {

    private final Controller controller;

    public SaveLoadGame(Controller controller){
    
        this.controller = controller; 
    }
    
    public boolean saveGame(){
        
        //HumanPlayer     
        PlayerData playerData = new PlayerData(this.controller.player.mapModel, 
                                          "player", 
                                          this.controller.finalScore, 
                                          this.controller.totalTime,
                                          this.controller.player.iniShipsToPlace);
        
        //ComputerPlayer
        PlayerData oponentData = new PlayerData(this.controller.oponent.mapModel, 
                                          "oponent", 
                                          this.controller.finalScore, 
                                          this.controller.totalTime,
                                          0);
        
        
        StorageFormat game = new StorageFormat(playerData, oponentData);
        
        return saveGame(game);
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
    
//    /**
//     * It takes the map model , time, score , player name and save it in file
//     * @param map
//     * @param name
//     * @param score
//     * @param time
//     * @return boolean True if successful. False otherwise
//     */
//    public boolean saveGame(MapModel[][] map, String name, int score , double time){
//        
//        boolean successful = false;
//        
//        PlayerData p1 = new PlayerData(map, name, score, time);
//        
//        try 
//        {
//            FileOutputStream f = new FileOutputStream("game.dat",false);
//            ObjectOutputStream o = new ObjectOutputStream(f);
//
//            o.writeObject(p1);
//            o.close();
//            f.close();
//            
//            successful = true;
//        } 
//        catch (FileNotFoundException e) 
//        {
//            System.out.println("File not found");
//        } 
//        catch (IOException e) 
//        {
//            System.out.println("Error initializing stream");
//        } 
//
//        return successful;
//    }
    
    
    /**
     * It reads a file and convert it into the map model , time, score , 
     * player name 
     * @return boolean True if successful. False otherwise
     */
    public StorageFormat loadGame(){
        
        StorageFormat game = null;
        
        try{
            FileInputStream fi = new FileInputStream(new File("game.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            game =  (StorageFormat) oi.readObject();   
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
        
        PrintResult(game);
        return game;
        
    }

     private void PrintResult(StorageFormat game) {
        
        System.out.println(game.toString());
        
    }
}
