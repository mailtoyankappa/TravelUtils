package com.transport.tunal;

public class EvenNOdd implements Runnable{

    private int max;
    private int number;

    public EvenNOdd(int max, int number) {
        this.max = max;
        this.number = number;
    }

    @Override
    public void run(){
    while(number<=max){
        if(Thread.currentThread().getName().equalsIgnoreCase("odd")){
            try{
                printOdd();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try{
                printEven();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
        System.out.println("State : "+Thread.currentThread().getState());
    }
    public synchronized void printOdd() throws InterruptedException {
    if(number%2==0){
        wait();
        System.out.println("State : "+Thread.currentThread().getState());
    }
    System.out.println(number+" : "+Thread.currentThread().getName());
    number++;
    notifyAll();
        System.out.println("State : "+Thread.currentThread().getState());
    }
    public synchronized void printEven() throws InterruptedException {
        if(number%2!=0){
            wait();
            System.out.println("State : "+Thread.currentThread().getState());
        }
        System.out.println(number+" : "+Thread.currentThread().getName());
        number++;
        notifyAll();
        System.out.println("State : "+Thread.currentThread().getState());
    }
}


