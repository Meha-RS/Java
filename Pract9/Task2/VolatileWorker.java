package Pract9.Task2;

public class VolatileWorker implements Runnable {
    private volatile boolean running = true;

    public void stop() { running = false; }
    public boolean isRunning() { return running; }

    @Override
    public void run() {
        long i = 0;
        while (running) { i++; }
        System.out.println("volatile: остановился, i=" + i);
    }
}