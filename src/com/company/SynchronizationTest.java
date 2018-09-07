package com.company;
import java.util.Random;

public class SynchronizationTest extends Thread{

    public static void main(String[] args) {
	  System.out.println("Test Synchronization");

	  rdm = new Random();
	  SynchronizationTest mn = new SynchronizationTest();
	  Method t1 = new Method(1, rdm);
      Method t2 = new Method(2, rdm);
      Method t3 = new Method(3, rdm);
      Method t4 = new Method(4, rdm);
	  mn.startThreads(new Method[]{t1, t2, t3, t4});
    }

    private static Random rdm;

    public SynchronizationTest(){
    }

    public void startThreads(Method[] threadsitos){
        for(Thread t: threadsitos)
          t.start();
    }
}

class Method extends Thread{

    private int number;
    private Random rdm;

    public Method (int number, Random rdm){
        this.number = number;
        this.rdm = rdm;
    }

    @Override
    public void run(){
        doSomething();
    }

    public synchronized void doSomething(){
        try {
            while (true) {
                System.out.println("Thread #" + number + ":   " + ((rdm.nextDouble() * 10 )+(rdm.nextDouble() * 10)));
                Thread.sleep(100);
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}