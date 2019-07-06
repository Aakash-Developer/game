package gameui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;


public class Grid {
    
    public Grid(int size){
        
    //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      //Setting size for the pane  
      gridPane.setMinSize (size, size);
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
       //Setting the vertical and horizontal gaps between the columns 
      gridPane.setVgap(5); 
      gridPane.setHgap(5); 

      
    }
}
