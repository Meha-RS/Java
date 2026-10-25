package Pract7.Task5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("Pract7", "Task5", "courses.bin");

        List<CourseRecord> courses = List.of(
                new CourseRecord("Java Basics", 40),
                new CourseRecord("Java OOP", 60)
        );

        BinaryStorage.save(file, courses);
        System.out.println("Сохранено. Размер: " + Files.size(file) + " байт.");

        System.out.println("Прочитано:");
        BinaryStorage.load(file).forEach(System.out::println);

        System.out.println("\nТест: обрезанный файл");
        Path brokenFile = Path.of("Pract7", "Task5", "broken.bin");
        Files.writeString(brokenFile, "A");

        try {
            BinaryStorage.load(brokenFile);
        } catch (IOException e) {
            System.out.println("Поймали ошибку: " + e.getClass().getSimpleName());
        }
    }
}