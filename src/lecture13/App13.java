package lecture13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by amagupta on 6/16/2017.
 */
public class App13 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
       /* {
            @Override
            public void run() {


        }
        }*/
       Future<Integer> future =  executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if (duration>2000){
                    throw new IOException("sleeping for too long");
                }

                System.out.println("Starting : ");
                try {
                    Thread.sleep(random.nextInt(duration));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finish");
                return duration;
            }
        });

        executor.shutdown();


      try {
           // executor.awaitTermination();
            System.out.println("Result is :" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
          System.out.println(e);
        }
    }
}
