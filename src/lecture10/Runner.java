package lecture10;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by amagupta on 6/15/2017.
 */
public class Runner {

    private  int count =0;

    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment(){
        for(int i =0;i<10000;i++){
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        lock.lock();

        System.out.println("Waiting ");

        cond.await();

        System.out.println("Woken up");
        try {
            increment();
        }  finally {
            lock.unlock();
        }

    }

    public void secondThread() throws InterruptedException{

        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press return key : ");
        new Scanner(System.in).nextLine();

        System.out.println("Got Return key : ");

        cond.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("Count is "+ count);
    }
}
