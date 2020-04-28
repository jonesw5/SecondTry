import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.EventHandler;




public class start extends Application {

    // create static variables for each car image
    private static ImageView car_1;
    private static ImageView car_2;
    private static ImageView car_3;


    // create a global variable to control the flow of the race
    private static boolean paused = false;
    private static boolean finished = false;
    private static boolean ok2Go = true;


    // create static final variables to constrain the event
    private static final int car_x = 40;
    private static final int car_1_y = 75;
    private static final int car_2_y = 110;
    private static final int car_3_y = 145;
    private static final int finishLine = 375;
    private static final int window_width = 500;
    private static final int window_height = 200;
    private static final int btn_w = 80;
    private static final int btn_h = 20;


    // Main method
    public static void main(String[] args) {

        // pull in source for the car image
        Image sportsCar = new Image("sample/sportive-car.png");

        // create individual image views for each car
        car_1 = new ImageView(sportsCar);
        car_2 = new ImageView(sportsCar);
        car_3 = new ImageView(sportsCar);

        // place the images where the belong
        car_1.relocate(car_x, car_1_y);
        car_2.relocate(car_x, car_2_y);
        car_3.relocate(car_x, car_3_y);

        // correct an issue with placement of car image
        car_1.setX(car_x - car_1.getImage().getWidth()*2);
        car_2.setX(car_x - car_2.getImage().getWidth()*2);
        car_3.setX(car_x - car_3.getImage().getWidth()*2);


        // start the GUI
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        // Add Start Button
        Button btn_start = new Button();
        btn_start.setText("Start");
        btn_start.relocate(window_width/4 - btn_w/2, 20);
        btn_start.setPrefSize(btn_w, btn_h);
        EventHandler<ActionEvent> event_start = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ if (ok2Go && !finished) startYourEngines(); }
        };
        btn_start.setOnAction(event_start);
        //Finished adding start buttton

        // add pause button
        Button btn_pause = new Button();
        btn_pause.setText("Pause");
        btn_pause.relocate(window_width/2 - btn_w/2, 20);
        btn_pause.setPrefSize(btn_w, btn_h);
        EventHandler<ActionEvent> event_pause = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ if (!finished) pauseEngines(); }
        };
        btn_pause.setOnAction(event_pause);
        // finished adding pause button


        // add stop button
        Button btn_reset = new Button();
        btn_reset.setText("Reset");
        btn_reset.relocate(3 * window_width/4 - btn_w/2, 20);
        btn_reset.setPrefSize(btn_w, btn_h);
        EventHandler<ActionEvent> event_stop = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){ haltEngine(); }
        };
        btn_reset.setOnAction(event_stop);
        // finished adding stop button



        // add first race track (Rectangle)
        Rectangle rectangle1 = new Rectangle();
        rectangle1.setFill(Color.LIGHTGRAY);
        rectangle1.setHeight(20);
        rectangle1.setWidth(400);
        rectangle1.relocate(50, 80);
        // finished adding first race track


        // add Second race track (Rectangle)
        Rectangle rectangle2 = new Rectangle();
        rectangle2.setFill(Color.LIGHTGRAY);
        rectangle2.setHeight(20);
        rectangle2.setWidth(400);
        rectangle2.relocate(50, 115);
        // finished adding second race track


        // add third race track (Rectangle)
        Rectangle rectangle3 = new Rectangle();
        rectangle3.setFill(Color.LIGHTGRAY);
        rectangle3.setHeight(20);
        rectangle3.setWidth(400);
        rectangle3.relocate(50, 150);
        // finished adding third race track


        // build the pane that is basically the background
        Pane pane = new Pane();
        pane.setPrefSize(window_width, window_height);
        pane.getChildren().addAll(btn_start, btn_pause, btn_reset, rectangle1,
                        rectangle2, rectangle3, car_1, car_2, car_3);

        // build a scene that everything will be added to
        Scene scene = new Scene(pane, window_width, window_height);

        // Add tile and scene to the primary stage
        primaryStage.setTitle("Corona Raceway");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //when the stop button is hit
    private static void haltEngine(){
        paused = true;
        car_1.setX(car_x - car_1.getImage().getWidth()*2);
        car_2.setX(car_x - car_2.getImage().getWidth()*2);
        car_3.setX(car_x - car_3.getImage().getWidth()*2);
        ok2Go = true;
        finished = false;
    }





    // When the pause button is hit
    private static void pauseEngines(){
        paused = true;
        ok2Go = true;
    }





    // when the start button is hit
    private void startYourEngines(){
        // check to make sure race isn't halted
        if(!paused) {
            car_1.setX(car_x - car_1.getImage().getWidth() * 2);
            car_2.setX(car_x - car_2.getImage().getWidth() * 2);
            car_3.setX(car_x - car_3.getImage().getWidth() * 2);
        }
        paused = false;

        // Create thread for Car 1
        Thread thread_1 = new Thread() {
            public synchronized void run() {
                while (true) {
                    if (paused)
                        break;
                    int rando = (int) (Math.random() * 10);
                    Platform.runLater(() -> car_1.setX(car_1.getX() + rando));
                    if (car_1.getX() >= finishLine) {
                        finshline(1);
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

        // Create separate thread for car_2
        Thread thread_2 = new Thread() {
            public synchronized void run() {
                while (true) {
                    if (paused)
                        break;
                    int rando = (int) (Math.random() * 10);
                    Platform.runLater(() -> car_2.setX(car_2.getX() + rando));
                    if (car_2.getX() >= finishLine) {
                        finshline(2);
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

        // Create separate thread for car_3
        Thread thread_3 = new Thread() {
            public synchronized void run() {
                while (true) {
                    if (paused)
                        break;
                    int rando = (int) (Math.random() * 10);
                    Platform.runLater(() -> car_3.setX(car_3.getX() + rando));
                    if (car_3.getX() >= finishLine) {
                        finshline(3);
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

        // Start all the threads
        thread_1.start();
        thread_2.start();
        thread_3.start();

        ok2Go = false;
    }





    // called when a car makes it to the finish line!
    private Runnable finshline(int i) {
        if (!paused) {
            Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, "Car: " + i + " won!").show());
        }
        paused = true;
        ok2Go = false;
        finished = true;
        return null;
    }

}
