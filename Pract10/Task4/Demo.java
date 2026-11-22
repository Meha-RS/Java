package Pract10.Task4;

import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) throws Exception {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Доступно ядер CPU: " + cores);

        //1. CPU-bound
        System.out.println("\nCPU-bound");
        ExecutorService cpuPool = Executors.newFixedThreadPool(cores);
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < cores; i++) {
            cpuPool.submit(() -> heavyCpuTask());
        }
        
        cpuPool.shutdown();
        cpuPool.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Время CPU-bound: " + (System.currentTimeMillis() - start) + " мс");

        //2. Blocking I/O
        System.out.println("\nBlocking I/O (Virtual Threads)");
        ExecutorService virtualPool = Executors.newVirtualThreadPerTaskExecutor();
        start = System.currentTimeMillis();
        
        for (int i = 0; i < 1000; i++) {
            virtualPool.submit(() -> blockingIoTask());
        }
        
        virtualPool.shutdown();
        virtualPool.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Время Blocking I/O: " + (System.currentTimeMillis() - start) + " мс");
    }

    static void heavyCpuTask() {
        long sum = 0;
        for (int i = 0; i < 1_000_000_000; i++) {
            sum += i;
        }
    }

    static void blockingIoTask() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}