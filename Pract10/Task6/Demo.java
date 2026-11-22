package Pract10.Task6;

import java.util.concurrent.*;

public class Demo {
    public static void main(String[] args) throws Exception {
        long totalBudgetMs = 2000;

        long deadline = System.nanoTime() + TimeUnit.MILLISECONDS.toNanos(totalBudgetMs);

        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<String> future = pool.submit(() -> {
            try {
                Thread.sleep(3000);
                return "Успех";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Отменено";
            }
        });

        long remainingNanos = deadline - System.nanoTime();
        long remainingMs = TimeUnit.NANOSECONDS.toMillis(remainingNanos);

        if (remainingMs <= 0) {
            System.out.println("Дедлайн уже истек, отменяем.");
            future.cancel(true);
        } else {
            try {
                System.out.println("Ждем " + remainingMs + " мс...");
                String result = future.get(remainingMs, TimeUnit.MILLISECONDS);
                System.out.println("Результат: " + result);
            } catch (TimeoutException e) {
                System.out.println("Время вышло! Отменяем задачу.");
                future.cancel(true);
            }
        }

        pool.shutdown();
        if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            pool.shutdownNow();
        }
    }
}