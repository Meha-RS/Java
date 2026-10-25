package Pract7.Task3;

public class Demo {
    public static void main(String[] args) {
        System.out.println("--- Начало программы ---");

        try {
            try (ResourceTracker res1 = new ResourceTracker("Ресурс-1 (Чтение)");
                 ResourceTracker res2 = new ResourceTracker("Ресурс-2 (Запись)")) {

                res1.doWork();
                res2.doWork();

                System.out.println("Вызываем ошибку");
                throw new RuntimeException("Симуляция сбоя в середине pipeline");

            }
        } catch (Exception e) {
            System.out.println("Поймали исключение в main: " + e.getMessage());
        }
        System.out.println("Конец");
    }
}