import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;




public class start extends Application {


    private static ImageView car_1;
    private static ImageView car_2;
    private static ImageView car_3;

    private static Button btn_start;
    private static Button btn_pause;
    private static Button btn_reset;

    private static boolean finished = false;
    private static int car_1_y = 75;
    private static int car_1_x = 40;
    private static int car_2_y = 110;
    private static int car_2_x = 40;
    private static int car_3_y = 145;
    private static int car_3_x = 40;


    public static void main(String[] args) {
        car_1 = new ImageView();
        car_2 = new ImageView();
        car_3 = new ImageView();

        Image sportsCar = new Image("sample/sportive-car.png");
        car_1 = new ImageView(sportsCar);
        car_2 = new ImageView(sportsCar);
        car_3 = new ImageView(sportsCar);

        car_1.relocate(car_1_x, car_1_y);
        car_2.relocate(car_2_x, car_2_y);
        car_3.relocate(car_3_x, car_3_y);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setPrefSize(500,300);

        btn_start = new Button();
        btn_start.setText("Start");
        btn_start.relocate(50, 20);
        btn_start.setPrefSize(80, 20);
        EventHandler<ActionEvent> event_start = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ startYourEngines(); }
        };
        btn_start.setOnAction(event_start);

        btn_pause = new Button();
        btn_pause.setText("Pause");
        btn_pause.relocate(200, 20);
        btn_pause.setPrefSize(80, 20);
        EventHandler<ActionEvent> event_pause = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ pauseEngines(); }
        };
        btn_pause.setOnAction(event_pause);

        btn_reset = new Button();
        btn_reset.setText("Stop");
        btn_reset.relocate(400, 20);
        btn_reset.setPrefSize(80, 20);
        EventHandler<ActionEvent> event_stop = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ haltEngine(); }
        };
        btn_reset.setOnAction(event_stop);

//       <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#b8c8d7" height="10.0" layoutX="50.0" layoutY="80.0"
//       stroke="BLACK" strokeType="INSIDE" width="400.0" />
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setFill(Color.LIGHTGRAY);
        rectangle1.setHeight(20);
        rectangle1.setWidth(400);
        rectangle1.relocate(50, 80);

//      <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#b8c8d7" height="10.0" layoutX="50.0" layoutY="115.0"
//      stroke="BLACK" strokeType="INSIDE" width="400.0" />

        Rectangle rectangle2 = new Rectangle();
        rectangle2.setFill(Color.LIGHTGRAY);
        rectangle2.setHeight(20);
        rectangle2.setWidth(400);
        rectangle2.relocate(50, 115);
//      <Rectangle arcHeight="5.0" arcWidth="5.0" fill="#b8c8d7" height="10.0" layoutX="50.0" layoutY="150.0"
//      stroke="BLACK" strokeType="INSIDE" width="400.0" />
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setFill(Color.LIGHTGRAY);
        rectangle3.setHeight(20);
        rectangle3.setWidth(400);
        rectangle3.relocate(50, 150);

        pane.getChildren().addAll(btn_start, btn_pause, btn_reset, rectangle1,
                        rectangle2, rectangle3, car_1, car_2, car_3);

        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static Thread thread_1;
    private static Thread thread_2;
    private static Thread thread_3;

    private static void haltEngine(){
        thread_1.interrupt();
        thread_2.interrupt();
        thread_3.interrupt();
        car_1.setX(car_1_x);
        car_2.setX(car_2_x);
        car_2.setX(car_3_x);
    }



    private static void pauseEngines(){
        finished = true;
    }


    private static void startYourEngines(){
        if(!finished) {
            car_1.relocate(car_1_x, car_1_y);
            car_2.relocate(car_2_x, car_2_y);
            car_3.relocate(car_3_x, car_3_y);
        }
        finished = false;
        thread_1 = new Thread(){
            public synchronized void run(){
               while(true){
                   if(finished)
                       break;
                   int rando = (int) (Math.random() * 10);
                   Platform.runLater(() ->car_1.setX(car_1.getX()+ rando));
                   if(car_1.getX() >= 400){
                       Platform.runLater(finshline(1));
                       break;
                   }
                   try {
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        };
        thread_2 = new Thread(){
            public synchronized void run(){
                while(true){
                    if(finished)
                        break;
                    int rando = (int) (Math.random() * 10);
                    Platform.runLater(() ->car_2.setX(car_2.getX()+ rando));
                    if(car_2.getX() >= 400){
                        Platform.runLater(finshline(2));
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread_3 = new Thread(){
            public synchronized void run(){
                while(true){
                    if(finished)
                        break;
                    int rando = (int) (Math.random() * 10);
                    Platform.runLater(() ->car_3.setX(car_3.getX()+ rando));
                    if(car_3.getX() >= 400){
                        Platform.runLater(finshline(3));
                        break;
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread_1.start();
        thread_2.start();
        thread_3.start();

    }


    private static Runnable finshline(int i) {
        finished = true;
        System.out.println("Car: " + i + "won");
        return null;
    }

}
