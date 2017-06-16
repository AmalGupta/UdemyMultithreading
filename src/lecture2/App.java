package lecture2;

import java.util.Scanner;

/**
 * Created by amagupta on 6/14/2017.
 */

class Processor extends Thread{

    private volatile boolean running = true;

    @Override
    public void run() {

        while (running){
            System.out.println("hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown(){
        running = false;
    }
}
public class App {

    public static void main(String[] args) {
        Processor processor1 = new Processor();
        processor1.start();

        System.out.println("Press any key to stop....");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        processor1.shutdown();

    }
}
