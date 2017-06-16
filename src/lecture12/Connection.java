package lecture12;

import java.util.concurrent.Semaphore;

/**
 * Created by amagupta on 6/16/2017.
 */
public class Connection {

    private int connections = 0;

    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    Semaphore sem = new Semaphore(10, true);

    private Connection() {
    }

    public  void connect(){

        try {
            sem.acquire();
            doConnect();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        finally {
            sem.release();
        }
    }

    public void doConnect(){

        synchronized (this){
            connections++;

            System.out.println("Current Connections: "+connections);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            connections--;
        }


    }


}
