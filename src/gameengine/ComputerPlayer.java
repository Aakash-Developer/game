package gameengine;
import api.Constant;
import api.IPlayerModel;
import api.IPlayerView;
import gameui.PlayerView;
import java.util.concurrent.ThreadLocalRandom;
import utils.Tuple;

/**
 * ComputerPlayer class implements IPlayer for computer player 
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */

public class ComputerPlayer extends Player {

    public ComputerPlayer(IPlayerModel model, IPlayerView view) {
        super(model, view);
    }
    
    /*
    public void Initialize(){
        
        PositionShipsOnTheMap();
        PlayerView pv = (PlayerView)this.GetView();
        pv.OnButtonStartClicked();
    }
          
    private void PositionShipsOnTheMap(){
         
         int[][] example = new int[][]{ 
             {1,0,0,0,0,0,0,0,0,1,1,1},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {1,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,1,1},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {0,0,0,0,0,0,0,0,0,0,0,0},
             {1,1,1,1,0,0,0,0,0,1,1,1},
         };
         
         this.model.SetPlayerMap(example);
     }
     */
    
    /**
     * Method of making the next move for computer player.
     * @return
     */
    @Override
    public Tuple NextMove() {
        
        int a = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        int b = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        
        return new Tuple(a, b);
    }
    
    /**
     * Method of getting the number of ships for computer player.
     * @return
     */
    @Override
    public int GetNumberOfShips() {
        return Constant.totalShips;
    }
    
    

}
