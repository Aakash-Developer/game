package model;
import api.Constant;

/**
 * This class holds the model of the game in the MVC architecture being used
 * @author Team4
 */
public class MapModel{

        //properties
        public Constant.Space space       = Constant.Space.IsEmpty;
        public Constant.Uncover uncover   = Constant.Uncover.No;
        public Ship ShipInstance = null; //set and get the ship instance
        public int visitCounter  = 0;

        
        public boolean IsSpaceEmpty(){
            
            return space == Constant.Space.IsEmpty;
        }
        
        public boolean IsUncover(){
            
            return uncover == Constant.Uncover.Yes;
        }

        //Attacking mode or seen from attrackers perspective
        public boolean IsShipAlive(){
            
            if(this.ShipInstance!=null){
                return this.ShipInstance.isAlive();
            }
            
            return false;
        }
        
        public boolean IsShipFound(){
            
            return space    == Constant.Space.IsShip && 
                   uncover  == Constant.Uncover.Yes;
        }
        
        public boolean IsAMissedHit(){
            
            return space    == Constant.Space.IsEmpty &&
                   uncover  == Constant.Uncover.Yes;
        }
        
        //Player mode, setting the ships in the map
        public void SetAShip(Ship ship){
            
            this.ShipInstance   = ship;
            this.space          = Constant.Space.IsShip;
            this.uncover        = Constant.Uncover.No;
        }
        
        public String space(){
            
            return space.equals(Constant.Space.IsEmpty)?" ":"s";
            
        }
        
        public String uncover(){
            
            return uncover.equals(Constant.Uncover.No)?" ":"u";
            
        }
    }