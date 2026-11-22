package Pract9.Task1;

public class SafeCounter {
    int counter1, counter2;

    // Монитор - This
    synchronized void inc1(){
        counter1++;
    }
    synchronized int get1(){
        return counter1;
    }

    // Монитор - Объект Lock
    final Object lock = new Object();
    void inc2(){
        synchronized(lock){counter2++;}
    }
    int get2(){
        synchronized(lock){return counter2;}
    }
}