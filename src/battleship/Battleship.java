package battleship;

import api.IController;
import api.IPlayer;
import gameengine.OponentMap;
import gameengine.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Battleship class for starting a new game.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 */

public class Battleship extends Application {
   
    public static int gridCellNum = 10;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Battleship Game: Player VS Computer");
        Controller mainController = new Controller();
        Scene scene = new Scene(mainController.mainWindowView.generateGameScene(primaryStage));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    /**
     * Main method, starting point of this application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}




/* HighLevel Requierements

(done) 1.- The controller owns (create and destroy) the Views and Models. 
(done) 2.- The Views knows the instance of the controller and know how to send notifications to it. 
3.- The controller administer the player's turn.
3.1 - In one player game, the user starts first.
(done) 3.1.1 - Create P1 (The user) and P2 (computer) in the ctor.
3.1.2 - The VIEW verify by itself the user position all the ships. The view checks Constant.SHIPS_SIZE to calculate the amount and size of the ships
3.1.3 - The VIEW keeps the START button "disable" until all the ships are positioned.
3.1.4 - The VIEW enable the START button once all the ships are in the map.

3.2 - In two plyer game, the user is selected randomly




*/