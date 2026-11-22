package Pract9.Task4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
    private final BlockingQueue<Request> queue;
    private final AtomicInteger processed;

    public Consumer(BlockingQueue<Request> queue, AtomicInteger processed) {
        this.queue = queue;
        this.processed = processed;
    }

    @Override
    public void run() {
        try {
            while (true) {
                queue.take();
                processed.incrementAndGet();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}