package Pract10.Task2;

import java.util.List;
import java.util.concurrent.*;

public class Demo {
    private static final ExecutorService pool = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        System.out.println("Главный поток: " + Thread.currentThread().getName());

        // 1. supplyAsync
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Загрузка пользователя в потоке: " + Thread.currentThread().getName());
            return "User_123";
        }, pool);

        // 2. thenApply
        CompletableFuture<String> cardFuture = userFuture.thenApply(user -> {
            System.out.println("Форматирование карточки в потоке: " + Thread.currentThread().getName());
            return "Карточка пользователя: [" + user + "]";
        });

        // 3. thenAccept
        cardFuture.thenAccept(card -> {
            System.out.println("Вывод в потоке: " + Thread.currentThread().getName());
            System.out.println("Результат: " + card);
        });


        CompletableFuture<String> userF = CompletableFuture.supplyAsync(() -> "User_123", pool);
        CompletableFuture<List<String>> enrollmentsFuture = userF.thenCompose(user -> 
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Загрузка записей для " + user + " в потоке: " + Thread.currentThread().getName());
                return List.of("Java Course", "SQL Course");
            }, pool)
        );

        System.out.println("Записи на курсы: " + enrollmentsFuture.join());

        pool.shutdown();
    }
}