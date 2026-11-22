package Pract9.Task4;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo {
    public static void main(String[] args) throws Exception {
        int producers = 3, consumers = 4, perProducer = 1000;

        BlockingQueue<Request> queue = new ArrayBlockingQueue<>(10);
        AtomicInteger processed = new AtomicInteger();

        ExecutorService pool = Executors.newFixedThreadPool(consumers, r -> {
            Thread t = new Thread(r);
            t.setName("consumer");
            return t;
        });

        for (int i = 0; i < consumers; i++) {
            pool.submit(new Consumer(queue, processed));
        }

        Thread[] prod = new Thread[producers];
        for (int i = 0; i < producers; i++) {
            prod[i] = new Thread(new Producer(queue, perProducer), "producer-" + i);
            prod[i].start();
        }

        for (Thread t : prod) t.join();

        Thread.sleep(500);

        pool.shutdownNow();
        
        pool.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("processed = " + processed.get());
    }
}