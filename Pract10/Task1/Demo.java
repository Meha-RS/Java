package Pract10.Task1;

import java.util.concurrent.*;
public class Demo {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        System.out.println("1. Runnable vs Callable");
        Future<?> rFuture = pool.submit(() -> System.out.println("Runnable работает, возвращает null"));
        System.out.println("Runnable result: " + rFuture.get());

        Future<Integer> cFuture = pool.submit(() -> {
            Thread.sleep(500);
            return 42;
        });
        System.out.println("Callable result: " + cFuture.get());


        System.out.println("\n2. Timeout и Ошибка");
        Future<String> slowTask = pool.submit(() -> {
            Thread.sleep(2000);
            return "Готово";
        });

        try {
            slowTask.get(500, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("Ошибка: Timeout (не успели за 500мс). Отменяем задачу...");
            slowTask.cancel(true); 
        } catch (ExecutionException e) {
            System.out.println("Ошибка вычисления: " + e.getCause());
        }

        System.out.println("\n3. Отмена и Interruption");
        Future<?> interruptibleTask = pool.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.out.println("Задача поймала прерывание и корректно завершилась");
            } finally {
                System.out.println("Задача освободила ресурсы");
            }
        });

        Thread.sleep(200);
        System.out.println("Отправляем запрос cancel(true)...");
        interruptibleTask.cancel(true);

        pool.shutdown();
        if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
            pool.shutdownNow();
        }
    }
}