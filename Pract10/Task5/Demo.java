package Pract10.Task5;

import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) throws Exception {
        int tasks = 10_000;
        int sleepMs = 100;

        //1.
        System.out.println("Platform Threads");
        long start = System.currentTimeMillis();
        
        try (ExecutorService platformPool = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < tasks; i++) {
                platformPool.submit(() -> {
                    try { Thread.sleep(sleepMs); } catch (InterruptedException e) { }
                });
            }
        }
        
        long timePlatform = System.currentTimeMillis() - start;
        System.out.println("Время: " + timePlatform + " мс");

        //2.
        System.out.println("\nVirtual Threads");
        start = System.currentTimeMillis();
        
        try (ExecutorService virtualPool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < tasks; i++) {
                virtualPool.submit(() -> {
                    try { Thread.sleep(sleepMs); } catch (InterruptedException e) { }
                });
            }
        }
        
        long timeVirtual = System.currentTimeMillis() - start;
        System.out.println("Время: " + timeVirtual + " мс");
    }
}