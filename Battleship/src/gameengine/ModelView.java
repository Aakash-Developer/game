package gameengine;

import battleship.IPlayerMap;
import gameui.PlayerMap;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import utils.Tuple;
import javafx.scene.paint.Color;

public class ModelView {
    
    private Tuple AttackMatrix;
    private IPlayerMap _playerMap;

    public ModelView(){
        
        _playerMap = new PlayerMap();
        initializeEventHandler(_playerMap);
        
    }
    
    private void initializeEventHandler(IPlayerMap playerMap){
        
        GridPane attackMap = playerMap.GetAttackMap();
        
        attackMap.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            Node target = (Node)e.getTarget() ;
            Integer colIndex = GridPane.getColumnIndex(target);
            Integer rowIndex = GridPane.getRowIndex(target);
            
            if(target instanceof Rectangle){
                OnClickAttackMap(new Tuple(colIndex,rowIndex));
                System.out.println("col:" + colIndex + ", row:" + rowIndex + " --- " + target);
            }

        });
    }
    
    public VBox GetPlayerMap(){
      return _playerMap.GetPlayerMap();  
    }
    
    private void OnClickAttackMap(Tuple attackPoint){
        
    }
    
    public void initializeShips(byte[][] matrix ){
     
        GridPane gp = _playerMap.GetShipMap();
        
        for(int x=0; x < matrix.length ; x++){
            for(int y=0; y < matrix.length ; y++){
                if(matrix[x][y]==1){
                    Rectangle r = (Rectangle) getNodeByRowColumnIndex(x,y,gp);
                    r.setFill(Color.BLACK);
                }
            }
        }
    }
    
    
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

}
