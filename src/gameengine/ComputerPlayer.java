package gameengine;
import api.Constant;
import api.IPlayer;
import api.IPlayerModel;
import api.IPlayerView;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import gameui.GridMap.MapModel;
import gameui.PlayerView;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import static jdk.nashorn.internal.objects.NativeMath.round;
import utils.Tuple;
import static utils.Validate.IsCoordinateValid;

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
         
        processState = ProcessState.Initial;
        mapModel     = mainController.playerMapView.mapModel;
        nextPosition     = null;
        prevPosition     = null;
        firstHit         = null;
        left             = false;
        right            = false;
        up               = false;
        down             = false;
    }

    int[][] shootingMap;
    ProcessState processState;
    Tuple nextPosition;
    Tuple prevPosition;
    Tuple firstHit;
    MapModel[][] mapModel;
    int counterFirstHit;
    boolean left,right,up,down;
    
    enum ProcessState {
            Initial,
            Random,
            Left,
            Right,
            Up,
            Down,
            Repeat
        }
    
    public Tuple ComputerMove(){

        if(this.prevPosition != null){ //Determine next action from previous move
            
            MapModel cell = mapModel[this.prevPosition.t1][this.prevPosition.t2];
            
            if(cell.IsUncover())
            {
                if(cell.IsSpaceEmpty()){ //change direction/cardinality
                    
                    switch(processState) { //search in oposite direction
                        case Up:
                            processState = ProcessState.Down;
                            break;
                        case Down:
                            processState = ProcessState.Up;
                            break;
                        case Left:
                            processState = ProcessState.Right;
                            break;
                        case Right:
                            processState = ProcessState.Left;
                            break;
                        default:
                            processState = ProcessState.Random;
                            break;  
                    }
                }
                else{   // ship is there , continue same direction
                    
                    if(!cell.ShipInstance.isAlive()){
                        System.out.println("Dead ship!");
                        processState = ProcessState.Initial;
                    }
                    
                    switch(processState) { //Continue searching in the same direction 
                        case Initial:  // first hit, change strategy
                            processState    = ProcessState.Random;
                            break;
                        case Up:
                            processState = ProcessState.Up;
                            break;
                        case Down:
                            processState = ProcessState.Down;
                            break;
                        case Left:
                            processState = ProcessState.Left;
                            break;
                        case Right:
                            processState = ProcessState.Right;
                            break;
                        case Random:  // first hit, change strategy
                            processState    = ProcessState.Right;
                            break;
                        default:
                            processState = ProcessState.Random;
                            break;  
                    }
                    
                }
                
            }
        }
        
        this.nextPosition = null; //initialize a new search
        
        while(this.nextPosition == null){

            switch(processState){

                case Initial:
                    nextPosition = new Tuple(0,0);  //  TEST PURPOSE. TO BE REMOVED
                    processState = ProcessState.Random;
                    break;
                case Up:
                    nextPosition = new Tuple(this.prevPosition.t1, this.prevPosition.t2 + 1);
                    break;
                case Down:
                    nextPosition = new Tuple(this.prevPosition.t1, this.prevPosition.t2 - 1);
                    break;
                case Left:
                    this.left = true;
                    nextPosition = new Tuple(this.prevPosition.t1 - 1, this.prevPosition.t2);
                    break;
                case Right:
                    this.right = true;
                    if(this.left){ //let it go right one step and change axis
                        processState = ProcessState.Down;
                    }
                    else{
                        nextPosition = new Tuple(this.prevPosition.t1 + 1, this.prevPosition.t2);
                    }
                    break;
                case Random:
                    nextPosition = new Tuple(
                            ThreadLocalRandom.current().nextInt(1, this._gridSize + 1), 
                            ThreadLocalRandom.current().nextInt(1, this._gridSize + 1));
                    break;
                default:
                    break;     
            }
            
            if(!IsCoordinateValid(nextPosition)){ //coordinate out of range, change direction
                
                nextPosition = null;
                
                switch(processState) {
                    case Up:
                        processState = ProcessState.Down;
                        break;
                    case Down:
                        processState = ProcessState.Up;
                        break;
                    case Left:
                        processState = ProcessState.Right;
                        break;
                    case Right:
                        processState = ProcessState.Left;
                        break;
                    default:
                        processState = ProcessState.Random;
                    break;  
                }
            }
            else{
                
                MapModel cell = mapModel[this.nextPosition.t1][this.nextPosition.t2];
            
                if(cell.IsUncover()){ //coordinate out of range, change direction
                
                    this.prevPosition = this.nextPosition;
                    nextPosition = null;
                }
            }
        }
        
        this.prevPosition = this.nextPosition;
        
        switch(processState){
            case Up:
                System.out.println("Up:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Down:
                System.out.println("Down:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Left:
                System.out.println("Left:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Right:
                System.out.println("Right:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
            case Random:
                System.out.println("Random:"+this.nextPosition.t1 + ","+this.nextPosition.t2);
                break;
        }
        
        return nextPosition;
    }
    
    public void computerMove() {
        
        while (mainController.computerTurn) {
 
            Tuple nextmove = ComputerMove();
            int x = nextmove.t1;
            int y = nextmove.t2;
            
//            Tuple nextmove = searchEngine.GetNextCoordinate(true,true);
//            int x = (int) nextmove.t1;
//            int y = (int) nextmove.t2;
            
//            int counter=0;
//            for(int i=0;i<10000;i++){
//                counter+=i;
//            }
            
            GridBox selectedGridBox = mainController.playerMapView.getGridBoxByCoordinate(x, y);
            
            if (selectedGridBox.isHitted){
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
