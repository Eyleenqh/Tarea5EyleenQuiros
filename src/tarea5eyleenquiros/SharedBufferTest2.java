/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea5eyleenquiros;

// SharedBufferTest2creates producer and consumer threads.
public class SharedBufferTest2 {

    public static void main(String[] args) {
        // create shared object used by threads; we use a SynchronizedBuffer
        // reference rather than a Buffer reference so we can invoke
        // SynchronizedBuffer method displayState from main
        SynchronizedBuffer sharedLocation = new SynchronizedBuffer();

        //**************************************** title ******************************************
        // Display column heads for output
        //display with appereance of columns
        StringBuffer columnHeads = new StringBuffer("Operation");
        columnHeads.setLength(40);
        columnHeads.append("Buffer\tOccupied Count");
        System.err.println(columnHeads);
        System.err.println();
        System.err.println();
        sharedLocation.displayState("Initial State");
        //******************************************************************************************

        // create producer and consumer objects
        Producer producer = new Producer(sharedLocation);
        Consumer consumer = new Consumer(sharedLocation);

        producer.start();  // start producer thread
        consumer.start();  // start consumer thread

    } // end main
} // end class SharedBufferTest2

