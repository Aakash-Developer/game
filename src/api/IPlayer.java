package api;

import utils.Tuple;

/**
 * IPlyer class for initializing the player.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */
public interface IPlayer {
    
    //IPlayerModel GetModel();
    //IPlayerView GetView();

     /**
     * Method for fetching the coordination of next move
     * @return Tuple information about x-y position
     */
    public Tuple NextMove();             
}
