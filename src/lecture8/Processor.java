package lecture8;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by amagupta on 6/15/2017.
 */
public class Processor {

    public void produce() throws InterruptedException{

        synchronized (this){
            System.out.println("Producer Thread Running ");
            wait();
            System.out.println("Resumed..");
        }

    }

    public void consume() throws InterruptedException{

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this){
            System.out.println("Waiting for return key ...");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();

            Thread.sleep(5000);
        }
    }
}
