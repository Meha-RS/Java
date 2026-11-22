package Pract10.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Demo {
    // Имитация загрузки данных с сервера
    private static String loadCourse(String id) {
        if (id.equals("C2"))
            throw new RuntimeException("Ошибка сети");
        return "Курс-" + id;
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<String> ids = List.of("C1", "C2", "C3");

        List<CompletableFuture<String>> futures = new ArrayList<>();
        for (String id : ids) {
            CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> loadCourse(id), pool);
            futures.add(f);
        }

        //1: Fail-Fast (упасть при первой ошибке)
        CompletableFuture<Void> all = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        all.handle((v, ex) -> {
            if (ex != null) {
                System.out.println("Fail-Fast: Ошибка! " + ex.getCause().getMessage());
            } else {
                List<String> results = futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
                System.out.println("Fail-Fast: Успех! " + results);
            }
            return null;
        }).join();

        //2: Частичный результат
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (Exception e) {
            System.out.println("(Одна из задач упала, но мы продолжаем собирать остатки)");
        }

        List<String> success = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        for (CompletableFuture<String> f : futures) {
            if (f.isCompletedExceptionally()) {
                errors.add(f.handle((res, ex) -> ex.getCause().getMessage()).join());
            } else {
                success.add(f.join());
            }
        }

        System.out.println("Частичный результат: " + success + "\nОшибки: " + errors);

        pool.shutdown();
    }
}