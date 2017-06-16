package lecture11;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by amagupta on 6/15/2017.
 */

public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private  int count =0;

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();


    private void increment(){
        for(int i =0;i<10000;i++){
            count++;
        }
    }

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{

        while(true){

            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try{
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }
            finally {

                if(gotFirstLock&&gotSecondLock){
                    return;
                }
                if(gotFirstLock){
                    firstLock.unlock();
                }
                if(gotSecondLock){
                    secondLock.unlock();
                }
            }

            Thread.sleep(100);
        }

    }

    public void firstThread() throws InterruptedException{
        Random random = new Random();
        for(int i=0;i<10000;i++){

            acquireLocks(lock1,lock2);;
            try {

                Account.transfer(acc1,acc2,random.nextInt(100));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }


        }

    }

    public void secondThread() throws InterruptedException{

        Random random = new Random();
        try {
            acquireLocks(lock2,lock1);
            for (int i = 0; i < 10000; i++) {
                Account.transfer(acc2, acc1, random.nextInt(100));
            }
        }
        finally {
            lock1.unlock();
            lock2.unlock();
        }
    }

    public void finished(){
        System.out.println("Account 1 Balance : "+acc1.getBalance());
        System.out.println("Account 2 Balance : "+acc2.getBalance());
        System.out.println("Total Balance : "+(acc1.getBalance()+acc2.getBalance()));
    }
}

