package gameui;

import api.IPlayer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Tuple;

/**
 * MainWindow class creates the main UI window of the game.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */
public class MainWindow {
    private Stage primaryStageObj;
    private BorderPane root;
    private boolean isPlayer2Turn = false;
    private GridMap computerMap, playerMap;
    private final int gridCellNum = 10;
    
    private final int[] hitCapacityType = {2,3,3,4,5};
    private int iniShipsToPlace;
    private boolean computerTurn = false;

    private Random random = new Random();
    Button startButton,exitButton;
    
    IPlayer _computerPlayer;
    
    /**
     * MainWindow class generates the main UI window of the game.
     * @param computerPlayer
     */
    public MainWindow(IPlayer computerPlayer){
     
        _computerPlayer = computerPlayer;
        iniShipsToPlace = _computerPlayer.GetNumberOfShips();
    }
    
    /**
     * "generateGameScene" method creates main scene in the main window
     * @param StageScene
     * @return
     */
    public Parent generateGameScene(Stage StageScene){
        primaryStageObj = StageScene;
        root = new BorderPane();
        root.setPrefSize(660, 680);
        root.setStyle("-fx-background-color: #d3ecff;");
        // Initialize the insturctions for the game.
        Text textbox = new Text();
        textbox.setText("  Instructions:\n\n"
                       +"  1. Place 5 battle ships in the player's (bottom one) grid map.\n\n"
                       +"  2. Noted that \"left click\" of mouse is for vertical placement of a ship,\n"
                       +"      and \"right click\" of mouse is for horizontal placement of a ship.\n\n"
                       +"  3. At least one space in between for each ship.\n\n"
                       +"  4. The ships you place have the order of the hit capacity as 5, 4, 3, 3, 2.\n\n"
                       +"  5. Click the box in the computer's grid map (upper one) to start a game.\n\n"
                       +"  6. Blue: Unhitted spot, Black: missed spot, Red: hitted spot.");
                        
        root.setRight(new Text("\n\n\n\n\n                                  "));
        // Initialize the grid map for computer side
        computerMap = new GridMap(true, event -> {
            if (!isPlayer2Turn){
                return;
            }
            
            GridMap.GridBox selectedGridBox = (GridMap.GridBox) event.getSource();
            if (selectedGridBox.isHitted){
                return;
            }

            computerTurn = !selectedGridBox.hitGridBox();
            // If there is no ship left on the computer's map, then player wins!
            if (computerMap.shipsNumOnMap == 0) {
                root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
            }

            if (computerTurn){
                computerMove(); 
            }
        },gridCellNum);
        
        // A message for reminding a player to start a game
        root.setLeft(new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n       The game has started.\n\n"
                                       +"     Please click the buttom map\n"
                                       +"     for placing the ships!     "));
        
        // Initialize the grid map for player side
        playerMap = new GridMap(false, event -> {
            if (isPlayer2Turn){
                return;
            }
            
            GridMap.GridBox selectedGridBox = (GridMap.GridBox) event.getSource();
            
            if (playerMap.placingBattleShipOn_X_Y(new Ship(hitCapacityType[iniShipsToPlace-1], 
                    event.getButton() == MouseButton.PRIMARY), 
                    selectedGridBox.pos_x, selectedGridBox.pos_y)) {
                if (--iniShipsToPlace == 0) {
                    root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                            + "             Player's move             "));
                    iniComputerSide();
                }
            }
            
        },gridCellNum);
        // Initialize buttons for restart and exit the game.
        startButton = new Button("Start A New Game");
        startButton.setWrapText(true);
        exitButton = new Button("Leave the Game");
        exitButton.setWrapText(true);
        startButton.setOnAction(actionEvent -> {
        
            try {
                //restart(primaryStage2);
                restart();
            } catch (Exception ex) {
                Logger.getLogger("BattleshipError").log(Level.SEVERE, null, ex);
            }
        });
        exitButton.setOnAction(actionEvent -> {
            System.exit(0);
        });
        // Setting the instruction area in the main game waindow
        HBox instructionArea = new HBox();       
        instructionArea.getChildren().add(textbox);        
        root.setTop(instructionArea);
        // Setting the message area in the main game waindow
        HBox messageArea = new HBox();
        messageArea.getChildren().addAll(startButton,exitButton);
        root.setBottom(messageArea);
        // Setting the grid maps area in the main game waindow
        VBox mapGridsArea = new VBox(20, computerMap, playerMap);
        mapGridsArea.setAlignment(Pos.CENTER);
        root.setCenter(mapGridsArea);
        
        Text upperMessageBox = new Text();
        upperMessageBox.setText("Computer Map: ");
        upperMessageBox.setFont(Font.font ("Arial", 16));
        upperMessageBox.setFill(Color.DARKBLUE);
        Text lowerMessageBox = new Text();
        lowerMessageBox.setText("Player Map: ");
        lowerMessageBox.setFont(Font.font ("Arial", 16));
        lowerMessageBox.setFill(Color.CADETBLUE);
        VBox mapGridsArea2 = new VBox(115, upperMessageBox, lowerMessageBox);
        VBox.setMargin(upperMessageBox, new Insets(20, 20, 80, 20));
        VBox.setMargin(lowerMessageBox, new Insets(20, 20, 80, 20));
        HBox centerArea = new HBox();
        centerArea.getChildren().add(mapGridsArea2);
        centerArea.getChildren().add(mapGridsArea);
        centerArea.setAlignment(Pos.CENTER);
        root.setCenter(centerArea);
        
        return root;
    }
    
    private void computerMove() {
        
        while (computerTurn) {
            
            Tuple nextmove = _computerPlayer.NextMove();
            int x = (int) nextmove.t1;
            int y = (int) nextmove.t2;
            
            int counter=0;
            for(int i=0;i<10000;i++){
                counter+=i;
            }
            
            GridMap.GridBox selectedGridBox = playerMap.getGridBoxByCoordinate(x, y);
            if (selectedGridBox.isHitted)
                continue;
            
            computerTurn = selectedGridBox.hitGridBox();
            // If there is no ship left on the player's map, then computer wins!
            if (playerMap.shipsNumOnMap == 0) {
                root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
            }
        }
    }
    
    private void restart() throws Exception {
        cleanup();
        Stage newPrimaryStage = new Stage();
        newPrimaryStage.setTitle("Battleship Game: Player VS Computer");
        primaryStageObj.close();
        Scene scene = new Scene(generateGameScene(newPrimaryStage));
        newPrimaryStage.setScene(scene);
        newPrimaryStage.setResizable(false);
        newPrimaryStage.show();
    }
    
     /**
      * method for re-initializing the parameters
      */
    private void cleanup() {
        isPlayer2Turn = false;
        iniShipsToPlace = _computerPlayer.GetNumberOfShips();
        computerTurn = false;
        computerMap = playerMap = null;
        random = new Random();
    }
    
    private void iniComputerSide() {
        // computer's turn of placing ships
        int type = _computerPlayer.GetNumberOfShips();
        while (type > 0) {
            
            Tuple coordinates = _computerPlayer.NextMove();
            int pos_x = (int) coordinates.t1;
            int pos_y = (int) coordinates.t2;
            
            if (computerMap.placingBattleShipOn_X_Y(new Ship(hitCapacityType[type-1], Math.random() < 0.5), pos_x, pos_y)) {
                type--;
            }
        }
        
        isPlayer2Turn = true;
    }
}
