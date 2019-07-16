package battleship;

import api.IPlayer;
import gameengine.ComputerPlayer;
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
        //ComputerPlayer ai = new ComputerPlayer(gridCellNum);
        //MainWindow main = new MainWindow((IPlayer) ai);
        //Scene scene = new Scene(main.generateGameScene(primaryStage));
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
