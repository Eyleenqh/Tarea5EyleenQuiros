/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5eyleenquiros;

// SynchronizedBuffer synchronizes access to a single shared integer.
import Domain.Shuriken;

public class SynchronizedBuffer implements Buffer {

    private Shuriken buffer = null; // shared by producer and consumer threads
    private int occupiedBufferCount = 0; // count of occupied buffers

    // place value into buffer
    public synchronized void set(Shuriken object) {
        // for output purposes, get name of thread that called this method
        String name = Thread.currentThread().getName();

        // while there are no empty locations, place thread in waiting state
        while (occupiedBufferCount == 1) {

            // output thread information and buffer information, then wait
            try {
                System.err.println(name + " tries to write.");
                displayState("Buffer full. " + name + " waits.");

                //this thread waits
                wait();

            } // if waiting thread interrupted, print stack trace
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        } // end while

        //if arrive to this place, it means that buffer is empty
        buffer = object; // set new buffer value

        // indicate producer cannot store another value
        // until consumer retrieves current buffer value
        ++occupiedBufferCount;

        displayState(name + " writes " + buffer);

        notify(); // tell waiting thread to enter ready state

    } // end method set; releases lock on SynchronizedBuffer

    // return value from buffer
    public synchronized Shuriken get() {
        // for output purposes, get name of thread that called this method
        String name = Thread.currentThread().getName();

        // while no data to read, place thread in waiting state
        while (occupiedBufferCount == 0) {

            // output thread information and buffer information, then wait
            try {
                System.err.println(name + " tries to read.");
                displayState("Buffer empty. " + name
                        + " waits.");
                wait();
            } // if waiting thread interrupted, print stack trace
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }

        } // end while

        // indicate that producer can store another value
        // because consumer just retrieved buffer value
        --occupiedBufferCount;

        displayState(name + " reads " + buffer);

        notify(); // tell waiting thread to become ready to execute

        return buffer;

    } // end method get; releases lock on SynchronizedBuffer

    // display current operation and buffer state
    public void displayState(String operation) {
        StringBuffer outputLine = new StringBuffer(operation);
        outputLine.setLength(40);
        outputLine.append(buffer + "\t\t"
                + occupiedBufferCount);
        System.err.println(outputLine);
        System.err.println();
    }
    public void setObject(){
        buffer=null;
    }
    public Shuriken getObject() {
        return this.buffer;
    }

} // end class SynchronizedBuffer

