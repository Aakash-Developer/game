package battleship;

import gameengine.ComputerPlayer;
import gameengine.Controller;
import gameengine.ModelView;
import gameui.MainWindow;
import gameui.PlayerMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        
        ComputerPlayer ai = new ComputerPlayer(gridCellNum);
        
        //ModelView ctrl = new ModelView();
        Controller ctrl = new Controller();
        Scene scene = new Scene(ctrl.GetModelView().GetPlayerMap());
                
        primaryStage.setTitle("Battleship Game: Player VS Computer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        
        
        
//        ComputerPlayer ai = new ComputerPlayer(gridCellNum);
//        
//        MainWindow main = new MainWindow((IPlayer) ai);
//        
//        Scene scene = new Scene(main.generateGameScene());
//        
//        primaryStage.setTitle("Battleship Game: Player VS Computer");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
