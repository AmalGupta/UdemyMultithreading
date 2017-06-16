package com.udemy.multithreading.main;

/**
 * Created by amagupta on 6/14/2017.
 */
public class DemoRunnable {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner1());

        t1.start();
        t2.start();
    }
}


class Runner1 implements  Runnable{

    @Override
    public void run() {

        for(int i=0;i<10;i++){
            System.out.println("Hello "+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}