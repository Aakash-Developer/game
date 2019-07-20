package gameengine;
import api.Constant;
import api.IPlayer;
import api.IPlayerModel;
import api.IPlayerView;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import gameui.PlayerView;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static jdk.nashorn.internal.objects.NativeMath.round;
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

public class ComputerPlayer implements IPlayer{
    
    private int _gridSize=Constant.gridCellNum;
    private Random _random = new Random();
    //private final int totalShips = Constant.totalShips;
    private Controller mainController;
    public long startTimeStamp;
    public long endTimeStamp;
    public SearchEngine searchEngine;
    //public ComputerPlayer(IPlayerModel model, IPlayerView view) {
    //    super(model, view);
    //}
    
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
        
        //int a = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        //int b = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        int a = _random.nextInt(_gridSize);
        int b = _random.nextInt(_gridSize);

        return new Tuple(a, b);
    }
    
    public Tuple NextMove(int status, boolean wasSank) {
        
        //int a = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        //int b = ThreadLocalRandom.current().nextInt(0, this.model.GetController().GetGridSize() + 1);
        int a = _random.nextInt(_gridSize);
        int b = _random.nextInt(_gridSize);

        return new Tuple(a, b);
    }
    
    /**
     * Method of getting the number of ships for computer player.
     * @return
     */
//    @Override
//    public int GetNumberOfShips() {
//        return Constant.totalShips;
//    }
    
    // Initialize the grid map for computer side
    public GridMap computerMapView = new GridMap(true, (MouseEvent event) -> {
        
        if (!mainController.isPlayer2Turn){
            return;
        }

        GridBox selectedGridBox = (GridBox) event.getSource();
        endTimeStamp = System.currentTimeMillis();
        
        double timeDiff = (endTimeStamp - startTimeStamp)/1000;
        String timeSpan = timeDiff + " seconds  ";
        System.out.println("time diff: "+ timeSpan);
        
        if(this.computerMapView.finishIni && (timeDiff < 1000)){
            mainController.mainWindowView.totalTime = mainController.mainWindowView.totalTime + timeDiff;
            mainController.mainWindowView.root.setRight(new Text("\n\n\n  "+timeSpan+"\n\n  for this step! "));
        }
        /*if (selectedGridBox.isHitted){
            return;
        }*/
        mainController.mainWindowView.salvoCheckBox = mainController.mainWindowView.checkBox1.isSelected();
            if(mainController.mainWindowView.salvoCheckBox){
                if(selectedGridBox.hitGridBox()){
                    this.computerMapView.salvoMoveCheck[this.computerMapView.moveCounter] = true;
                    mainController.computerTurn = !checkNextMove(this.computerMapView.salvoMoveCheck);
                }
                 this.computerMapView.moveCounter++;         
                 
                 if(this.computerMapView.moveCounter >4){
                     mainController.computerTurn = true;
                     this.computerMapView.moveCounter = 0;
                     computerMove();
                 }
            }else{
                if (selectedGridBox.isHitted){
                    return;
                }
                mainController.computerTurn = !selectedGridBox.hitGridBox();
                if (mainController.computerTurn){
                    this.computerMapView.moveCounter = 0;
                    computerMove(); 
                }
            }

        //mainController.computerTurn = !selectedGridBox.hitGridBox();
        // If there is no ship left on the computer's map, then player wins!
        if (this.computerMapView.shipsNumOnMap == 0) {
            //root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
            mainController.mainWindowView.displayMessage(2);
        }
        
        /*if (mainController.computerTurn){
            computerMove(); 
        }*/
    },_gridSize);
    
    
    
    /**
     * Method for initiating a computer player.
     * 
     * @param injectedController
     */
    public ComputerPlayer(Controller injectedController){
        this.mainController = injectedController;
        this._gridSize = injectedController.gridCellNum;
        this.shootingMap = new int[this._gridSize ][this._gridSize ];
         searchEngine = new SearchEngine(this._gridSize, shootingMap);
    }

    int[][] shootingMap;
    
    public void computerMove() {
        
        while (mainController.computerTurn) {
 
//            Tuple nextmove = mainController.ai.NextMove();
//            int x = (int) nextmove.t1;
//            int y = (int) nextmove.t2;
            
            Tuple nextmove = searchEngine.GetNextCoordinate(true,true);
            int x = (int) nextmove.t1;
            int y = (int) nextmove.t2;
            
//            int counter=0;
//            for(int i=0;i<10000;i++){
//                counter+=i;
//            }
            
            GridBox selectedGridBox = mainController.playerMapView.getGridBoxByCoordinate(x, y);
            
            if (selectedGridBox.isHitted){
                
                System.out.println("Ship has been hit");
                
                if(selectedGridBox.ship.isAlive()){
                    System.out.println("Ship is alive");
                }
                else{
                    System.out.println("Ship Sank");
                }
                
                continue;
            }
                
            if(mainController.mainWindowView.salvoCheckBox){
                mainController.playerMapView.salvoMoveCheck[mainController.playerMapView.moveCounter] = selectedGridBox.hitGridBox();
                mainController.playerMapView.moveCounter++;
                
                if(mainController.playerMapView.moveCounter >4){
                    mainController.playerMapView.moveCounter = 0;
                    mainController.computerTurn = false;
                }
            }else{
                mainController.computerTurn = selectedGridBox.hitGridBox();
            }
            
            //mainController.computerTurn = selectedGridBox.hitGridBox();
            
            // If there is no ship left on the player's map, then computer wins!
            if (mainController.playerMapView.shipsNumOnMap == 0) {
                //root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
                mainController.mainWindowView.displayMessage(4);
            }
        }
        startTimeStamp = System.currentTimeMillis();
    }
    
    private boolean checkNextMove(boolean[] inputArr){
        for(int i=0;i<inputArr.length;i++){
            if(inputArr[i]){
                return inputArr[i];
            }
        }
        return false;
    }
}
