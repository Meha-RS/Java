package Pract7.Task7;

import java.io.IOException;
import java.nio.file.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("Pract7", "Task7", "import.csv");

        String badContent = "Java Basics;40\nJava OOP;60\nBad Line\nJava Files;20";
        Files.writeString(file, badContent);

        System.out.println("Импорт поврежденного файла");
        ImportResult result = CourseImporter.importCourses(file);

        if (result.success()) {
            System.out.println("Импорт успешен. Курсов: " + result.validCourses().size());
        } else {
            System.out.println("Импорт ОТМЕНЕН. Найдены ошибки:");
            result.errors().forEach(System.out::println);
            System.out.println("Каталог не был изменен (All-or-Nothing).");
        }
    }
}