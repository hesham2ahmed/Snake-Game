package snake.com;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;


public class Main extends Application {

    public static int pstageheight = 805;
    public static int pstagewidth = 900;
    public static double MAX_WIDTH = 860;
    public static double MAX_HEIGHT = 740;
    public int score = 0 ;

    public  static Pane layout = new Pane();
    private Scene scene = new Scene(layout);
    private Text text = new Text();
    private Food food;

    private Boolean MOVE_RIGHT = true;
    private Boolean MOVE_LEFT = true;
    private Boolean MOVE_DOWN = true;
    private Boolean MOVE_UP = true;

    public static void main(String[] args) {
	// write your code here
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setHeight(pstageheight);
        primaryStage.setWidth(pstagewidth);

        //Height
        primaryStage.setMaxHeight(pstageheight);
        primaryStage.setMinHeight(pstageheight);

        //Width
        primaryStage.setMaxWidth(pstagewidth);
        primaryStage.setMinWidth(pstagewidth);

        text.setText("Score : ");
        text.setX(10);
        text.setY(20);

        text.setFill(Color.WHITE);

        layout.getChildren().addAll(text);
        layout.setStyle("-fx-background-color: black");


        primaryStage.setTitle("Snake game");

        primaryStage.setScene(scene);

        primaryStage.show();

        play();

        //System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    public void play(){

        food = Food.createFood(100,100,15,15);
        food.draw(layout);
        Snake snake = Snake.creatInstance(400,400,20,Color.YELLOW);
        snake.add();
        snake.add();

        Rectangle rectangle = new Rectangle(100,100,20,20);



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        if (MOVE_LEFT) {
                            snake.moveLeft(); // Big(O) = n
                            checkValid(snake);
                            feed(snake);
                            MOVE_DOWN = true;
                            MOVE_RIGHT = false;
                            MOVE_UP = true;
                        }
                        break;

                    case RIGHT:
                        if (MOVE_RIGHT) {
                            snake.moveRight(); // Big(O) = n
                            checkValid(snake);
                            feed(snake);
                            MOVE_LEFT = false;
                            MOVE_DOWN = true;
                            MOVE_UP = true;
                        }
                        break;

                    case UP:
                        if (MOVE_UP) {
                            snake.moveUp(); // Big(O) = n
                            checkValid(snake); // Big(O) = n
                            feed(snake); // Big (O) = n
                            MOVE_DOWN = false;
                            MOVE_RIGHT = true;
                            MOVE_LEFT = true;
                        }
                        break;

                    case DOWN:
                        if (MOVE_DOWN) {
                            snake.moveDown(); // Big(O) = n
                            checkValid(snake);
                            feed(snake);
                            MOVE_UP = false;
                            MOVE_RIGHT = true;
                            MOVE_LEFT = true;
                        }
                        break;
                }
            }
        });

    }

    // Check if snake is exist in a valid area
    public void checkValid(Snake snake) {
        Rectangles ptr2 = snake.head;

        while (ptr2.next != null)
        {
            ptr2 = ptr2.next;
            if (snake.head.getX() == ptr2.getX() && snake.head.getY() == ptr2.getY()
                    || snake.head.getX() > Main.MAX_WIDTH
                    || snake.head.getX() < 0
                    || snake.head.getY() > Main.MAX_HEIGHT
                    || snake.head.getY() < 0) {
                gameOver(snake);
                break;
            }
        }
    }

    // To feed snake
    public void feed(Snake snake){
        if(snake.head.getX() == food.getX() && snake.head.getY() == food.getY()){
            food.draw(layout);
            snake.add();
            increaseScore();
        }
    }

    public void increaseScore(){
        score += 5;
        changeScore(score);
    }


    public void gameOver(Snake snake){
        snake.destroy();
        score = 0;
        changeScore(score);
        snake = Snake.creatInstance(400,400,20,Color.YELLOW);
        snake.add();
        snake.add();
    }


    public void changeScore(int score){
        text.setText("Score : " + score);
    }

}
