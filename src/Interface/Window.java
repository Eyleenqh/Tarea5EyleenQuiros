/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.BufferOverflowException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tarea5eyleenquiros.Consumer;
import tarea5eyleenquiros.Producer;
import tarea5eyleenquiros.SynchronizedBuffer;

/**
 *
 * @author Eyleen
 */
public class Window extends Application implements Runnable {

    private Thread thread;
    private Scene scene;
    private Pane pane;
    private Canvas canvas;
    private Image image;

    private Producer producer;
    private Consumer consumer;
    private SynchronizedBuffer sharedLocation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tarea 5");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
    }

    @Override
    public void run() {

        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(wait);
                GraphicsContext gc = this.canvas.getGraphicsContext2D();
                draw(gc);
            } catch (InterruptedException ex) {
            }
        }
    }

    private void initComponents(Stage primaryStage) throws FileNotFoundException {
        try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, 800, 550);
            this.canvas = new Canvas(800, 550);
            this.image = new Image(new FileInputStream("src/Assets/fondo.jpg"));

            this.pane.getChildren().add(this.canvas);

            primaryStage.setScene(this.scene);
            sharedLocation = new SynchronizedBuffer();
            sharedLocation.displayState("Initial State");

            //Inicializamos cada hilo (personaje) y lo iniciamos
            this.producer = new Producer(sharedLocation);
            this.producer.start();
            this.consumer = new Consumer(sharedLocation);
            this.consumer.start();

            this.thread = new Thread(this);
            this.thread.start();
        } // catch (FileNotFoundException ex){}
        catch (BufferOverflowException ex) {
        }
    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 550);
        gc.drawImage(image, 0, 0);
        gc.setStroke(Color.HONEYDEW);
        gc.strokeRect(350, 350, 70, 70);
        gc.drawImage(this.producer.getImage(), this.producer.getX(), this.producer.getY());
        gc.drawImage(this.consumer.getImage(), this.consumer.getX(), this.consumer.getY());
        gc.drawImage(this.producer.getObject().getImage(), this.producer.getObject().getX(), this.producer.getObject().getY());
        if (this.consumer.getObject() != null) {
            gc.drawImage(this.consumer.getObject().getImage(), this.consumer.getObject().getX(), this.consumer.getObject().getY());
        }
        if (sharedLocation.getObject() != null) {
            gc.drawImage(this.sharedLocation.getObject().getImage(), 350, 350);
        }

        //if () {
            int i = 382;
            int j = 0;
            while (j < this.consumer.getArray().size()) {
                gc.drawImage(this.consumer.getArray().get(j),i, 40);
                j++;
                i = i +32;
            }
        //}
    }

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}
