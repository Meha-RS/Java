package Pract9.Task5;

import Pract9.Task1.BrokenCounter;
import org.junit.jupiter.api.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class CounterRaceTest {

    @Test
    @Timeout(5)
    void testRace() throws Exception {
        int threads = 8;
        int incs = 100_000;
        BrokenCounter counter = new BrokenCounter();
        
        try (ExecutorService pool = Executors.newFixedThreadPool(threads)) {
            CountDownLatch start = new CountDownLatch(1);
            CountDownLatch finish = new CountDownLatch(threads);

            for (int i = 0; i < threads; i++) {
                pool.submit(() -> {
                    try {
                        start.await();
                        for (int j = 0; j < incs; j++) counter.inc();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        finish.countDown();
                    }
                });
            }

            start.countDown();
            finish.await(3, TimeUnit.SECONDS);

            int expected = threads * incs;
            int actual = counter.get();
            
            System.out.println("Ждали: " + expected + ", получили: " + actual);
            assertTrue(actual < expected, "Гонка не воспроизвелась!");
        }
    }
}