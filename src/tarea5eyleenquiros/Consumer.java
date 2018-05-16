/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5eyleenquiros;

import Domain.Shuriken;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

// Consumer's run method controls a thread that loops four
// times and reads a value from sharedLocation each time.
public class Consumer extends Thread {

    private SynchronizedBuffer sharedLocation; // reference to shared object
    private Shuriken shuriken;
    private Image conector;
    private Image image;
    private int x, y;
    private ArrayList<Image> aList;

    // constructor
    public Consumer(SynchronizedBuffer shared) {
        super("Consumer");
        this.sharedLocation = shared;
        this.conector = new Image("Assets/image2.png");
        this.image=new Image("Assets/image4.png");
        aList=new ArrayList<Image>();
        int x = 350;
        int y = 350;
        setX(y);
        setY(x);
    }

    // read sharedLocation's value four times and sum the values
    @Override
    public void run() {
        for (int count = 1; count <= 8; count++) {

            this.shuriken = this.sharedLocation.get();
            sharedLocation.setObject();
            int j = 350;
            int i = 350;
            int random=5+((int) (Math.random() * 21));
            while (j <= 790 && i >= 10) {
                try {
                    Thread.sleep(random);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.shuriken.setX(j);
                this.shuriken.setY(i);
                setX(j);
                setY(i);
                i--;
                j++;

            }
            aList.add(image);
            int z = 700;
            int c = 20;
            random=5+((int) (Math.random() * 21));
            while (z >= 350 && c <= 350) {
                try {
                    Thread.sleep(random);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                setX(z);
                setY(c);
                z--;
                c++;
                // sleep 0 to 3 seconds, then place value in Buffer
            }
            System.err.println(getName() + " read values totaling: "
                    + ".\nTerminating " + getName() + ".");
        }

        this.shuriken.setImage(null);
        sharedLocation.setObject();
    } // end method run

    public Shuriken getObject() {
        return this.shuriken;
    }

    public Image getImage() {
        return this.conector;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public ArrayList<Image> getArray(){
        return this.aList;
    }

} // end class Consumer

