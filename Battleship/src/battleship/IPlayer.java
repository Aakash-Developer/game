package battleship;
import utils.Tuple;
/*
 * @author Team 4
 * @Zbigniew Ivan Angelus
 * @Chen-Fang Chung
 * @Ayush Dave
 * @Aakash Ahuja
 * @Pulkit Wadhwa
 */
public interface IPlayer {
    
    /**
    @return the coordinates for the next move
    */ 
    public Tuple NextMove();
    
    /**
    @informas the player its move hit a ship
    */ 
    public void Hit();
    /**
    @informs the player it completely destroyed the ship.
    */ 
    public void ShipWasDestroyed();
    
    /**
    @return the number of ships
    */ 
    public int GetNumberOfShips();
}
