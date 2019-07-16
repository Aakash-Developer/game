
package gameui;

import api.IBinding;
import api.IController;
import api.IPlayer;
import api.IPlayerView;
import gameengine.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * "View" module.
 * @author zange
 */
public class PlayerView implements IPlayerView, IBinding {
    
    Controller controller;
    GridMap playerMapView;
    GridMap computerMapView;
    Button startButton,exitButton;
    
    private Stage primaryStageObj;
    private BorderPane root;
    
    //public PlayerView(Controller injectedController, IPlayer player){
    public PlayerView(Controller injectedController){
        this.controller = injectedController;
        this.playerMapView = injectedController.playerMapView;
        this.computerMapView = injectedController.computerMapView;
    }
    
    private void buildGridMap(){
        
    }

    @Override
    public void OnMouseLeftClickedInEnemyGrid() {
        controller.OnMouseLeftClickedInEnemyGrid();
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
        
        // A message for reminding a player to start a game
        root.setLeft(new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n       The game has started.\n\n"
                                       +"     Please click the buttom map\n"
                                       +"     for placing the ships!     "));
        
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
        VBox mapGridsArea = new VBox(20, computerMapView, playerMapView);
        mapGridsArea.setAlignment(Pos.CENTER);
        root.setCenter(mapGridsArea);
        displayMessage(1);
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
    
    private void restart() throws Exception {
        Stage newPrimaryStage = new Stage();
        newPrimaryStage.setTitle("Battleship Game: Player VS Computer");
        primaryStageObj.close();
        Controller newMainController = new Controller();
        Scene scene = new Scene(newMainController.mainWindowView.generateGameScene(newPrimaryStage));
        newPrimaryStage.setScene(scene);
        newPrimaryStage.setResizable(false);
        newPrimaryStage.show();
    }
    
    public void displayMessage(int messageType){
        switch (messageType){
            case 1:
                root.setLeft(new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n       The game has started.\n\n"
                                   +"     Please click the buttom map\n"
                                   +"     for placing the ships!     "));
                break;
            case 2:
                root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! "));
                break;
            case 3:
                root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                        + "             Player's move             "));
                break;
        }
    }
}
