
package gameui;

import api.Constant;
import api.IController;
import gameengine.Controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.RectangleShipObj;
import model.Ship;
/**
 *
 * @author zange
 */
//public class PlayerView extends PlayerViewAbstract{
public class PlayerView{
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    RectangleShipObj two;
    
    RectangleShipObj three1,three2, four, five;
    Bounds b1;
    Bounds b2;
    Bounds rp_bound;
    
    Controller controller;
    GridMap playerMapView;
    GridMap computerMapView;
    Button startButton,exitButton; //,loadGameButton,saveGameButton;
    public String userName;
    public Text updatableTextBox;
    public CheckBox checkBox1;
    public boolean salvoCheckBox = false;
    private Stage primaryStageObj;
    public BorderPane root;
    public double totalTime = 0;
    public int finalScore = 0;
    
    EventHandler<MouseEvent> rectangleOnMousePressedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
            orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
            Rectangle r = (Rectangle) (t.getSource());
            r.toFront();
        }
    };
     
    EventHandler<MouseEvent> rectangleOnMouseDraggedEventHandler = 
        new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent t) {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;
             
            ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
            ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
            
            //new added

            
            
        }
    };
    
    /*public PlayerView(IController controller) {
        super(controller);

    }*/
    
    public PlayerView(Controller injectedController){
        this.controller = injectedController;
        this.playerMapView = injectedController.playerMapView;
        this.computerMapView = injectedController.computerMapView;
    }
    /*
     public void SimulateUserEnterTheValuesAndClickedStart(){
        
        //example to ilustrate that the player finish positioning the ships
        //and the view generates a matrix with the ships position. The user then
        //clicks start to send this information to the controller.
        
        this.player_map = new int[][]{ 
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
        
        this.OnButtonStartClicked();
    }
    */
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
        // Setting the instruction area in the main game waindow
        HBox instructionArea = new HBox();       
        //instructionArea.getChildren().add(textbox);
        
        
        
        checkBox1 = new CheckBox("Advanced Game (Salvo variation)");
        Text textboxInSecondCol = new Text();
        textboxInSecondCol.setText("This is 'Advanced' game!    ");
        VBox theFirstColum = new VBox(textbox);
        VBox theSecondColum = new VBox(20,checkBox1,textboxInSecondCol);
        //theSecondColum.getChildren().addAll(textboxInSecondCol,checkBox1);
        //instructionArea.getChildren().add(checkBox1);
        //instructionArea.getChildren().add(theSecondColum);
        //instructionArea.getChildren().addAll(textbox,theSecondColum);
        instructionArea.getChildren().addAll(theFirstColum,theSecondColum);
        salvoCheckBox = checkBox1.isSelected();
        
        root.setTop(instructionArea);
        if(salvoCheckBox){
            textboxInSecondCol.setText("\n\n\n\n\n      This is 'Advanced' game!    ");
        }else{
            textboxInSecondCol.setText("\n\n\n\n\n                                  ");
        }
        
        
        // A message for reminding a player to start a game
        root.setLeft(new Text("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n       The game has started.\n\n"
                                       +"     Please click the buttom map\n"
                                       +"     for placing the ships!     "));
        
        
        // Initialize buttons for restart and exit the game.
        startButton = new Button("Start A New Game");
        startButton.setWrapText(true);
        exitButton = new Button("Leave the Game");
        exitButton.setWrapText(true);
        //loadGameButton = new Button("Load A Game");
        //loadGameButton.setWrapText(true);
        //saveGameButton = new Button("Save the Game");
        //saveGameButton.setWrapText(true);
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
        
        // Setting the message area in the main game waindow
        HBox messageArea = new HBox(3);
        messageArea.getChildren().addAll(startButton,exitButton);
        //messageArea.getChildren().addAll(startButton,exitButton,loadGameButton,saveGameButton);
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
        
        updatableTextBox = new Text();
        updatableTextBox.setText("\n\n\n                                ");
        root.setRight(updatableTextBox);
        root.setRight(new Text("\n\n\n       seconds  \n\n  for this step! "));
        
        VBox mapGridsArea2 = new VBox(115, upperMessageBox, lowerMessageBox);
        VBox.setMargin(upperMessageBox, new Insets(20, 20, 80, 20));
        VBox.setMargin(lowerMessageBox, new Insets(20, 20, 80, 20));
        HBox centerArea = new HBox();
        centerArea.getChildren().add(mapGridsArea2);
        centerArea.getChildren().add(mapGridsArea);
        centerArea.setAlignment(Pos.CENTER);
        root.setCenter(centerArea);
        //long endTime = 0;
        
        TextInputDialog dialog = new TextInputDialog("Kevin");
        dialog.setTitle("Welcome to BattleShip!");
        dialog.setHeaderText("Please Enter your name:");
        dialog.setContentText("Name:");
 
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(name -> {
            this.userName=name;
            primaryStageObj.setTitle("Battleship Game: "+this.userName+" VS Computer");
        });
        System.out.print("userName: "+userName);
        
        CreateRectangles();
        root.getChildren().addAll(two,three1,three2, four, five);
        
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
                if(salvoCheckBox){
                    totalTime = totalTime/2;
                    finalScore = (int) ((int) 10000/totalTime + 23);
                }else{
                    finalScore = (int) ((int) 10000/totalTime + 23);
                }
                root.setLeft(new Text("\n\n\n\n\n       Nice...You WIN the Battle!! \n\n"
                        + "   Your total time is: "+totalTime+" seconds\n\n"
                        + "       Your score is: "+finalScore));
                Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
                winAlert.setTitle("YOU WIN");
                winAlert.setHeaderText(null);
                winAlert.setContentText("You win the game!!\nYour total time is: "+totalTime+" seconds.\n"
                                        + "Your score is: "+finalScore);
                winAlert.showAndWait();
                break;
            case 3:
                root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                        + "             Player's move             "));
                break;
            case 4:
                root.setLeft(new Text("\n\n\n\n\n      Sorry...You LOST the Battle!!"));
                break;
            case 5:
                root.setRight(new Text("\n\n\n  Advanced game!  \n\n  5 hits at a time!  "));
                break;
            case 6:
                root.setRight(new Text("\n\n\n                                \n"));
                break;
        }
    }
    
    private void CreateRectangles(){
       
        two = new RectangleShipObj(16,(20*2-4),Color.RED);
        two.setCursor(Cursor.HAND);
        //two.setCursor(Cursor.MOVE);
        two.setTranslateX(10);
        two.setTranslateY(250);
        two.setOnMousePressed(rectangleOnMousePressedEventHandler);
        two.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        
        two.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            
            if (e.getButton() == MouseButton.SECONDARY) {
                //System.out.println("secondary button clicked");
                two.ChangeState();
            }
            
            RectangleShipObj r = ((RectangleShipObj)(e.getSource()));
            Bounds rb = r.getBoundsInParent();
            rp_bound = playerMapView.getBoundsInLocal();
            
            double sence_X = rb.getMinX();
            double sence_Y = rb.getMinY();
            double sence_sX = r.getScene().getX();
            double sence_sY = r.getScene().getY();
            
            //System.out.println("!!-->"+rb.toString());
            //System.out.println("! rp_bound->"+rp_bound.toString());
            
            if(Constant.gridCellNum==10){
                if(two.isVertical){
                    if(sence_X>356 && sence_X<(567-15) && sence_Y>443 && sence_Y < (650-35)){
                        r.ValidState();
                        //System.out.println("in-->");
                        //System.out.println("box_x: "+(int)((sence_X-345)/21));
                        //System.out.println("box_y: "+(int)((sence_Y-443)/21));
                    }
                    else{
                        r.InvalidState();
                        //System.out.println("out-->");
                    }
                }else{
                    if(sence_X>356 && sence_X<(567-35) && sence_Y>443 && sence_Y < (650-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }

                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    two.isVertical), 
                    (int)((sence_X-356)/21), (int)((sence_Y-443)/21))) {
                    //System.out.println("Place ship at: "+computerSelectedGridBox.abs_pos_x+", "+computerSelectedGridBox.abs_pos_y);
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }else if(Constant.gridCellNum==12){
                if(two.isVertical){
                    if(sence_X>345 && sence_X<(595-15) && sence_Y>474 && sence_Y < (725-35)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>345 && sence_X<(595-35) && sence_Y>474 && sence_Y < (725-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }

                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    two.isVertical), 
                    (int)((sence_X-345)/21), (int)((sence_Y-474)/21))) {
                    //System.out.println("Place ship at: "+computerSelectedGridBox.abs_pos_x+", "+computerSelectedGridBox.abs_pos_y);
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }
        });
        
        three1 = new RectangleShipObj(16,(20*3-4),Color.RED);
        three1.setCursor(Cursor.HAND);
        three1.setTranslateX(30);
        three1.setTranslateY(250);
        three1.setOnMousePressed(rectangleOnMousePressedEventHandler);
        three1.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        three1.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            RectangleShipObj r = ((RectangleShipObj)(e.getSource()));
            Bounds rb = r.getBoundsInParent();
            rp_bound = playerMapView.getBoundsInLocal();
            if (e.getButton() == MouseButton.SECONDARY) {
                three1.ChangeState();
            }
            double sence_X = rb.getMinX();
            double sence_Y = rb.getMinY();
            double sence_sX = r.getScene().getX();
            double sence_sY = r.getScene().getY();
            if(Constant.gridCellNum==10){
                if(three1.isVertical){
                    if(sence_X>356 && sence_X<(567-15) && sence_Y>443 && sence_Y < (650-54)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>356 && sence_X<(567-54) && sence_Y>443 && sence_Y < (650-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    three1.isVertical), 
                    (int)((sence_X-356)/21), (int)((sence_Y-443)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }else if(Constant.gridCellNum==12){
                if(three1.isVertical){
                    if(sence_X>345 && sence_X<(595-15) && sence_Y>474 && sence_Y < (725-54)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>345 && sence_X<(595-54) && sence_Y>474 && sence_Y < (725-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    three1.isVertical), 
                    (int)((sence_X-345)/21), (int)((sence_Y-474)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }
        });
        
        three2 = new RectangleShipObj(16,(20*3-4),Color.RED);
        three2.setCursor(Cursor.HAND);
        three2.setTranslateX(50);
        three2.setTranslateY(250);
        three2.setOnMousePressed(rectangleOnMousePressedEventHandler);
        three2.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        three2.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            RectangleShipObj r = ((RectangleShipObj)(e.getSource()));
            Bounds rb = r.getBoundsInParent();
            rp_bound = playerMapView.getBoundsInLocal();
            if (e.getButton() == MouseButton.SECONDARY) {
                three2.ChangeState();
            }
            double sence_X = rb.getMinX();
            double sence_Y = rb.getMinY();
            double sence_sX = r.getScene().getX();
            double sence_sY = r.getScene().getY();
            if(Constant.gridCellNum==10){
                if(three2.isVertical){    
                    if(sence_X>356 && sence_X<(567-15) && sence_Y>443 && sence_Y < (650-54)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>356 && sence_X<(567-54) && sence_Y>443 && sence_Y < (650-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    three2.isVertical), 
                    (int)((sence_X-356)/21), (int)((sence_Y-443)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }else if(Constant.gridCellNum==12){
                if(three2.isVertical){    
                    if(sence_X>345 && sence_X<(595-15) && sence_Y>474 && sence_Y < (725-54)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>345 && sence_X<(595-54) && sence_Y>474 && sence_Y < (725-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    three2.isVertical), 
                    (int)((sence_X-345)/21), (int)((sence_Y-474)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }
        });
        
        four = new RectangleShipObj(16,(20*4-4),Color.RED);
        four.setCursor(Cursor.HAND);
        four.setTranslateX(70);
        four.setTranslateY(250);
        four.setOnMousePressed(rectangleOnMousePressedEventHandler);
        four.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        four.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            RectangleShipObj r = ((RectangleShipObj)(e.getSource()));
            Bounds rb = r.getBoundsInParent();
            rp_bound = playerMapView.getBoundsInLocal();
            if (e.getButton() == MouseButton.SECONDARY) {
                four.ChangeState();
            }
            double sence_X = rb.getMinX();
            double sence_Y = rb.getMinY();
            double sence_sX = r.getScene().getX();
            double sence_sY = r.getScene().getY();
            if(Constant.gridCellNum==10){
                if(four.isVertical){    
                    if(sence_X>356 && sence_X<(567-15) && sence_Y>443 && sence_Y < (650-75)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>356 && sence_X<(567-75) && sence_Y>443 && sence_Y < (650-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    four.isVertical), 
                    (int)((sence_X-356)/21), (int)((sence_Y-443)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }else if(Constant.gridCellNum==12){
                if(four.isVertical){    
                    if(sence_X>345 && sence_X<(595-15) && sence_Y>474 && sence_Y < (725-75)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>345 && sence_X<(595-75) && sence_Y>474 && sence_Y < (725-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    four.isVertical), 
                    (int)((sence_X-345)/21), (int)((sence_Y-474)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }
        });
        
        five = new RectangleShipObj(16,(20*5-6),Color.RED);
        five.setCursor(Cursor.HAND);
        five.setTranslateX(90);
        five.setTranslateY(250);
        five.setOnMousePressed(rectangleOnMousePressedEventHandler);
        five.setOnMouseDragged(rectangleOnMouseDraggedEventHandler);
        five.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            RectangleShipObj r = ((RectangleShipObj)(e.getSource()));
            Bounds rb = r.getBoundsInParent();
            System.out.println("!!-->"+rb.toString());
            rp_bound = playerMapView.getBoundsInLocal();
            if (e.getButton() == MouseButton.SECONDARY) {
                five.ChangeState();
            }
            double sence_X = rb.getMinX();
            double sence_Y = rb.getMinY();
            double sence_sX = r.getScene().getX();
            double sence_sY = r.getScene().getY();
            if(Constant.gridCellNum==10){
                if(five.isVertical){    
                    if(sence_X>356 && sence_X<(567-15) && sence_Y>443 && sence_Y < (650-90)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>356 && sence_X<(567-90) && sence_Y>443 && sence_Y < (650-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    five.isVertical), 
                    (int)((sence_X-356)/21), (int)((sence_Y-443)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }else if(Constant.gridCellNum==12){
                if(five.isVertical){    
                    if(sence_X>345 && sence_X<(595-15) && sence_Y>474 && sence_Y < (725-90)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }else{
                    if(sence_X>345 && sence_X<(595-90) && sence_Y>474 && sence_Y < (725-15)){
                        r.ValidState();
                    }
                    else{
                        r.InvalidState();
                    }
                }
                if (this.playerMapView.placingBattleShipOn_X_Y(new Ship(controller.userPlayer.orderedShipsSizeList[controller.userPlayer.iniShipsToPlace-1], 
                    five.isVertical), 
                    (int)((sence_X-345)/21), (int)((sence_Y-474)/21))) {
                    if (--controller.userPlayer.iniShipsToPlace == 0) {
                        /*root.setLeft(new Text("\n\n\n\n\n             The battle is starting!! \n\n"
                                + "             Player's move             "));
                        */
                        controller.mainWindowView.displayMessage(3);
                        controller.userPlayer.PlaceShipsAutomatically();
                    }
                }
            }
        });
    }
}
