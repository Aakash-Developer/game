package gameengine;
import api.Constant;
import api.Constant.Turn;
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

/**
* It represents the enemy map from the player's point of view
* in the current game.
*/
public class OponentMap implements IPlayer{
    
    //private final Random _random = new Random();
    private final Controller controller;
    public long startTimeStamp;
    public long endTimeStamp;
    public ComputerPlayer searchEngine;
    public MapModel[][] mapModel;
    public GridMap mapView;
    
    public final int[] ShipsSizeOrderedList = Constant.SHIPS_SIZE;
 
    public OponentMap(Controller injectedController){
        
        this.controller = injectedController;
        this.mapModel       = new MapModel[Constant.GRID_SIZE][Constant.GRID_SIZE];  

        for(int x=0;x<Constant.GRID_SIZE;x++){
            for(int y=0;y<Constant.GRID_SIZE;y++){
                this.mapModel[x][y] = new MapModel(); 
            }
        }
        
        initialize();
    }

    private void initialize() {
        
        mapView = new GridMap(this.mapModel, true, (MouseEvent event) -> {
        
            if(this.controller.turn != Turn.None){
                
                GridBox selectedGridBox = (GridBox) event.getSource();
                endTimeStamp = System.currentTimeMillis();

                double timeDiff = (endTimeStamp - startTimeStamp)/1000;
                String timeSpan = timeDiff + " seconds  ";
                //System.out.println("time diff: "+ timeSpan);

                if(this.mapView.finishIni && (timeDiff < 1000)){
                    controller.mainWindowView.totalTime = controller.mainWindowView.totalTime + timeDiff;
                    controller.mainWindowView.root.setRight(new Text("\n\n\n  "+timeSpan+"\n\n  for this step! "));
                }
                /*if (selectedGridBox.isHitted){
                    return;
                }*/
                controller.mainWindowView.salvoCheckBox = controller.mainWindowView.checkBox1.isSelected();

                if(controller.mainWindowView.salvoCheckBox){ // ----- salvo variation
                    if(selectedGridBox.hitGridBox()){
                        this.mapView.salvoMoveCheck[this.mapView.moveCounter] = true;
                        controller.computerTurn = !checkNextMove(this.mapView.salvoMoveCheck);
                    }
                     this.mapView.moveCounter++;         

                     if(this.mapView.moveCounter >4){
                         controller.computerTurn = true;
                         this.mapView.moveCounter = 0;
                         computerMove();
                     }
                }else{
                    if (selectedGridBox.isHitted){
                        return;
                    }
                    controller.computerTurn = !selectedGridBox.hitGridBox();
                    if (controller.computerTurn){
                        this.mapView.moveCounter = 0;
                        computerMove(); 
                    }
                }

                //mainController.computerTurn = !selectedGridBox.hitGridBox();
                // If there is no ship left on the computer's map, then player wins!
                if (this.mapView.shipsNumOnMap == 0) {
                    //root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
                    controller.mainWindowView.displayMessage(2);
                }

                System.out.println("Player->"+selectedGridBox.pos_x + ","+selectedGridBox.pos_y);

            }
        });
    }
    
    public void placeShipsRandomly() {
        
        int numOfShips = ShipsSizeOrderedList.length;
        while (numOfShips > 0) {

            Tuple possibleCoordinate = NextMove();
                       
            Ship ship = new Ship(ShipsSizeOrderedList[numOfShips-1] , Math.random() < 0.5 );
            
            if (controller.oponentView.TryToPlaceShipOnModel(ship, possibleCoordinate.t1, possibleCoordinate.t2)) {
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
        
        while (this.controller.computerTurn) {
 
            Tuple nextmove = this.controller.ai.getNextComputerMove();
            int x = nextmove.t1;
            int y = nextmove.t2;
            
            GridBox selectedGridBox = controller.playerView.getGridBoxByCoordinate(x, y);
            
            if (selectedGridBox.isHitted){
                continue;
            }
                
            if(controller.mainWindowView.salvoCheckBox){
                controller.playerView.salvoMoveCheck[controller.playerView.moveCounter] = selectedGridBox.hitGridBox();
                controller.playerView.moveCounter++;
                
                if(controller.playerView.moveCounter >4){
                    controller.playerView.moveCounter = 0;
                    controller.computerTurn = false;
                }
            }else{
                
                controller.computerTurn = selectedGridBox.hitGridBox();
                
            }
            
            //mainController.computerTurn = selectedGridBox.hitGridBox();
            
            // If there is no ship left on the player's map, then computer wins!
            if (controller.playerView.shipsNumOnMap == 0) {
                //root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
                controller.mainWindowView.displayMessage(4);
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
