package snake.com;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


// Node
public  class Rectangles extends Rectangle implements Shapes
{
    protected  int order = 0;
    private Paint nodecolor = Color.RED;
    protected Rectangles next ;
    private final double width = 20;
    private final double height = 20;

    public Rectangles(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    //Constructor for head node
    public Rectangles (double x, double y, double width, double height, Paint nodecolor){
        super(x, y, width, height);
        this.nodecolor = nodecolor;
    }


    @Override
    public void draw(Pane layout) {
        this.setFill(nodecolor);
        layout.getChildren().addAll(this);
    }





}
