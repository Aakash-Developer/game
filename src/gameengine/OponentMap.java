package gameengine;
import api.Constant;
import api.IPlayer;
import gameui.GridMap;
import gameui.GridMap.GridBox;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.MapModel;
import model.Ship;
import utils.PrettyPrint;
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

public class OponentMap implements IPlayer{
    
    //private final Random _random = new Random();
    private final Controller mainController;
    public long startTimeStamp;
    public long endTimeStamp;
    public ComputerPlayer searchEngine;
    public MapModel[][] mapModel;
    public GridMap mapView;
    
    public final int[] ShipsSizeOrderedList = Constant.SHIPS_SIZE;
    /*
    ProcessState processState;
    Tuple nextPosition;
    Tuple prevPosition;
    Tuple firstHit;
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
    */
    
    public OponentMap(Controller injectedController){
        
        this.mainController = injectedController;
        this.mapModel       = new MapModel[Constant.GRID_SIZE][Constant.GRID_SIZE];  

        for(int x=0;x<Constant.GRID_SIZE;x++){
            for(int y=0;y<Constant.GRID_SIZE;y++){
                this.mapModel[x][y] = new MapModel(); 
            }
        }
        
        /*
        processState     = ProcessState.Initial;
        nextPosition     = null;
        prevPosition     = null;
        firstHit         = null;
        left             = false;
        right            = false;
        up               = false;
        down             = false;
        */
        
        initialize();
    }

    private void initialize() {
        
        mapView = new GridMap(this.mapModel, true, (MouseEvent event) -> {
        
            GridBox selectedGridBox = (GridBox) event.getSource();
            endTimeStamp = System.currentTimeMillis();

            double timeDiff = (endTimeStamp - startTimeStamp)/1000;
            String timeSpan = timeDiff + " seconds  ";
            //System.out.println("time diff: "+ timeSpan);

            if(this.mapView.finishIni && (timeDiff < 1000)){
                mainController.mainWindowView.totalTime = mainController.mainWindowView.totalTime + timeDiff;
                mainController.mainWindowView.root.setRight(new Text("\n\n\n  "+timeSpan+"\n\n  for this step! "));
            }
            /*if (selectedGridBox.isHitted){
                return;
            }*/
            mainController.mainWindowView.salvoCheckBox = mainController.mainWindowView.checkBox1.isSelected();
            
            if(mainController.mainWindowView.salvoCheckBox){ // ----- salvo variation
                if(selectedGridBox.hitGridBox()){
                    this.mapView.salvoMoveCheck[this.mapView.moveCounter] = true;
                    mainController.computerTurn = !checkNextMove(this.mapView.salvoMoveCheck);
                }
                 this.mapView.moveCounter++;         

                 if(this.mapView.moveCounter >4){
                     mainController.computerTurn = true;
                     this.mapView.moveCounter = 0;
                     computerMove();
                 }
            }else{
                if (selectedGridBox.isHitted){
                    return;
                }
                mainController.computerTurn = !selectedGridBox.hitGridBox();
                if (mainController.computerTurn){
                    this.mapView.moveCounter = 0;
                    computerMove(); 
                }
            }

            //mainController.computerTurn = !selectedGridBox.hitGridBox();
            // If there is no ship left on the computer's map, then player wins!
            if (this.mapView.shipsNumOnMap == 0) {
                //root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
                mainController.mainWindowView.displayMessage(2);
            }
        });
    }
    
    public void placeShipsRandomly() {
        
        int numOfShips = ShipsSizeOrderedList.length;
        while (numOfShips > 0) {

            Tuple possibleCoordinate = NextMove();
                       
            Ship ship = new Ship(ShipsSizeOrderedList[numOfShips-1] , Math.random() < 0.5 );
            
            if (mainController.oponentView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2)) {
                numOfShips--;
            }
        }
        
        PrettyPrint.shipsInModel("computer", mapModel);
        
        this.mapView.applyModelToView();
        this.mapView.finishIni = true;
    }

    @Override
    public Tuple NextMove() {
        
        return new Tuple(ThreadLocalRandom.current().nextInt(0, Constant.GRID_SIZE + 1),
                         ThreadLocalRandom.current().nextInt(0, Constant.GRID_SIZE + 1));
    }
       
    
    public void computerMove() {
        
        while (this.mainController.computerTurn) {
 
            Tuple nextmove = this.mainController.ai.getNextComputerMove();
            int x = nextmove.t1;
            int y = nextmove.t2;
            
            GridBox selectedGridBox = mainController.playerView.getGridBoxByCoordinate(x, y);
            
            if (selectedGridBox.isHitted){
                continue;
            }
                
            if(mainController.mainWindowView.salvoCheckBox){
                mainController.playerView.salvoMoveCheck[mainController.playerView.moveCounter] = selectedGridBox.hitGridBox();
                mainController.playerView.moveCounter++;
                
                if(mainController.playerView.moveCounter >4){
                    mainController.playerView.moveCounter = 0;
                    mainController.computerTurn = false;
                }
            }else{
                
                mainController.computerTurn = selectedGridBox.hitGridBox();
                
            }
            
            //mainController.computerTurn = selectedGridBox.hitGridBox();
            
            // If there is no ship left on the player's map, then computer wins!
            if (mainController.playerView.shipsNumOnMap == 0) {
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
    
    
    /*
    public Tuple getNextComputerMove(){

        PrettyPrint.uncoverInModel("move", mapModel);
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
                        continue;
                    }
                    else{
                        nextPosition = new Tuple(this.prevPosition.t1 + 1, this.prevPosition.t2);
                    }
                    break;
                case Random:
                    nextPosition = new Tuple(
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1), 
                            ThreadLocalRandom.current().nextInt(1, Constant.GRID_SIZE + 1));
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
    */

}
