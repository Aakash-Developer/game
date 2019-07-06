package battleship;

import gameengine.ComputerPlayer;
import gameui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * @author Team 4
 * @Zbigniew Ivan Angelus
 * @Chen-Fang Chung
 * @Ayush Dave
 * @Aakash Ahuja
 * @Pulkit Wadhwa
 */

public class Battleship extends Application {
   
    private final int gridCellNum = 10;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Battleship Game: Player VS Computer");
        ComputerPlayer ai = new ComputerPlayer(gridCellNum);
        MainWindow main = new MainWindow((IPlayer) ai);
        Scene scene = new Scene(main.generateGameScene());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
