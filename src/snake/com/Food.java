package snake.com;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Food extends Rectangle implements Shapes  {

    public Paint color = Color.YELLOW;
    public Random rand ;

    private static Food foodinstance = null ;

    private Food(double x, double y, double width, double height){
        super(x,y,width,height);
    }

    //lazy initialization for singleton
    public static Food createFood(double x, double y, double width, double height){
        if(foodinstance == null){
            foodinstance = new Food(x, y, width, height);
            return foodinstance;
        }
        return foodinstance;
    }


    @Override
    public void draw(Pane layout) {
        double x = generateX(Main.pstagewidth);
        double y = generateY(Main.pstageheight);

        this.setY(y);

        this.setX(x);

        this.setFill(color);

        try {
            layout.getChildren().addAll(this);
        }catch (Exception e)
        {}
    }



    //Generate position
    // To generate x for food

    private double generateX(int width){
        width -= 30;
        rand = new Random();
        return   0 + rand.nextInt(((int)Main.MAX_WIDTH - 0)/20) *20;
    }

    // To generate y for food
    private double generateY(int height){
        height -=30;
        rand = new Random();
        return  0 + rand.nextInt(((int)Main.MAX_HEIGHT - 0 )/20) *20;
    }

}