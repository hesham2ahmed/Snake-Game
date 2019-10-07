package snake.com;


import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import java.util.LinkedList;
import java.util.Queue;

//Queue with linkedlist based
public class Snake extends javafx.scene.shape.Shape {

    public Rectangles head;
    public double x , y ;
    private static Snake instance =  null;
    public  Rectangles ptr , ptr2 ;
    Queue<Double> x_axis = new LinkedList<>(); ;
    Queue<Double> y_axis = new LinkedList<>();;






    private Snake(double x , double y , int width, Paint headcolor ){
        head = new Rectangles(x,y,width,20,Color.YELLOW);
        head.draw(Main.layout);
    }


    // Lazy initialization with singleton
    public static Snake creatInstance(double x,double y,int width,Paint color){
        if(instance == null)
            return instance = new Snake(x,y,width,color);
        else
            {
                instance.head.setX(x);
                instance.head.setY(y);
                return instance;
        }
    }


    /**
     * function to add nodes
     * @return
     */
    public void add() {
         Rectangles node ;
         // creating pointer on the head of snake
         ptr = ptr2= head;

         // Loop to get the last node in at the snake
         while (ptr.next != null) {
             ptr2 = ptr;
             ptr = ptr.next;
         }

         // Adding last node
        node = generateLastNode(ptr,ptr2);
         node.order = ptr.order + 1;

         ptr = null;
         node.draw(Main.layout);
    }


    /**
     *
     * @param lastNode
     * @param preLastNode
     * @return a node that will be adding to snake
     */
    public Rectangles generateLastNode(Rectangles lastNode, Rectangles preLastNode){
        if(preLastNode.getX() == lastNode.getX())
             return lastNode.next = new Rectangles(lastNode.getX(),lastNode.getY()+lastNode.getHeight());
        else if(preLastNode.getY() == lastNode.getY())
            return lastNode.next = new Rectangles(lastNode.getX()+lastNode.getWidth(),lastNode.getY());
        else
            return null;
    }


    public void moveLeft(){
        ptr = head;

        y_axis.add(ptr.getY());
        x_axis.add(ptr.getX());

        while (ptr != null){
            if(ptr != head){
                y_axis.add(ptr.getY());
                x_axis.add(ptr.getX());

                ptr.setY(y_axis.poll());

                ptr.setX(x_axis.poll());

            }
            else {ptr.setX(x_axis.peek() - ptr.getWidth());}
            ptr = ptr.next;
        }
        removeAll();
    }

    public void moveRight(){
        ptr = head;

        y_axis.add(ptr.getY());
        x_axis.add(ptr.getX());

        while (ptr != null){
            if(ptr != head){
                y_axis.add(ptr.getY());
                x_axis.add(ptr.getX());

                ptr.setY(y_axis.poll());

                ptr.setX(x_axis.poll());
            }
            else {ptr.setX(x_axis.peek() + ptr.getWidth());}
            ptr = ptr.next;
        }
        removeAll();
    }

    public void moveUp() {
        ptr = head;

        y_axis.add(ptr.getY());
        x_axis.add(ptr.getX());

        while (ptr != null) {
            if (ptr != head) {
                y_axis.add(ptr.getY());
                x_axis.add(ptr.getX());

                ptr.setY(y_axis.poll());

                ptr.setX(x_axis.poll());
            }
            else {ptr.setY(y_axis.peek() - ptr.getHeight());}
            ptr = ptr.next;
        }
        removeAll();
    }

    public void moveDown(){
        ptr = head;

        y_axis.add(ptr.getY());
        x_axis.add(ptr.getX());

        while (ptr != null){
            if(ptr !=head){
                y_axis.add(ptr.getY());
                x_axis.add(ptr.getX());
                ptr.setY(y_axis.poll());

                ptr.setX(x_axis.poll());
            }
            else {ptr.setY(y_axis.peek() + ptr.getHeight());}
            ptr = ptr.next;
        }
        removeAll();
    }



    public void dead(){

    }




    // To get length of snack
    public double lenght(){
        int i = 0 ;
        ptr = head;
        while (ptr!= null) {
            ptr = ptr.next;
            i++;
        }
        double lenght = (i* head.getWidth());
        ptr = null ;
        return lenght;
    }

    // function to remove all elements in QUEUE
    public void removeAll(){
        this.x_axis.removeAll(x_axis);
        this.y_axis.removeAll(y_axis);
    }

    // function to remove or destroy body of the snack
    public void destroy(){
        if(head.next!=null) {

            ptr = this.head;
            ptr2 = this.head.next.next;

            while (ptr2 != null) {
                removeNode(ptr.next);
                head.next = ptr2;
                ptr2 = ptr2.next;
            }
            removeNode(ptr.next);
            head.next = null;
            ptr = null;
        }
    }


    public void removeNode(Rectangles rectangles)
    {
        Main.layout.getChildren().remove(rectangles);
        rectangles = null;
    }






    @Override
    public com.sun.javafx.geom.Shape impl_configShape() {
        return null;
    }








}
