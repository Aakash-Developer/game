package gameui;

import battleship.IPlayerMap;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class PlayerMap implements IPlayerMap {
    
    private final int RECTANGLE_SIZE = 15;
    private final int INSET_PADDING = 5;
    
    private GridPane _g1;
    private GridPane _g2;
    private VBox _vbox;
    
    public PlayerMap(){

        CretePlayerMap();
    }
    
    private VBox CretePlayerMap(){
        
        _vbox = new VBox();
        
        _vbox.setSpacing(10);
        
        _g1 = CreateGrid(12,Color.HOTPINK);
        _g1.setGridLinesVisible(true);
        
        _g2 = CreateGrid(12,Color.KHAKI);
        _g2.setGridLinesVisible(true);
        
        int insets = 10;
        _vbox.setMargin(_g1, new Insets(insets,insets,insets,insets));  
        _vbox.setMargin(_g2, new Insets(insets,insets,insets,insets)); 
      
        ObservableList list = _vbox.getChildren();
        list.addAll(_g1,_g2);

        return _vbox;
    }
   
    public GridPane CreateGrid(int size, Color color){

      GridPane gridPane = new GridPane();     
      gridPane.setMinSize (size, size);
      gridPane.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING)); 
      gridPane.setVgap(5); 
      gridPane.setHgap(5); 

      
      for(int y=0; y<size;y++){
          for(int x=0; x<size;x++){
                Rectangle r1  = new Rectangle(RECTANGLE_SIZE,RECTANGLE_SIZE);
                r1.setFill(color);
                gridPane.add(r1, x, y);
          }    
      }
      
      return gridPane;
    }
    
    @Override
    public VBox GetPlayerMap(){
        return _vbox;
    }
    
    
    @Override
    public GridPane GetShipMap() {
        return _g2;
    }

    @Override
    public GridPane GetAttackMap() {
        return _g1;
    }
    
    
}
