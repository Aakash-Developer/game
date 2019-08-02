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
import static java.lang.System.out;
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
    
    public SaveLoadGame(){
        
        this.map  = new MapModel[Constant.GRID_SIZE][Constant.GRID_SIZE];  
        
        for(int x=0;x<Constant.GRID_SIZE;x++){
            for(int y=0;y<Constant.GRID_SIZE;y++){
                map[x][y] = new MapModel(); 
            }
        }
        
        this.name = "Player1";
        this.score = 99;
        this.time = 7.0;
        
//        if(saveGame(map,name,score,time)){
//            
//            PlayerData p1 = loadGame();     
//        }
    }
    
    public boolean saveGame(){
        
        return saveGame(this.map, this.name, this.score , this.time);
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
    public PlayerData loadGame(){
        
        PlayerData p1 = null;
        
        try{
            FileInputStream fi = new FileInputStream(new File("game.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            p1 = (PlayerData) oi.readObject();

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
        
        PrintResult(p1);
        return p1;
        
    }

    private void PrintResult(PlayerData p1) {
        
        System.out.println(p1.toString());
        
    }
}
