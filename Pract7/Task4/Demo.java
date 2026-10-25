package Pract7.Task4;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) throws IOException {
        Path input = Path.of("Pract7", "Task4", "input.txt");
        Files.writeString(input, "Строка 1\nСтрока 2\nСтрока 3");


        System.out.println("Правильный вариант:");
        List<String> result = LineProcessor.readCorrectly(input);
        result.forEach(System.out::println);

        System.out.println("\nОшибочный вариант:");
        Stream<String> badStream = LineProcessor.readIncorrectly(input);

        badStream.forEach(System.out::println);
        System.out.println("Поток не закрыт");
    }
}