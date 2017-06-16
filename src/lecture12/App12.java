package lecture12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by amagupta on 6/16/2017.
 */
public class App12 {
    public static void main(String[] args) throws Exception{
/*
        Semaphore sem = new Semaphore(1);

        sem.release();
        sem.acquire();
        System.out.println("Available permits " + sem.availablePermits());
*/

        //Connection.getInstance().connect();

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0;i<200;i++){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }


        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
