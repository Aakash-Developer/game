
package model;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author zange
 */
public class RectangleShipObj extends Rectangle {

    FadeTransition fadeTransition;
    private double rotateState = 0;
    public boolean isVertical = true;
    Timeline timeline;
    
    public RectangleShipObj(int x, int y, Color color)
    {
        super(x, y, color);
        this.setRotate(rotateState);
    }


    public void ChangeState(){
        
        if(this.getRotate()==0){
            this.setRotate(90);
            this.isVertical = false;
            System.out.println("Change state: "+this.isVertical);
            InvalidState();
        }
        else{
            this.setRotate(0);
            this.isVertical = true;
            System.out.println("Change state: "+this.isVertical);
            ValidState();
        }
    }
    public void ValidState(){
        this.setFill(Color.GREENYELLOW);
//        timeline.stop();
    }
    
    public void InvalidState(){
        this.setFill(Color.RED);

        
//        timeline =       
//                new Timeline(new KeyFrame(Duration.seconds(0.3), evt -> this.setVisible(false)),
//                new KeyFrame(Duration.seconds(0.1),  evt -> this.setVisible(true)));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
       
    }
}
