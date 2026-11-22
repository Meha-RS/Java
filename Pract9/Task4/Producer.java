package Pract9.Task4;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue<Request> queue;
    private final int count;

    public Producer(BlockingQueue<Request> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                queue.put(new Request(i));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}