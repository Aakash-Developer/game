package battleship;

import api.IController;
import api.IPlayer;
import gameengine.OponentMap;
import gameengine.Controller;
import gameengine.Network_UDPServer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The Battleship class is the starting point of the
 * application.
 * @author Team 4
 * @author Zbigniew Ivan Angelus
 * @author Chen-Fang Chung
 * @author Ayush Dave
 * @author Aakash Ahuja
 * @author Pulkit Wadhwa
 * @version build2
 */

public class Battleship extends Application {
   
    public static int gridCellNum = 10;

    /**
     * This method is call to start a new game.
     * @param primaryStage The User Interface context used by JavaFX
     * @throws Exception General exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {        
        primaryStage.setTitle("Battleship Game: Player VS Computer");
        Controller mainController = new Controller();
        mainController.udp_port = 1111;
        mainController.udp_receieve_port = 1113;
        Scene scene = new Scene(mainController.mainWindowView.generateGameScene(primaryStage));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        Network_UDPServer testNetworkPlaying = new Network_UDPServer(mainController);
    }
    
    /**
     * Main method, starting point of this application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}