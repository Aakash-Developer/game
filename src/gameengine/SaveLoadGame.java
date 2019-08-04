package gameengine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.PlayerData;
import model.StorageFormat;

/**
 * It is responsible for saving and/or loading the data of the game
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
            FileOutputStream fileName = new FileOutputStream("game.dat",false);
            ObjectOutputStream binaryContent = new ObjectOutputStream(fileName);

            binaryContent.writeObject(game);
            binaryContent.close();
            fileName.close();
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
     * It reads a file and convert it into the map model , time, score , 
     * player name 
     * @return boolean True if successful. False otherwise
     */
    public StorageFormat loadGame(){
        
        StorageFormat game = null;
        
        try{
            FileInputStream fileName = new FileInputStream(new File("game.dat"));
            ObjectInputStream binaryContent = new ObjectInputStream(fileName);

            game =  (StorageFormat) binaryContent.readObject();   
            binaryContent.close();
            fileName.close();
            
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
