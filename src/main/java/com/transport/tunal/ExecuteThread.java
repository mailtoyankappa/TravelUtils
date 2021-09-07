package com.transport.tunal;

public class ExecuteThread {
    public static void main(String[] args){
        EvenNOdd thread = new EvenNOdd(10,1);
        Thread t1 = new Thread(thread, "odd");
        Thread t2 = new Thread(thread, "even");
        System.out.println("State : "+t1.getState());
        //Thread t3 = new Thread((EvenNOdd(10,1)), "odd");
        //Thread t4 = new Thread((new EvenNOdd(10,1)), "even");
        t1.start();
        System.out.println("State : "+t1.getState());
        t2.start();
        t1.yield();
        //t3.start();
        //t4.start();
    }
}
