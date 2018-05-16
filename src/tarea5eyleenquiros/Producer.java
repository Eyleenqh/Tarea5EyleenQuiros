/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5eyleenquiros;

// Producer's run method controls a thread that
import Domain.Shuriken;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

// stores values from 1 to 4 in sharedLocation.
public class Producer extends Thread {

    private Buffer sharedLocation; // reference to shared object
    private Shuriken shuriken;
    private Image conector;
    private int x, y;

    // constructor
    public Producer(Buffer shared) {
        super("Producer");
        sharedLocation = shared;
        shuriken = new Shuriken(5, 5);
        conector = new Image("Assets/image3.png");
        x = 0;
        y = 0;
    }

    // store values from 1 to 4 in sharedLocation
    @Override
    public void run() {
        for (int count = 1; count <= 8; count++) {
            int j = 5;
            int i = 5;
            int random=5+((int) (Math.random() * 21));
            while (j <= 280 && i <= 330) {
                try {
                    Thread.sleep(random);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                shuriken.setX(j);
                shuriken.setY(i);
                setX(j);
                setY(i);
                j++;
                i++;

            }
            try {
                //acumulates count value
                sharedLocation.set(shuriken.clone());

            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
            shuriken.setX(5);
            shuriken.setY(5);
            int z = 280;
            int c = 330;
            random=5+((int) (Math.random() * 21));
            while (z >= 5 && c >= 5) {
                try {
                    Thread.sleep(random);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                setX(z);
                setY(c);
                z--;
                c--;
                // sleep 0 to 3 seconds, then place value in Buffer
            }

        } // end for
        System.err.println(getName() + " done producing." + "\nTerminating " + getName() + ".");

    } // end method run

    public Shuriken getObject() {
        return shuriken;
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

} // end class Producer

