package Pract9.Task2;

public class BrokenWorker implements Runnable {
    private boolean running = true;

    public void stop() {
        running = false;
    }
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        long i = 0;
        while (running) {
            i++;
        } 
        System.out.println("broken: остановился, i=" + i);
    }
}